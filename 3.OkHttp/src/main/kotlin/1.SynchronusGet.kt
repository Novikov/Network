import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun main(){
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            //печать заголовков ответа
            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            //возврат ответа от сервера в виде строки
            println(response.body!!.string())
        }

}