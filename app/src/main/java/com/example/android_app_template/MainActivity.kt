package com.example.android_app_template

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.android_app_template.ui.theme.AndroidapptemplateTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
                    NetworkTestokhttp3Button()
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

    // 使用Box布局，使内容居中显示
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // 打开画廊的按钮，向上偏移50dp
        Button(
            onClick = { imagePicker.launch("image/*") },
            modifier = Modifier.offset(y = -50.dp)
        ) {
            Text("打開相簿")
        }

        // 如果有選擇圖片，則顯示圖片
        imageUri?.let { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = "選擇的圖片",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun NetworkTestokhttp3Button() {
    val coroutineScope = rememberCoroutineScope()

    // 使用Box容器来指定按钮的大小並使其居中
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp) // 為Box四周添加一些外邊距，可選
            .size(width = 200.dp, height = 48.dp) // 直接設置Box的大小
    ) {
        // 點擊以發起網絡請求的按鈕
        Button(
            onClick = {
                coroutineScope.launch {
                    // 確保 NetworkUtils 實例被正確初始化或引用
                    val networkUtils = NetworkUtils() // 假設你有這樣一個實例
                    val response = withContext(Dispatchers.IO) {
                        networkUtils.httpRequestWithokhttp3("https://openapi.twse.com.tw/v1/opendata/t187ap14_L")
                    }
                    println(response.toString()) // 確保調用 toString() 以避免歧義
                }
            }
        ) {
            Text("okhttp3")
        }
    }
}