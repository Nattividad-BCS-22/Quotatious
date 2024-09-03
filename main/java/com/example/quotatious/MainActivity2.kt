package com.example.quotatious

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity2 : AppCompatActivity() {

    lateinit var btn : Button
    lateinit var tbox : TextView
    lateinit var tboxAuthor : TextView

    val client = OkHttpClient()
    val key = "api-key-goes-here"
    val request = Request.Builder()
        .url("https://api.api-ninjas.com/v1/quotes")
        .get()
        .addHeader("X-Api-Key", key)
        .build()

    fun main() {
        val gson = Gson()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                tbox.text = "Check your Internet Connectivity"
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {
                    var values = response.body!!.string()

                    values = values.substring(1,values.length-1)

                    var test = gson.fromJson(values,dataBank::class.java)
                    tbox.text = test.quote
                    tboxAuthor.text = "- ${test.author}"
                }
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btn = findViewById(R.id.btnQuote)
        tbox = findViewById(R.id.tbox)
        tboxAuthor = findViewById(R.id.tbox_Author)

        main()

        btn.setOnClickListener{
            btn.animate().apply {
                duration = 100
                scaleXBy(0.2f)
                scaleYBy(0.2f)
            }.withEndAction{
                btn.animate().apply {
                    duration = 100
                    scaleXBy(-0.2f)
                    scaleYBy(-0.2f)
                }
            }.start()
            main()
        }

    }

}

data class dataBank (
    @SerializedName("quote") var quote: String,
    @SerializedName("author") var author: String
)




