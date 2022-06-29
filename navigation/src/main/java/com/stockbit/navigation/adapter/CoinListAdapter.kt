package com.stockbit.navigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.model.ListDataCoins
import com.stockbit.navigation.R
import kotlinx.android.synthetic.main.item_coin_list.view.*
import java.text.DecimalFormat

class CoinListAdapter(private val context: Context, private val mCoinList: MutableList<ListDataCoins>) : RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_list, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.tv_coinName?.text = mCoinList[position].CoinInfo?.Name
        holder.tv_coinFullName?.text = mCoinList[position].CoinInfo?.FullName
        holder.tv_coinPrice?.text = mCoinList[position].DISPLAY?.USD?.PRICE
        holder.tv_priceChange?.text = mCoinList[position].DISPLAY?.USD?.CHANGE24HOUR
//
        holder.containerView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return mCoinList.size
    }

    class CoinViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val tv_coinName = containerView.tv_coinName
        val tv_coinFullName = containerView.tv_coinFullName
        val tv_coinPrice = containerView.tv_coinPrice
        val tv_priceChange = containerView.tv_priceChange
    }

    fun currencyFormatter(num: String): String? {
        val m = num.toDouble()
        val formatter = DecimalFormat("###,###.###")
        return formatter.format(m)
    }

}