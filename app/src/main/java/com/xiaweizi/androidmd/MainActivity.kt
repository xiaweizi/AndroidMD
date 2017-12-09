package com.xiaweizi.androidmd

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var mIvHead: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav: NavigationView = findViewById(R.id.nv_left)
        mIvHead = nav.getHeaderView(0).findViewById(R.id.iv_head)
        Glide.with(this).load(Contants.HEAD_IMAGE_URL)
                .into(mIvHead)
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fl_content, MainFragment())
        beginTransaction.commit()
    }

}
