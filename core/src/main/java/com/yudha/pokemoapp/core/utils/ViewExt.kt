package com.yudha.pokemoapp.core.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadImage(
    url: String?,
    @DrawableRes placeholder: Int? = null,
    @DrawableRes error: Int? = null,
) {
    var builder = Glide.with(this.context).load(url)
    if (placeholder != null) builder = builder.placeholder(placeholder)
    if (error != null) builder = builder.error(error)
    builder.into(this)
}
