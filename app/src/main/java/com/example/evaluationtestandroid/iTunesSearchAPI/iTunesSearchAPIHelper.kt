package com.example.evaluationtestandroid.iTunesSearchAPI

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.evaluationtestandroid.models.AlbumModel
import com.example.evaluationtestandroid.models.SongModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

const val BASE_URL = "https://itunes.apple.com/search?"
const val BASE_LOOKUP_URL = "https://itunes.apple.com/lookup?"

const val MEDIA_MUSIC = "music"

const val ENTITY_ALBUM = "album"
const val ENTITY_TRACK = "song"

fun getAlbums(
    search: String,
    function: (dataList: MutableList<AlbumModel>) -> Unit
) {
    val searchRequest = search.toLowerCase(Locale.ENGLISH).replace(' ', '+')
    val dataList = mutableListOf<AlbumModel>()
    //make url
    val url = BASE_URL +
            "term=$searchRequest" +
            "&media=${MEDIA_MUSIC}" +
            "&entity=${ENTITY_ALBUM}" +
            "&limit=200"
    val okHttpClient = OkHttpClient()
    val request = Request.Builder().url(url).build()
    //call request
    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("getAlbums", e.message.toString())
            return
        }

        //get data
        override fun onResponse(call: Call, response: Response) {
            val json = response.body()?.string()
            if (json.isNullOrEmpty())
                return
            val results = JSONObject(json).getJSONArray("results")
            for (i in 0 until results.length()) {
                val obj = results.getJSONObject(i)
                dataList.add(
                    AlbumModel(
                        obj.getInt("collectionId"),
                        obj.getString("collectionName"),
                        obj.getString("artistName"),
                        obj.getString("primaryGenreName"),
                        obj.getString("artworkUrl100"),
                        obj.getInt("trackCount")
                    )
                )
            }
            // refer datalist to UI thread
            Handler(Looper.getMainLooper()).post {
                kotlin.run {
                    function(dataList)
                }
            }
        }
    })
}

fun getSongs(album: AlbumModel, function: (dataList: MutableList<SongModel>) -> Unit) {
    val dataList = mutableListOf<SongModel>()
    // make lookup url to find songs by album's id
    val url = "${BASE_LOOKUP_URL}id=${album.id}" +
            "&media=${MEDIA_MUSIC}" +
            "&entity=${ENTITY_TRACK}" +
            "&limit=${album.songsCount}"
    val okHttpClient = OkHttpClient()
    val request = Request.Builder().url(url).build()
    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("getSongs", e.message.toString())
            return
        }

        override fun onResponse(call: Call, response: Response) {
            val json = response.body()?.string()
            if (json.isNullOrEmpty())
                return
            val results = JSONObject(json).getJSONArray("results")
            for (i in 0 until results.length()) {
                val obj = results.getJSONObject(i)
                if (obj.getString("wrapperType") == "track") {
                    dataList.add(
                        SongModel(
                            obj.getString("trackName"),
                            obj.getString("artistName"),
                            obj.getString("collectionName"),
                            obj.getString("trackTimeMillis"),
                            obj.getString("artworkUrl100"),
                            obj.getInt("trackNumber")
                        )
                    )
                }
            }
            Handler(Looper.getMainLooper()).post {
                kotlin.run {
                    function(dataList)
                }
            }
        }
    })
}