package com.artemis.storageunitman.adapters

import com.artemis.storageunitman.data.StorageUnitManItem

interface StorageItemClickListener {
    fun onItemClicked(storageUnitManItem: StorageUnitManItem)
}