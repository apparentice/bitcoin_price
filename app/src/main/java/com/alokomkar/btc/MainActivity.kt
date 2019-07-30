package com.alokomkar.btc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.alokomkar.btc.data.Status
import com.alokomkar.btc.extension.changeVisibility
import com.alokomkar.btc.extension.observe
import com.alokomkar.btc.extension.showSnackBar
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

class MainActivity : AppCompatActivity() {

    private lateinit var priceViewModel: PriceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        priceViewModel = ViewModelProviders.of(this).get(PriceViewModel::class.java)
        loadContent()
        fab.setOnClickListener { loadContent() }
        priceRefreshLayout.setOnRefreshListener { loadContent() }

    }

    private fun showToast(message: String) {

    }

    private fun loadContent() {
        observe(priceViewModel.getCurrentPrice()) {
            it?.apply {
                pbCurrentPrice.changeVisibility(status == Status.LOADING )
                tvCurrentPrice.changeVisibility( status != Status.LOADING )
                if( status == Status.ERROR ) {
                    Log.d("NetworkResponse", "Failed : " + this.message)
                    tvCurrentPrice.showSnackBar(this.message ?: "Unknown Error", R.string.retry) {
                        loadContent()
                    }
                    tvCurrentPrice.text = "Oops!!"
                }
                else if( status == Status.SUCCESS ) {
                    Log.d("NetworkResponse", "Success " + this.data?.sellingPrice)
                    tvCurrentPrice.text = "Current Price : $ " + this.data?.sellingPrice.toString()
                }
            }
        }
        fun fetchPriceHistory( fetchFromLocal : Boolean ) {
            observe(priceViewModel.getPriceHistory(fetchFromLocal)) {
                it?.apply {
                    pbPriceHistory.changeVisibility(status == Status.LOADING)
                    priceRefreshLayout.isRefreshing = (status == Status.LOADING )
                    if( status == Status.ERROR ) {
                        Log.d("NetworkResponse", "Failed : " + this.message)
                        showToast((this.message ?: "Unknown Error") + " : checking offline data")
                        fetchPriceHistory(true)
                    }
                    else if( status == Status.SUCCESS ) {
                        Log.d("NetworkResponse", "Price history Success " + this.data?.size)
                        rvPriceHistory.apply {
                            adapter = PriceHistoryAdapter().apply {
                                removeAll()
                                val items = data ?: ArrayList()
                                var previousHeader = ""
                                items.forEach{ item ->
                                    if( previousHeader == "" ) {
                                        item.header = item.getPriceDate()
                                        previousHeader = item.header
                                    }
                                    else if( previousHeader != item.getPriceDate() ) {
                                        item.header = item.getPriceDate()
                                        previousHeader = item.header
                                    }
                                }
                                addAll(items)
                            }
                        }
                        rvDate.apply {
                            adapter = PriceDateAdapter().apply {
                                removeAll()
                                var itemIndex = 0
                                data?.forEach {
                                        item ->
                                    addUnique(PriceIndexedData(itemIndex, item.getPriceDate()))
                                    itemIndex++
                                }
                                onItemClickListener = { _, _, item ->
                                    Log.d("SmoothScroll", "Position : " + item.index)
                                    rvPriceHistory.smoothScrollToPosition(item.index + 5)
                                }
                            }
                        }
                    }
                }
            }
        }
        fetchPriceHistory(false)

    }

    class NetworkConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {

        }

    }

}
