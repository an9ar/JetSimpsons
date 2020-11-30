package com.an9ar.jetsimpsons.data

import android.content.res.AssetManager
import com.an9ar.jetsimpsons.Episode
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets


private val episodesList = ArrayList<Episode>()

suspend fun getEpisodesList(assets: AssetManager): List<Episode>{
    getEpisodesFromJsonData(assets = assets)
    return episodesList
}

private fun getJsonDataFromLocalFile(assets: AssetManager): String {
    var jsonString = ""
    jsonString = try {
        val inputStream: InputStream = assets.open("simpsons_episodes.json")
        val fileSize: Int = inputStream.available()
        val buffer = ByteArray(fileSize)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, StandardCharsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return ""
    }
    return jsonString
}

private suspend fun getEpisodesFromJsonData(assets: AssetManager) {
    try {
        val jsonObject = JSONObject(getJsonDataFromLocalFile(assets = assets))
        val array = jsonObject.getJSONArray("array")
        for (i in 0 until array.length()) {
            val episodeObject = array.getJSONObject(i)
            episodesList.add(
                Episode(
                    id = episodeObject.getLong("id"),
                    image_url = episodeObject.getString("image_url").replace("http","https"),
                    imdb_rating = episodeObject.getDouble("imdb_rating"),
                    imdb_votes = episodeObject.getLong("imdb_votes"),
                    number_in_season = episodeObject.getInt("number_in_season"),
                    number_in_series = episodeObject.getInt("number_in_series"),
                    original_air_date = episodeObject.getString("original_air_date"),
                    original_air_year = episodeObject.getInt("original_air_year"),
                    production_code = episodeObject.getString("production_code"),
                    season = episodeObject.getInt("season"),
                    title = episodeObject.getString("title"),
                    us_viewers_in_millions = episodeObject.getString("us_viewers_in_millions"),
                    video_url = episodeObject.getString("video_url").replace("http","https"),
                    views = episodeObject.getLong("views"),
                )
            )
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
}