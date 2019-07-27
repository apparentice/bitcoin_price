package com.alokomkar.btc.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.data.Resource
import com.alokomkar.btc.data.remote.ApiEmptyResponse
import com.alokomkar.btc.data.remote.ApiErrorResponse
import com.alokomkar.btc.data.remote.ApiResponse
import com.alokomkar.btc.data.remote.ApiSuccessResponse

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {


    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis") val dbSource = loadFromLocalDatabase()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            //Check if data needs to be fetched from remote based on some criteria
            if( shouldFetch(data) ) {
                fetchFromRemote(dbSource)
            }
            else {
                //When criteria says no remote data necessary
                result.addSource(dbSource) { localData ->
                    setValue(Resource.success(localData))
                }
            }

        }
    }

    private fun fetchFromRemote( dbSource : LiveData<ResultType>) {
        //Create api response from remote
        val apiResponse = createNetworkCall()

        // we re-attach dbSource as a new source, it will dispatch its latest value quickly - from local database
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        // once data from local db is dispatched, we need to check the remote for latest data
        result.addSource(apiResponse) { remoteResponse ->
            //remove both sources now
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when( remoteResponse ) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResultToLocalDb(processResponseData(remoteResponse))
                        appExecutors.mainThread().execute {
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            result.addSource(loadFromLocalDatabase()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    //On empty response - send data stored in local
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        result.addSource(loadFromLocalDatabase()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(remoteResponse.errorMessage, newData))
                    }
                }

            }

        }

    }

    //Mediator live data exposed as live data - used to observe data usually in view models
    fun asLiveData() = result as LiveData<Resource<ResultType>>

    protected open fun onFetchFailed() {}

    @WorkerThread
    protected open fun processResponseData(response: ApiSuccessResponse<RequestType>) = response.data

    @WorkerThread
    protected abstract fun saveCallResultToLocalDb(item: RequestType)


    @MainThread
    protected abstract fun createNetworkCall(): LiveData<ApiResponse<RequestType>>

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue)
            result.value = newValue
    }

    @MainThread
    protected abstract fun loadFromLocalDatabase(): LiveData<ResultType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

}