package com.artemis.storageunitman.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StorageUnitManItem(
    val unit_des: String?= null,
    val unit_image: String?= null,
    val unit_name: String?= null
): Parcelable