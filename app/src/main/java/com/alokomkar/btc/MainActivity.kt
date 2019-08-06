package com.alokomkar.btc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.alokomkar.btc.base.OnAdapterItemClickListener
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
        setObservers()
    }

    private fun setObservers() {
        observe(priceViewModel.currentPriceLiveData) {
            it?.apply {
                pbCurrentPrice.changeVisibility(status == Status.LOADING)
                tvCurrentPrice.changeVisibility(status != Status.LOADING)
                when( status ) {
                    Status.ERROR -> {
                        tvCurrentPrice.showSnackBar(this.message ?: "Unknown Error", R.string.retry) {
                            loadContent()
                        }
                        tvCurrentPrice.text = getString(R.string.oops)
                    }
                    Status.SUCCESS -> {
                        tvCurrentPrice.text = "Current Price : $ ${this.data?.sellingPrice}"
                    }
                    else -> {}
                }
            }
        }

        observe(priceViewModel.priceHistoryLiveData) {
            it?.apply {
                priceRefreshLayout.isRefreshing = (status == Status.LOADING )
                when (status) {
                    Status.ERROR -> {
                        showToast((this.message ?: "Unknown Error") + " : checking offline data")
                    }
                    Status.SUCCESS -> {
                        rvPriceHistory.apply {
                            adapter = PriceHistoryAdapter().apply {
                                removeAll()
                                val items = data ?: ArrayList()
                                var previousHeader = ""
                                items.forEach{ item ->
                                    if( previousHeader == "" ) {
                                        item.header = item.getPriceDate()
                                        previousHeader = item.header
                                    } else if( previousHeader != item.getPriceDate() ) {
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
                                data?.forEach { item ->
                                    addUnique(PriceIndexedData(itemIndex, item.getPriceDate()))
                                    itemIndex++
                                }
                                onAdapterItemClickListener = object : OnAdapterItemClickListener {
                                    override fun onItemClick(view: View, position: Int) {
                                        val item = getItemAtPosition(position)
                                        rvPriceHistory.smoothScrollToPosition(item.index + 5)
                                    }
                                }
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun loadContent() {
        priceViewModel.getCurrentPrice()
        priceViewModel.getPriceHistory()
    }

}
