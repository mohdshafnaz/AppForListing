package com.example.appforlisting.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appforlisting.MainViewModel
import com.example.appforlisting.adapters.MainListAdapter
import com.example.appforlisting.databinding.ActivityMainBinding
import com.example.appforlisting.models.response.ResultList
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(), MainListAdapter.ItemListener {
    private lateinit var mViewModel: MainViewModel
    private var binding: ActivityMainBinding? = null
    private var mAdapter: MainListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = MainViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setRecyclerView()
        getListApi()
        observeEvents()

    }

    private fun observeEvents() {

        lifecycle.coroutineScope.launchWhenStarted {
            mViewModel.listEvent.collect { event ->
                when (event) {
                    is MainViewModel.MainEvents.GetList -> {
                        if (event.data.results != null) {
                            mAdapter?.submitList(event.data.results)
                            mAdapter?.notifyDataSetChanged()
                        }
                    }
                }

            }

        }

    }

    override fun onResume() {
        super.onResume()
    }

    private fun getListApi() {
        mViewModel.getList()

    }


    private fun setRecyclerView() {
        mAdapter = MainListAdapter(this, this)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding?.recyclerView?.layoutManager = linearLayoutManager
        binding?.recyclerView?.adapter = mAdapter

    }

    override fun onItemClick(item: ResultList) {
        navigateDetailScreen(item)
    }

    private fun navigateDetailScreen(item: ResultList) {
        val bundle = Bundle()
        bundle.putSerializable("KEY", item)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }


}