package com.artemis.storageunitman.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUploader {

        fun loadImage(view: ImageView, url: String){
            Glide.with(view).load(url).into(view)
        }


}