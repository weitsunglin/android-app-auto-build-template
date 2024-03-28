package com.example.android_app_template

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import android.provider.MediaStore

class ImagePicker(private val context: Context, private val resultLauncher: ActivityResultLauncher<Intent>) {

    interface ImagePickerListener {
        fun onImagePicked(imageUri: String)
    }

    var listener: ImagePickerListener? = null

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.resolveActivity(context.packageManager)?.let {
            resultLauncher.launch(intent)
        }
    }
}
