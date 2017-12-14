package com.xiaweizi.androidmd

import android.R.id
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.Explode
import android.transition.Slide
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.WeaponDetailActivity
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/10
 *     desc   : 武器详情界面
 * </pre>
 */
class WeaponDetailActivity : AppCompatActivity() {

    private lateinit var mBean: WeaponBean
    private lateinit var mIvDetail: ImageView
    private lateinit var mTvDetail: TextView
    private lateinit var mTbName: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        // 透明导航栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        // 透明状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // 设置转场动画
        window.enterTransition = Explode().setDuration(2000)
        window.exitTransition = Slide().setDuration(2000)
        setContentView(R.layout.activity_weapon_detail)
        initView()
        setSupportActionBar(mTbName)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initData()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mBean = intent.getParcelableExtra(Constants.EXTRA_WEAPON_BEAN)
        Glide.with(this).load(mBean.imageUrl).into(mIvDetail)
        mTvDetail.text = mBean.content
        mTbName.title = mBean.symbol
    }

    /**
     * 初始化 view
     */
    private fun initView() {
        mIvDetail = findViewById(R.id.iv_weapon_detail)
        mTvDetail = findViewById(R.id.tv_weapon_detail)
        mTbName = findViewById(R.id.tb_weapon_name)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId === id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}