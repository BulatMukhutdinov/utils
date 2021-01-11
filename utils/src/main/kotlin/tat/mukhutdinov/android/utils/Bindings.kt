package tat.mukhutdinov.android.utils

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("textColorAttr")
fun setTextColorAttr(textView: TextView, @AttrRes attr: Int) {
    val typedValue = TypedValue()
    val theme: Resources.Theme = textView.context.theme
    theme.resolveAttribute(attr, typedValue, true)

    textView.setTextColor(typedValue.data)
}

@BindingAdapter("verticalBias")
fun setVerticalBias(view: View, bias: Float) {
    val params = view.layoutParams as ConstraintLayout.LayoutParams
    params.verticalBias = bias
    view.layoutParams = params
}

@BindingAdapter("src")
fun setImageDrawable(imageView: ImageView, @DrawableRes res: Int?) {
    res?.let {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, it))
    }
}