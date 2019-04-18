package com.exam.zoointro.ui.widget

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView

import kotlinx.android.synthetic.main.title_bar.view.*

class TitleBar(context: Context?, attrs: AttributeSet?): FrameLayout(context, attrs) {

   private val titleView: TextView?
        get() = text_title
   private val leftButton: ImageButton?
        get() = button_left

    fun init(@StringRes title: Int, titleString: String?, showLeftIcon: Boolean) {
        titleView?.text = if (title != 0) context.getText(title) else titleString
        leftButton?.visibility = if (showLeftIcon) View.VISIBLE else View.GONE
    }
}