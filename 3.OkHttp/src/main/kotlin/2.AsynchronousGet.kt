import okhttp3.*
import java.io.IOException

fun main(){
    run()
}

//Асинхронное программирование - это использование колбеков
//В данной программе колбек реализован с помощью анонимного класса Callback
fun run() {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("http://publicobject.com/helloworld.txt")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                println(response.body!!.string())
            }
        }
    })
}