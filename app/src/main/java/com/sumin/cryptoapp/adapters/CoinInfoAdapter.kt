package com.sumin.cryptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.sumin.cryptoapp.R
import com.sumin.cryptoapp.databinding.ItemCoinInfoBinding
import com.sumin.cryptoapp.pojo.CoinPriceInfo

class CoinInfoAdapter(
    private val context: Context,
//Создание ClickListener Вариант 2. 1) Обьявляю onCoinClickListener
// в конструкторе CoinInfoAdapter
    private val onCoinClickListener: (CoinPriceInfo) -> Unit
) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
////Вариант 1. 2) Создаю нулабельную var переменную
//    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        with(holder) {
            bind(coinInfoList[position])
////Вариант1. 3) вызываю onCoinClickListener в onBindViewHolder
//        itemView.setOnClickListener {
//            onCoinClickListener?.onCoinClick(coinInfoList[position])
//        }
//  Вариант2. 2) вызываю onCoinClickListener в onBindViewHolder
            itemView.setOnClickListener {
                onCoinClickListener.invoke(coinInfoList[position])
            }
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding) :
        ViewHolder(binding.root) {

        fun bind(coinPriceInfo: CoinPriceInfo) {
            with(binding) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text =
                    String.format(symbolsTemplate, coinPriceInfo.fromSymbol, coinPriceInfo.tosymbol)
                tvPrice.text = coinPriceInfo.price.toString()
                tvLastUpdate.text =
                    String.format(lastUpdateTemplate, coinPriceInfo.getFormattedTime())
                Picasso.get()
                    .load(coinPriceInfo.getFullImageUrl())
                    .into(ivLogoCoin)
            }
        }
    }
}
//////Создание ClickListener Вариант 1. 1) Создаю интерфейс
//interface OnCoinClickListener{
//    fun onCoinClick(coinPriceInfo: CoinPriceInfo)
//}


//Не забудь разобраться со временем обновления. Прриложение показывает -3 часа.