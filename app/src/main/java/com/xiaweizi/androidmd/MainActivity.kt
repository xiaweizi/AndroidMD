package com.xiaweizi.androidmd

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*





class MainActivity : AppCompatActivity() {

    private lateinit var mIvHead: ImageView
    private var myAdapter: MyAdapter? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var mToolbar: Toolbar
    private lateinit var mDrawerLayout: DrawerLayout

    private var mToggle: ActionBarDrawerToggle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
        initListener()
        mRefreshLayout.isRefreshing = true
        getData(0)
    }

    /**
     * 初始化 view
     */
    private fun initView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRefreshLayout = findViewById(R.id.refresh_layout)
        mToolbar = findViewById(R.id.toolbar)
        mDrawerLayout = findViewById(R.id.dl_main)
        val nav: NavigationView = findViewById(R.id.nv_left)
        mIvHead = nav.getHeaderView(0).findViewById(R.id.iv_head)
    }


    /**
     * 初始化数据
     */
    private fun initData() {
        myAdapter = MyAdapter()
        mRecyclerView.adapter = myAdapter
        staggeredVertical()
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        setSupportActionBar(mToolbar)
        Glide.with(this).load(Constants.HEAD_IMAGE_URL)
                .into(mIvHead)
        //给打开左侧菜单的按钮是指特效
        mToggle = ActionBarDrawerToggle(this@MainActivity,
                mDrawerLayout,
                mToolbar,
                R.string.open, R.string.close)
        mToggle!!.syncState()
        mDrawerLayout.addDrawerListener(mToggle!!)
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
                    val weaponBean = adapter.data[position]
                    val intent = Intent(this@MainActivity, WeaponDetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_WEAPON_BEAN, weaponBean)
                    startActivity(intent)
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
    private fun getData(state: Int) {
        MyClient.instance
                .create(MyService::class.java, Constants.MASTER_URL)
                .weaponInfo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var status = it.status
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
        Toast.makeText(this, "获取数据失败！", Toast.LENGTH_SHORT).show()
    }

    /**
     * 成功获取数据
     */
    private fun setData(state: Int, it:WeaponModelnfo) {
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
    }

    /***************************
     * 创建菜单
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /***************************
     * 给菜单设置点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.staggeredHorizontal -> staggeredHorizontal()
            R.id.staggeredVertical -> staggeredVertical()
            R.id.linearHorizontal -> linearHorizontal()
            R.id.linearVertical -> linearVertical()
            R.id.gridVertical -> gridVertical()
            R.id.gridHorizontal -> gridHorizontal()
        }
        return true
    }


    /**
     * 垂直瀑布流
     */
    private fun staggeredVertical() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
    /**
     * 水平瀑布流
     */
    private fun staggeredHorizontal() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
    }
    /**
     * 水平列表
     */
    private fun linearHorizontal() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }
    /**
     * 垂直列表
     */
    private fun linearVertical() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }
    /**
     * 水平宫格
     */
    private fun gridVertical() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT)
        mRecyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    }
    /**
     * 垂直宫格
     */
    private fun gridHorizontal() {
        myAdapter!!.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mRecyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
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
}
