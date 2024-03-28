package com.example.android_app_template

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.example.android_app_template.ui.theme.AndroidapptemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidapptemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryImagePicker()
                }
            }
        }
    }
}

@Composable
fun GalleryImagePicker() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { imagePicker.launch("image/*") }) {
            Text("Open Gallery")
        }
        imageUri?.let { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = "Selected Image",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
