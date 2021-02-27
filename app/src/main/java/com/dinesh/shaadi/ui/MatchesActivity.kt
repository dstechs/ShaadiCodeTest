package com.dinesh.shaadi.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinesh.shaadi.databinding.ActivityMainBinding
import com.dinesh.shaadi.ui.adapter.MatchesAdapter
import com.dinesh.shaadi.util.RequestResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MatchesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstCall = true
    private val matchesViewModel: MatchesViewModel by viewModels()
    private val mAdapter: MatchesAdapter by lazy {
        MatchesAdapter() {
            matchesViewModel.updateItem(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        observerData()
        observerRequestState()
        initRecyclerView()
        binding.srlSwipe.setOnRefreshListener {
            callRequest()
        }
    }

    private fun initRecyclerView() {
        binding.rvMatches.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = mAdapter
        }
    }

    private fun callRequest() {
        firstCall = false
        matchesViewModel.requestMatches()
    }

    private fun observerData() {
        matchesViewModel.matches.observe(this, Observer {
            mAdapter.updateList(it)
            if (firstCall && it.isEmpty())
                callRequest()
        })
    }

    private fun observerRequestState() {
        matchesViewModel.loadingObserver.observe(this, Observer {
            when (it) {
                RequestResource.Status.SUCCESS -> {
                    srlSwipe?.isRefreshing = false
                }
                RequestResource.Status.ERROR -> {
                    srlSwipe?.isRefreshing = false
                }
                RequestResource.Status.LOADING -> {
                    srlSwipe?.isRefreshing = true
                }
            }
        })
    }
}