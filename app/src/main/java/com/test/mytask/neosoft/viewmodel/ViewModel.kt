package com.test.mytask.neosoft.viewmodel

import androidx.lifecycle.ViewModel
import com.test.mytask.neosoft.adapter.FirstListAdapter
import com.test.mytask.neosoft.adapter.CategoryAdapter
import com.test.mytask.neosoft.adapter.SecondListAdapter
import com.test.mytask.neosoft.adapter.ThreeListAdapter

class ViewModel: ViewModel() {
    var categoryAdapter = CategoryAdapter()
    var firstListAdapter = FirstListAdapter()
    var secondListAdapter = SecondListAdapter()
    var threeListAdapter = ThreeListAdapter()
}

