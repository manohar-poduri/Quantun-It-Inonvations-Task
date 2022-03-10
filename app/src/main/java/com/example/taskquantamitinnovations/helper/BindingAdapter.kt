package com.example.taskquantamitinnovations.helper

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.taskquantamitinnovations.R

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun setImageUrl(
    view: ImageView,
    imageUrl: String?,
) {
    if (imageUrl != null && imageUrl.isNotEmpty()) {
        Glide.with(view).load(imageUrl).placeholder(R.drawable.download).into(view)
    }
}