package com.xiaweizi.androidmd

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.MainActivity
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/10
 *     desc   : 主界面
 * </pre>
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mIvHead: ImageView
    private lateinit var mDrawerLayout: DrawerLayout
    private var currentFragment: Fragment? = null


    private var weaponFragment: WeaponFragment ?= null
    private var peopleFragment: TestFragment ?= null
    private var subFragment: TestFragment ?= null
    private var thingsFragment: TestFragment ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_main)
        initView()
        initData()
        initListener()
        if (weaponFragment == null) {
            weaponFragment = WeaponFragment()
        }
        switchFragment(weaponFragment!!)
    }

    /**
     * 初始化 view
     */
    private fun initView() {
        mDrawerLayout = findViewById(R.id.dl_main)
        val nav: NavigationView = findViewById(R.id.nv_left)
        mIvHead = nav.getHeaderView(0).findViewById(R.id.iv_head)
    }


    /**
     * 初始化数据
     */
    private fun initData() {
        Glide.with(this).load(Constants.HEAD_IMAGE_URL)
                .into(mIvHead)
    }

    /**
     * 初始化 listener
     */
    private fun initListener() {
        // 左侧侧滑菜单选择事件
        nv_left!!.setCheckedItem(R.id.nav_weapon_inc)
        nv_left!!.setNavigationItemSelectedListener { item ->
            nv_left!!.setCheckedItem(item.itemId)
            dl_main!!.closeDrawers()
            when (item.itemId) {
                R.id.nav_people_inc -> openBottomSheetDialog()
                R.id.nav_sub_inc -> {
                    // 弹出 SnackBar 和 Toast
                    Snackbar.make(weaponFragment!!.mFloatButton, "弹出 SnackBar", Snackbar.LENGTH_SHORT).setAction("Cancel") {
                        Toast.makeText(this@MainActivity, "Cancel this action", Toast.LENGTH_SHORT).show()
                    }.show()
                }
                R.id.nav_things_inc -> {
                    // 弹出 AlertDialog
                    AlertDialog.Builder(this).setTitle("title").setMessage("message").setNegativeButton("确认", null)
                            .setPositiveButton("取消", null).show()
                }
            }
            false
        }

        // 底部 view 点击事件处理
        navigation!!.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_weapon_inc -> {
                    if (weaponFragment == null) {
                        weaponFragment = WeaponFragment()
                    }
                    switchFragment(weaponFragment!!)
                }
                R.id.bottom_people_inc -> {
                    if (peopleFragment == null) {
                        peopleFragment = TestFragment.getInstance(getString(R.string.people_inc))
                    }
                    switchFragment(peopleFragment!!)
                }
                R.id.bottom_sub_inc -> {
                    if (subFragment == null) {
                        subFragment = TestFragment.getInstance(getString(R.string.sub_inc))
                    }
                    switchFragment(subFragment!!)
                }
                R.id.bottom_things_inc -> {
                    if (thingsFragment == null) {
                        thingsFragment = TestFragment.getInstance(getString(R.string.thing_inc))
                    }
                    switchFragment(thingsFragment!!)
                }
            }
            true
        }
    }

    /**
     * 打开 bottomSheetDialog 对话框
     */
    private fun openBottomSheetDialog() {
//        val parent = LinearLayout(this)
//        parent.orientation = LinearLayout.VERTICAL
//        val title = TextView(this)
//        title.text = "枪械简介"
//        title.textSize = 20f
//        title.setPadding(15, 15, 15, 0)
//        parent.addView(title)
//
//        val recyclerView = RecyclerView(this)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        recyclerView.adapter = myAdapter
//        parent.addView(recyclerView)
//
//        val dialog = BottomSheetDialog(this)
//        dialog.setContentView(parent)
//        dialog.show()

    }

    /**
     * 切换Fragment的显示

     * @param target 要切换的 Fragment
     */
    private fun switchFragment(target: Fragment) {

        // 如果当前的fragment 就是要替换的fragment 就直接return
        if (currentFragment === target) return

        // 获得 Fragment 事务
        val transaction = supportFragmentManager.beginTransaction()

        // 如果当前Fragment不为空，则隐藏当前的Fragment
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        // 如果要显示的Fragment 已经添加了，那么直接 show
        if (target.isAdded) {
            transaction.show(target)
        } else {
            // 如果要显示的Fragment没有添加，就添加进去
            transaction.add(R.id.fl_content, target, target.javaClass.name)
        }

        // 事务进行提交
        transaction.commit()

        //并将要显示的Fragment 设为当前的 Fragment
        currentFragment = target
    }



}
