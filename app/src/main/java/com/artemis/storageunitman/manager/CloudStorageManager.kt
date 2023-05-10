package com.artemis.storageunitman.manager

import android.net.Uri
import android.widget.ImageView
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlin.reflect.KFunction2


class CloudStorageManager {

    private val firebaseStorage by lazy { FirebaseStorage.getInstance() }

    fun uploadImages(selectedImageUri: Uri, onSuccessAction: (String) -> Unit){
        val imageReference = firebaseStorage.reference


        selectedImageUri.lastPathSegment?.let { slash ->
            val imageRef = imageReference.child(slash)

            imageRef.putFile(selectedImageUri)
                .continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                val exception = task.exception

                if (!task.isSuccessful && exception != null) {
                    throw exception
                }
                return@Continuation imageRef.downloadUrl
            }).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        onSuccessAction(downloadUri.toString())
                    }
                }
        }
    }

}