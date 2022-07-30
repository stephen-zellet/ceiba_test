package com.example.ceiba_test.core.util

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.example.ceiba_test.core.visible

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, @DrawableRes imageResource: Int?) {
    if (imageResource == null || imageResource == 0) {
        imageView.visibility = View.GONE
        return
    }
    imageView.visibility = View.VISIBLE
    imageView.setImageResource(imageResource)
}

@BindingAdapter("setVisible")
fun visible(view:View,value:Boolean?){
    value?.let {
        view.visible = it
    }
}

@BindingAdapter("setHtmlText")
fun setHtmlText(textView: TextView, text: Any?) {
    setText(textView, text)
}

@BindingAdapter("setText")
fun setText(textView: TextView, text: Any?) {
    if (text == null) {
        textView.visibility = View.GONE
        return
    }
    if (text is CharSequence) {
        if (text.isEmpty()) {
            textView.visibility = View.GONE
            return
        }
        textView.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(text.toString().replace("%20"," "), Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            textView.text = Html.fromHtml(text.toString().replace("%20"," "))
        }
    } else if (text is Int) {
        val context = textView.context
        if (text == 0) {
            textView.visibility = View.GONE
            return
        }
        textView.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(context.getString(text), Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            textView.text = Html.fromHtml(context.getString(text))
        }
    }
}
