package com.xiaweizi.androidmd

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidmd.TestFragment
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/12/18
 *     desc   :
 * </pre>
 */
class TestFragment : Fragment(){

    private lateinit var mConetent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            mConetent = arguments.getString("content")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_test, null)
        val tvContent = view.findViewById<TextView>(R.id.tv_content)
        tvContent.text = mConetent
        return view
    }

    public companion object {

        fun getInstance(content: String): TestFragment {
            val testFragment = TestFragment()
            val bundle = Bundle()
            bundle.putString("content", content)
            testFragment.arguments = bundle
            return testFragment
        }
    }

}