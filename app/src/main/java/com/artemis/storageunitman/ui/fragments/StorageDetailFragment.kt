package com.artemis.storageunitman.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.findNavController
import com.artemis.storageunitman.R
import com.artemis.storageunitman.adapters.ImageUploader
import com.artemis.storageunitman.databinding.FragmentStorageDetailBinding
import com.artemis.storageunitman.manager.CloudStorageManager
import com.bumptech.glide.Glide


class StorageDetailFragment : Fragment() {

    private var binding: FragmentStorageDetailBinding ?= null

    private val cloudStorageManager by lazy { CloudStorageManager() }

    private val imageLoader by lazy { ImageUploader() }

    private lateinit var imageView: ImageView

    companion object {
        //private const val IMAGE_TYPE = "image/jpeg"
        @JvmStatic @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String){
            Glide.with(view).load(url).into(view)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val detailUnitBinding = FragmentStorageDetailBinding.inflate(inflater, container, false)
        binding = detailUnitBinding
        return detailUnitBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments

        if (bundle == null){
            Log.d("Confirmation", "Detail fragment didn't receive info")
            return
        }

        val detailArgs = bundle.let {
            StorageDetailFragmentArgs.fromBundle(it)
        }

        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            unitDetailFragment = this@StorageDetailFragment
            storageData = detailArgs.storageDetails
        }
        //initializeThings()

    }

   /* private fun initializeThings() {
        pickImages.launch(IMAGE_TYPE)
    }*/

    fun launchToHome(){
        findNavController().navigate(R.id.action_storageDetailFragment_to_storageHomeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //this was a simple way of uploading photos from phone to firebase storage.

  /*  private fun onImageUploadSuccess(url: String){
        imageLoader.loadImage(imageView, url)
    }

    private val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri -> uri?.let {selectedImageUri ->
            cloudStorageManager.uploadImages(selectedImageUri, ::onImageUploadSuccess)
        }
    }*/




}