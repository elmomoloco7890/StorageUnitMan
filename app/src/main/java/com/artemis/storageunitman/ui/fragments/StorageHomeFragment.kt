package com.artemis.storageunitman.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.artemis.storageunitman.R
import com.artemis.storageunitman.databinding.FragmentStorageHomeBinding


class StorageHomeFragment : Fragment() {

    private var binding: FragmentStorageHomeBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val homeUnitBinding = FragmentStorageHomeBinding.inflate(inflater, container, false)
        binding = homeUnitBinding
        return homeUnitBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            unitHomeFragment = this@StorageHomeFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun launchToMenu(){
        findNavController().navigate(R.id.action_storageHomeFragment_to_storageMenuFragment)
    }


}