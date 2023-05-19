package com.example.fooddeliverycompose.data

import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes

data class CategoryModel(
    @DrawableRes val restId: Int,
    val title: String,
    val isSelected: Boolean = false
)
