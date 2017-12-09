package com.xiaweizi.androidmd

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    private var myAdapter: MyAdapter? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        init()
        initListener()
        mRefreshLayout.isRefreshing = true
        getData(0)

    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRefreshLayout = findViewById(R.id.refresh_layout)
    }

    private fun init() {
        myAdapter = MyAdapter()
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.adapter = myAdapter

    }

    private fun initListener() {
        mRefreshLayout.setOnRefreshListener {
            mRefreshLayout.isRefreshing = true
            getData(1)
        }
        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                if (adapter is MyAdapter) {
                    Log.i("itemTouch", adapter.data[position].toString())
                }
            }
        })
        myAdapter!!.setOnLoadMoreListener {
            getData(2)
        }
    }

    /**
     * 获取数据
     * 0 第一次进入
     * 1 下拉刷新
     * 2 上啦加载更多
     */
    fun getData(state: Int) {
        MyClient.getInstance()
                .create(MyService::class.java, "http://owj4ejy7m.bkt.clouddn.com/getWeaponInfo/")
                .weaponInfo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var status = it.status
                    if (status == 0) {
                        when (state) {
                            0 -> {
                                myAdapter!!.setNewData(it.data)
                                mRefreshLayout.isRefreshing = false
                            }
                            1 -> {
                                Toast.makeText(this, "刷新成功！", Toast.LENGTH_SHORT).show()
                                Collections.shuffle(it.data)
                                myAdapter!!.setNewData(it.data)
                                mRefreshLayout.isRefreshing = false
                            }
                            2 -> {
                                Thread(Runnable {
                                    Thread.sleep(800)
                                    runOnUiThread {
                                        myAdapter!!.addData(it.data)
                                        myAdapter!!.loadMoreComplete()
                                    }
                                }).start()

                            }
                        }
                    } else {
                        mRefreshLayout.isRefreshing = false
                        Toast.makeText(this, "获取数据失败！", Toast.LENGTH_SHORT).show()
                    }
                }, {
                    mRefreshLayout.isRefreshing = false
                    Toast.makeText(this, "获取数据失败！", Toast.LENGTH_SHORT).show()
                })
    }
}
