package com.sumin.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sumin.cryptoapp.adapters.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinInfoAdapter
    private lateinit var rvCoinPriceList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coin_price_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvCoinPriceList = findViewById(R.id.rvCoinPriceList)
        adapter = CoinInfoAdapter(
            this,
//Вариант 2. 3) Обрабатываю ClickListener (п. 1 и 2 в CoinInfoAdapter)
            onCoinClickListener = {
                Log.d("DETAIL_INFO", it.fromSymbol)
                val intent = CoinDetailActivity.newIntent(this, it.fromSymbol)
                startActivity(intent)
            }
        )
////Вариант 1. 4) Обрабатываю ClickListener (п. 1,2,3 в CoinInfoAdapter)
//        adapter.onCoinClickListener = object : OnCoinClickListener {
//            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
//                Log.d("DETAIL_INFO", coinPriceInfo.fromSymbol)
//                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
//                startActivity(intent)
//            }
//        }
        rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(
            this, CoinViewModelFactory(application)
        )[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
            adapter.coinInfoList = it
        })

    }

}