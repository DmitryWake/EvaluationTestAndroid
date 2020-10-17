package com.example.evaluationtestandroid.iTunesSearchAPI

import android.os.Handler
import android.os.Looper
import com.example.evaluationtestandroid.models.AlbumModel
import com.example.evaluationtestandroid.screens.MainScreen.MainAdapter
import com.example.evaluationtestandroid.screens.MainScreen.MainFragment
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*


//Запускается в отдельном потоке
fun getAlbums(searchRequest: String, adapter: MainAdapter, count: Int) {
    val dataList = mutableListOf<AlbumModel>()
    val url =
        "https://itunes.apple.com/search?term=${
            searchRequest.toLowerCase(Locale.ENGLISH).replace(' ', '+')
        }&media=music&entity=album&attribute=ratingIndex&limit=$count"
    val okHttpClient = OkHttpClient()
    val request = Request.Builder().url(url).build()
    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
        }

        override fun onResponse(call: Call, response: Response) {
            val json = response.body()?.string()
            val results = JSONObject(json).getJSONArray("results")
            for (i in 0 until results.length()) {
                val obj = results.getJSONObject(i)
                dataList.add(
                    AlbumModel(
                        obj.getString("collectionName"),
                        obj.getString("artistName"),
                        obj.getString("primaryGenreName"),
                        obj.getString("artworkUrl100")
                    )
                )
            }
            Handler(Looper.getMainLooper()).post {
                kotlin.run {
                    adapter.changeData(dataList)
                }
            }
        }
    })
}