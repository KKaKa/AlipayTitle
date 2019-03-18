package com.kkaka.alipaytitiedemo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_title_big.*
import kotlinx.android.synthetic.main.include_title_search.*
import kotlinx.android.synthetic.main.include_title_small.*

class MainActivity : AppCompatActivity(),AppBarLayout.OnOffsetChangedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarColor(R.color.title_blue).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_main)
        appBar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, offset: Int) {
        //获取竖直方向的偏移量
        val verticalOffset = Math.abs(offset)
        //AppBarLayout总的距离px
        val totalScrollRange = appBarLayout?.getTotalScrollRange()

        Log.i("ALipay","verticalOffset = $verticalOffset")
        Log.i("ALipay","totalScrollRange = $totalScrollRange")

        //背景颜色
        val backgroundColor = ContextCompat.getColor(this,R.color.title_blue)
        //根据竖直方向偏移量来进行颜色渐变
        val alpha = if(totalScrollRange!!.minus(verticalOffset) == 0) 0 else totalScrollRange.minus(verticalOffset)
        val colorSmall = Color.argb(alpha,Color.red(backgroundColor),Color.green(backgroundColor),Color.blue(backgroundColor))
        val color = Color.argb(verticalOffset,Color.red(backgroundColor),Color.green(backgroundColor),Color.blue(backgroundColor))

        //当上滑不超过一半时
        if(verticalOffset <= totalScrollRange.div(2)){
            view_title_search.visibility = View.VISIBLE
            title_search_mask.setBackgroundColor(color)
            view_title_small.visibility = View.GONE
        }else{
            view_title_search.visibility = View.GONE
            view_title_small.visibility = View.VISIBLE
            title_small_mask.setBackgroundColor(colorSmall)
        }
        title_big_mask.setBackgroundColor(color)

    }
}
