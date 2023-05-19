package com.example.fooddeliverycompose.utils

import com.example.fooddeliverycompose.R
import com.example.fooddeliverycompose.data.CategoryModel
import com.example.fooddeliverycompose.data.PopularModel

object FakeData {
    val categoryFakeData = listOf(
        CategoryModel(R.drawable.pizza, "Pizza", isSelected = true),
        CategoryModel(R.drawable.hamburger, "hamburger", isSelected = true),
        CategoryModel(R.drawable.drinks, "drinks", isSelected = true),
        CategoryModel(R.drawable.pizza, "Pizza", isSelected = true),
        CategoryModel(R.drawable.hamburger, "hamburger", isSelected = true),
        CategoryModel(R.drawable.drinks, "drinks", isSelected = true)
    )
    val popularFakeDara = listOf(
        PopularModel(
            R.drawable.salad_pesto_pizza,
            title = "Salad Pesto Pizza",
            description = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.",
            price = 10.55,
            calori = 540.0,
            scheduleTime = 20.0,
            rate = 5.0,
            ingradients = listOf(
                R.drawable.ing1,
                R.drawable.ing2,
                R.drawable.ing3,
                R.drawable.ing4,
                R.drawable.ing5,
            )
        ),
        PopularModel(
            R.drawable.primavera_pizza,
            title = "Primavera Pizza",
            description = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.",
            price = 12.55,
            calori = 440.0,
            scheduleTime = 30.0,
            rate = 4.5,
            ingradients = listOf(
                R.drawable.ing1,
                R.drawable.ing2,
                R.drawable.ing3,
                R.drawable.ing4,
                R.drawable.ing5,
            )
        )
    )
}