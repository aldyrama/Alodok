package com.alodokter.basic_mvvm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alodokter.basic_mvvm.R
import com.alodokter.basic_mvvm.adapter.HomeAdapter
import com.alodokter.basic_mvvm.base.BaseActivity
import com.alodokter.basic_mvvm.model.ProductModel
import com.alodokter.basic_mvvm.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.component_list.*
import kotlinx.android.synthetic.main.component_progress_bar.*

class HomeFragment : Fragment(), BaseActivity {

    var isRefresh = false
    private var list = ArrayList<ProductModel>()
    private val adapter by lazy { HomeAdapter() }
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private fun init(){
        initViewModel()
        fetchData()
//        swipe_refresh_entertainment.setOnRefreshListener {
//            refresh()
//        }
    }

    private fun initViewModel(){
        recyclerView.adapter = adapter
        viewModel.resource.observe(viewLifecycleOwner, Observer{
            when(it?.status) {
                Resource.LOADING -> onLoading()
                Resource.SUCCESS -> onSuccess()
                Resource.ERROR -> onFailure(it.error)
            }
        })

//        lifecycle.addObserver(viewModel)

        viewModel.dataList.observe(viewLifecycleOwner, Observer{
            list = it
            adapter.setHomeList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun fetchData(){
        list.clear();
        viewModel.getHome()
    }

    override fun refresh() {
        isRefresh = true
        list.clear();
        viewModel.onRefreshHome()
    }

    override fun reload() {
    }

    override fun onFailure(t: Throwable) {
        news_progress_load.visibility = View.GONE
//        swipe_refresh_entertainment.isRefreshing = false
        Toast.makeText(activity, "Failure $t", Toast.LENGTH_SHORT).show()
    }

    override fun onLoading() {
        if (!isRefresh) news_progress_load.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        news_progress_load.visibility = View.GONE
//        swipe_refresh_entertainment.isRefreshing = false
    }
}