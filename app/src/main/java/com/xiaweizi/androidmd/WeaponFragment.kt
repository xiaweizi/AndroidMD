package com.xiaweizi.androidmd

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.WeaponFragment
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/18
 *     desc   :
 * </pre>
 */
class WeaponFragment : Fragment() {

    private var myAdapter: MyAdapter? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var mToolbar: Toolbar
    private lateinit var mView: View
    lateinit var mFloatButton: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_weapon, null)
        initView()
        initData()
        initListener()
        mRefreshLayout.isRefreshing = true
        getData(0)
        return mView
    }

    /**
     * 初始化 view
     */
    private fun initView() {
        mRecyclerView = mView.findViewById(R.id.recyclerView)
        mRefreshLayout = mView.findViewById(R.id.refresh_layout)
        mToolbar = mView.findViewById(R.id.toolbar)
        mFloatButton = mView.findViewById(R.id.fab)
    }


    /**
     * 初始化数据
     */
    private fun initData() {
        myAdapter = MyAdapter()
        staggeredVertical()
        mRecyclerView.adapter = myAdapter
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        val mItemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback())
        mItemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    /**
     * 初始化 listener
     */
    private fun initListener() {
        mRefreshLayout.setOnRefreshListener {
            mRefreshLayout.isRefreshing = true
            getData(1)
        }
        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                if (adapter is MyAdapter) {
                    activity.window.exitTransition = Explode()
                    val weaponBean = adapter.data[position]
                    val intent = Intent(activity, WeaponDetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_WEAPON_BEAN, weaponBean)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
                }
            }
        })
        myAdapter!!.setOnLoadMoreListener {
            getData(2)
        }
        mFloatButton.setOnClickListener {
            mRecyclerView.smoothScrollToPosition(0)
        }

    }


    /**
     * 获取数据
     * 0 第一次进入
     * 1 下拉刷新
     * 2 上啦加载更多
     */
    private fun getData(state: Int) {
        MyClient.instance
                .create(MyService::class.java, Constants.MASTER_URL)
                .weaponInfo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val status = it.status
                    if (status == 0) {
                        setData(state, it)
                    } else {
                        showError()
                    }
                }, {
                    showError()
                })
    }

    /**
     * 获取数据失败
     */
    private fun showError() {
        mRefreshLayout.isRefreshing = false
        Toast.makeText(activity, "获取数据失败！", Toast.LENGTH_SHORT).show()
    }

    /**
     * 成功获取数据
     */
    private fun setData(state: Int, it: WeaponModelnfo) {
        when (state) {
            0 -> {
                myAdapter!!.setNewData(it.data)
                mRefreshLayout.isRefreshing = false
            }
            1 -> {
                Toast.makeText(activity, "刷新成功！", Toast.LENGTH_SHORT).show()
                Collections.shuffle(it.data)
                myAdapter!!.setNewData(it.data)
                mRefreshLayout.isRefreshing = false
            }
            2 -> {
                Thread(Runnable {
                    Thread.sleep(800)
                    activity.runOnUiThread {
                        myAdapter!!.addData(it.data)
                        myAdapter!!.loadMoreComplete()
                    }
                }).start()

            }
        }
    }

    internal inner class ItemTouchHelperCallback : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            myAdapter!!.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            myAdapter!!.onItemDismiss(viewHolder.adapterPosition)
        }
    }

    /**
     * 垂直瀑布流
     */
    private fun staggeredVertical() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
}