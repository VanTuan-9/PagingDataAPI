package com.example.ui.user

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.models.User
import com.example.ui.user.adapter.UserAdapter
import com.example.ui.user.adapter.UserListener
import com.example.ui.user.adapter.UserStateAdapter
import com.example.views.R
import com.example.views.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

@ExperimentalPagingApi
class UserAct : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: UserViewModel

    private val userListener: UserListener by lazy {
        object : UserListener {
            override fun onClick(model: User) {
                //

            }
        }
    }


    private val userAdapter: UserAdapter by lazy {
        UserAdapter(userListener = userListener,
            clickItem = {

            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUserRecycleView()
        initViewModel()
    }

    private fun initUserRecycleView() {
        binding.listUser.apply {
            layoutManager = LinearLayoutManager(this@UserAct)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            this.adapter = userAdapter.withLoadStateFooter(
                UserStateAdapter(retry = {
                    //
                    Log.d("initUserRecycleView", "CLICK RETRY")
                    // load tiếp
                    userAdapter.retry()
                })
            )

            userAdapter.addLoadStateListener { state ->
                binding.refreshBtn.isVisible = state.refresh is LoadState.Error
                binding.empty.isVisible =
                    state.refresh is LoadState.NotLoading && userAdapter.itemCount == 0
            }
        }

        binding.refreshBtn.setOnClickListener {
            //load lại toàn bộ dữ liệu , bắt đầu từ 1
            userAdapter.refresh()
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            // IO load data thoải mái từ api hoặc db
            viewModel.loadData().collectLatest {

                // Main Thread , update hiển thị
                userAdapter.submitData(it)
            }
        }
    }
}