package com.example.appforlisting.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appforlisting.MainViewModel
import com.example.appforlisting.databinding.ActivityDetailBinding
import com.example.appforlisting.models.response.ResultList


class DetailActivity : AppCompatActivity() {
    private var binding: ActivityDetailBinding? = null
    lateinit var viewModel: MainViewModel
    private var result: ResultList? = null
    private var logTag = DetailActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        viewModel = MainViewModel()
        setContentView(binding?.root)
        checkBundle()
        setupToolBar()
        handleData()


    }

    private fun checkBundle() {
        val bundle = intent.extras
        result = bundle?.getSerializable("KEY") as ResultList?
    }

    private fun handleData() {
        binding?.textviewTitle?.text = result?.title
        binding?.textviewDescription?.text = result?._abstract
        binding?.textviewDate?.visibility = View.GONE
        //  binding?.textviewDate?.text = CommonFunctions.getFormattedDate(viewModel.date)
    }

    fun setupToolBar() {
        try {
            binding?.include?.tvToolbar?.text =
                "Details"
            binding?.include?.layLeft?.setOnClickListener {
                onBackPressed()
            }
            setSupportActionBar(binding?.include?.toolbar)
        } catch (e: Exception) {
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}