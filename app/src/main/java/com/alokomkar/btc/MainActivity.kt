package com.alokomkar.btc

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alokomkar.btc.data.Status
import com.alokomkar.btc.extension.changeVisibility
import com.alokomkar.btc.extension.observe
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun loadContent() {
        observe(priceViewModel.getCurrentPrice()) {
            it?.apply {
                pbCurrentPrice.changeVisibility(status == Status.LOADING )
                tvCurrentPrice.changeVisibility( status != Status.LOADING )
                if( status == Status.ERROR ) {
                    Log.d("NetworkResponse", "Failed : " + this.message)
                    showToast(this.message ?: "Unknown Error")
                    tvCurrentPrice.text = "Oops!!"
                }
                else if( status == Status.SUCCESS ) {
                    Log.d("NetworkResponse", "Success " + this.data?.sellingPrice)
                    tvCurrentPrice.text = "Current Price : $ " + this.data?.sellingPrice.toString()
                }
            }
        }
        observe(priceViewModel.getPriceHistory()) {
            it?.apply {
                pbPriceHistory.changeVisibility(status == Status.LOADING)
                priceRefreshLayout.isRefreshing = false
                if( status == Status.ERROR ) {
                    Log.d("NetworkResponse", "Failed : " + this.message)
                }
                else if( status == Status.SUCCESS ) {
                    Log.d("NetworkResponse", "Price history Success " + this.data?.size)
                    rvPriceHistory.apply {
                        adapter = PriceHistoryAdapter().apply {
                            removeAll()
                            val items = data ?: ArrayList()
                            addAll(items)
                        }
                    }
                    rvDate.apply {
                        adapter = PriceDateAdapter().apply {
                            removeAll()
                            var itemIndex = 0
                            data?.forEach {
                                    item ->
                                addUnique(PriceIndexedData(itemIndex, item.priceDate))
                                itemIndex++
                            }
                            onItemClickListener = { _, _, item ->
                                Log.d("SmoothScroll", "Position : " + item.index)
                                rvPriceHistory.layoutManager?.scrollToPosition(item.index + 5)
                            }
                        }
                    }
                }
            }
        }
    }

}
