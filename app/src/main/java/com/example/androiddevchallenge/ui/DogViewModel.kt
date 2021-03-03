/*
*·Copyright·2021·The·Android·Open·Source·Project
*
*·Licensed·under·the·Apache·License,·Version·2.0·(the·"License");
*·you·may·not·use·this·file·except·in·compliance·with·the·License.
*·You·may·obtain·a·copy·of·the·License·at
*
*·····https://www.apache.org/licenses/LICENSE-2.0
*
*·Unless·required·by·applicable·law·or·agreed·to·in·writing,·software
*·distributed·under·the·License·is·distributed·on·an·"AS·IS"·BASIS,
*·WITHOUT·WARRANTIES·OR·CONDITIONS·OF·ANY·KIND,·either·express·or·implied.
*·See·the·License·for·the·specific·language·governing·permissions·and
*·limitations·under·the·License.
*/
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.data.DogItem

class DogViewModel : ViewModel() {
    val dogList = ArrayList<DogItem>()
    var curDog: DogItem? by mutableStateOf(null)
    init {
        dogList.add(DogItem("1", "Teddy", 2, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("2", "Blank", 1, R.mipmap.keji, "It is tame, friendly and easy to communicate with. I have kept one in my house, whose name is Chocolate. She is covered with brown curly hair and looks like a Teddy Bear. Now we have been living together for two years, so I’m very familiar with her characteristics. When she is hungry, she often barks loudly. At that time, I begin to realize that it’s time to feed her. She is keen on beef and fish. Sometimes, she also eats some rice."))
        dogList.add(DogItem("3", "Tim", 3, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("4", "Tom", 1, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("5", "Jerry", 2, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("6", "KaKa", 2, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("7", "Timmy", 1, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("8", "CoCo", 3, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("9", "Spike", 1, R.mipmap.keji, "A very cute baby"))
        dogList.add(DogItem("10", "Avenger", 2, R.mipmap.keji, "A very cute baby"))
    }
}