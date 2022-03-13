package com.whide.partner.global

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object CustomBindingAdapter {


    @JvmStatic
    @BindingAdapter("android:src_url")
    fun loadImage(view: ImageView, url: String?) {
        if (url == null || url.equals("", ignoreCase = true) || url.equals(
                "-1",
                ignoreCase = true
            )
        ) {
            // view.setImageResource(R.mipmap.sqr_img_placeholder)
            return
        }
        Picasso.get()
            .load(url)
            .into(view)
    }


}