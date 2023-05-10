package com.artemis.storageunitman.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artemis.storageunitman.data.StorageUnitManItem
import com.artemis.storageunitman.databinding.StorageMenuItemBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class StorageMenuAdapter(
    options: FirebaseRecyclerOptions<StorageUnitManItem>,
    private val clickListener: StorageItemClickListener
    ): FirebaseRecyclerAdapter<StorageUnitManItem, StorageMenuAdapter.UnitViewHolder?>(options) {

    private lateinit var binding: StorageMenuItemBinding

    class UnitViewHolder(private var storageItems: StorageMenuItemBinding): RecyclerView.ViewHolder(storageItems.root) {
        fun bind(ctx: StorageUnitManItem, listener: StorageItemClickListener){
            storageItems.storageData = ctx
            storageItems.storageItemClick = listener
            storageItems.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val menuItemBinding = StorageMenuItemBinding.inflate(inflater, parent, false)
        binding = menuItemBinding
        return UnitViewHolder(menuItemBinding)
    }

    override fun onBindViewHolder(
        holder: UnitViewHolder,
        position: Int,
        model: StorageUnitManItem
    ) {
        holder.bind(model, clickListener)
    }
}