package com.stockbit.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stockbit.model.ListDataCoins
import com.stockbit.navigation.adapter.CoinListAdapter
import com.stockbit.remote.ExampleService
import com.stockbit.remote.di.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_watchlist.*
import kotlinx.android.synthetic.main.fragment_watchlist.view.*

class WatchlistFragment : Fragment() {

    private var mAdapter: CoinListAdapter? = null
    private var listCoin : MutableList<ListDataCoins>? = ArrayList()

    private var progressBar : ProgressBar? = null
    private var swipeRefreshLayout : SwipeRefreshLayout? = null
    private  var cons_noConnection : ConstraintLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_watchlist, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        cons_noConnection = view.findViewById(R.id.cons_noConnection)

        view.recyclerList!!.layoutManager = LinearLayoutManager(context)

        mAdapter =
            this?.let { CoinListAdapter(context!!, listCoin!!) }
        view.recyclerList.adapter = mAdapter

        //swipe refresh
        swipeRefreshLayout?.setOnRefreshListener {
            getLoadData()
        }

        getLoadData()

        return view
    }

    //get riwayat nanti buat api baru
    private fun getLoadData(){
        progressBar?.visibility = View.VISIBLE
        swipeRefreshLayout?.isRefreshing = false
        cons_noConnection?.visibility = View.GONE
        var apiService: ExampleService = ApiClient.ApiClientStockbit.getClient().create(ExampleService::class.java)
        apiService.loadData(10,"USD")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    progressBar?.visibility = View.GONE
                    listCoin?.clear()
                    if (users?.Type == 100){
                        if (users?.Data.isNullOrEmpty()){
                            Toast.makeText(context,"Data Kosong", Toast.LENGTH_SHORT).show()
                            mAdapter?.notifyDataSetChanged()
                        }else{
                            users.Data?.let { listCoin?.addAll(it) }
                            mAdapter?.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(context, users.Message.toString(), Toast.LENGTH_SHORT).show()
                        cons_noConnection?.visibility = View.VISIBLE
                    }
                }, { error ->
                    swipeRefreshLayout?.isRefreshing = false
                    progressBar?.visibility = View.GONE
                    cons_noConnection?.visibility = View.VISIBLE
                    Log.e("error", error.toString())
                }
            )
    }

}