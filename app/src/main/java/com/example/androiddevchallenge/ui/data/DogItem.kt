package com.example.androiddevchallenge.ui.data

import androidx.annotation.DrawableRes

data class DogItem(
    val id: String,
    val name: String,
    val age: Int,
    @DrawableRes val avatar: Int,
    var discription: String
) {
    var isAdopted: Boolean = false
}
