package com.munyroth.majorrecommendation.activity

import androidx.activity.compose.setContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.munyroth.majorrecommendation.model.Recommendation
import com.munyroth.majorrecommendation.ui.screens.ResultRecommendationScreen
import com.munyroth.majorrecommendation.ui.theme.AppTheme

class ResultRecommendationActivity : BaseActivity() {
    override fun init() {
        val gson = Gson()
        val json = intent.getStringExtra("data")
        val arrayType = object : TypeToken<List<Recommendation>>() {}.type
        val dataList: List<Recommendation> = gson.fromJson(json, arrayType)

        setContent {
            AppTheme {
                ResultRecommendationScreen(data = dataList)
            }
        }
    }
}