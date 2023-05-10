package com.artemis.storageunitman.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.artemis.storageunitman.R
import com.artemis.storageunitman.adapters.StorageItemClickListener
import com.artemis.storageunitman.adapters.StorageMenuAdapter
import com.artemis.storageunitman.data.StorageUnitManItem
import com.artemis.storageunitman.databinding.FragmentStorageMenuBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class StorageMenuFragment : Fragment(), StorageItemClickListener {

    private var binding: FragmentStorageMenuBinding ?= null

    private lateinit var adapter: StorageMenuAdapter

    private var storageManDB: DatabaseReference ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val menuBinding = FragmentStorageMenuBinding.inflate(inflater, container, false)
        binding = menuBinding
        return menuBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storageManDB = FirebaseDatabase.getInstance().getReference("storage_unit")

        val options: FirebaseRecyclerOptions<StorageUnitManItem> =
            FirebaseRecyclerOptions.Builder<StorageUnitManItem>()
                .setQuery(storageManDB!!,StorageUnitManItem::class.java )
                .build()

        adapter = StorageMenuAdapter(options, this)
        binding?.apply {
            unitStorageAdapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClicked(storageUnitManItem: StorageUnitManItem) {
        for (units in 1 .. 20){
            if (units == 1){
                val action = StorageMenuFragmentDirections.actionStorageMenuFragmentToStorageDetailFragment(storageUnitManItem)
                findNavController().navigate(action)
                break
            }
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}