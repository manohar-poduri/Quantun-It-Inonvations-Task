package com.example.taskquantamitinnovations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.taskquantamitinnovations.adapter.PublishedAdapter
import com.example.taskquantamitinnovations.databinding.ActivityMainBinding
import com.example.taskquantamitinnovations.localstorage.PublishedRepository
import com.example.taskquantamitinnovations.services.PublishedModel
import com.example.taskquantamitinnovations.viewmodel.MainVM

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var recyclerViewAdapter: PublishedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = ViewModelProvider(this@MainActivity).get(MainVM::class.java)
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)

        binding.viewModel?.getData(this)
        binding.viewModel?.model?.observe(this) {
            it.let {
                binding.recyclerView.apply {
                    recyclerViewAdapter = PublishedAdapter(this@MainActivity, it)
                    adapter = recyclerViewAdapter

                }
            }
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.count()!! > 0) {
                    recyclerViewAdapter?.filter?.filter(s.toString())
                } else {
                    binding.viewModel?.getData(this@MainActivity)
                }
            }
        })
    }

    fun activityLoader(isShow: Boolean) {
        if (isShow) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}