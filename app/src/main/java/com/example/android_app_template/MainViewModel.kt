import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainViewModel : ViewModel() {
    companion object {
        private const val TAG = "MainViewModel"
    }

    private val client = OkHttpClient()

    fun httpRequestWithOkHttp3(url: String) {
        viewModelScope.launch {
            val responseString = withContext(Dispatchers.IO) {
                httpRequest(url)
            }
            // 打印响应内容
            Log.d(TAG, "Response: $responseString")
        }
    }

    private suspend fun httpRequest(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()

        // 使用 OkHttp 发起请求
        val response: Response = client.newCall(request).execute()

        // 处理响应
        return if (response.isSuccessful) {
            response.body?.string() ?: "No Response"
        } else {
            "Failed to execute request"
        }
    }
}
