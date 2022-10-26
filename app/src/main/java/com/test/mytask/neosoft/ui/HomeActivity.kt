package com.test.mytask.neosoft.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.test.mytask.databinding.ActivityHomeBinding
import com.test.mytask.neosoft.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        initView()

        mBinding.etSearch.doOnTextChanged { text, start, before, count ->
            text?.let {
                viewModel.firstListAdapter.filter.filter(it.toString())
            }
        }

        mBinding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    1 -> mBinding.recyclerView.adapter = viewModel.secondListAdapter
                    2 -> mBinding.recyclerView.adapter = viewModel.threeListAdapter
                    3 -> mBinding.recyclerView.adapter = viewModel.firstListAdapter
                    4 -> mBinding.recyclerView.adapter = viewModel.secondListAdapter
                    5 -> mBinding.recyclerView.adapter = viewModel.threeListAdapter
                    else -> {
                        mBinding.recyclerView.adapter = viewModel.firstListAdapter
                    }
                }
            }
        })
    }

    private fun initView() {
        mBinding.viewPager.adapter = viewModel.categoryAdapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
        mBinding.recyclerView.adapter = viewModel.firstListAdapter
    }
}