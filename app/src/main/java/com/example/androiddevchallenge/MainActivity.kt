/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.DogViewModel
import com.example.androiddevchallenge.ui.data.DogItem
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<DogViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.curDog != null) {
            viewModel.curDog = null
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun LoadDogs(dogList: List<DogItem>, onDogClick: (DogItem) -> Unit = {}) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        items(dogList) { dog ->
            DogItem(dog) {
                onDogClick(it)
            }
            Divider(
                color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
        }
    }
}

@Composable
fun DogItem(dogItem: DogItem, onDogClick: (DogItem) -> Unit = {}) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onDogClick(dogItem) })
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        val context = LocalContext.current
        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
        ) {
            Image(
                painterResource(id = dogItem.avatar), contentDescription = "dog avatar",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(dogItem.name, fontWeight = FontWeight.Bold)
            Text("Age:${dogItem.age}", Modifier.padding(5.dp), textAlign = TextAlign.Left)
            Text(
                "Dcrp:${dogItem.discription}",
                Modifier
                    .width(100.dp)
                    .padding(5.dp),
                textAlign = TextAlign.Left,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                fontStyle = FontStyle.Italic
            )
        }
        Button(
            onClick = {
                dogItem.isAdopted = true
                Toast.makeText(context, "Adopted", Toast.LENGTH_SHORT).show()
            },
            Modifier.width(IntrinsicSize.Max),
        ) {
            if (!dogItem.isAdopted) {
                Text("Take Home!", fontSize = 10.sp, textAlign = TextAlign.Center, maxLines = 1)
            } else {
                Text("Adopted!", fontSize = 10.sp, textAlign = TextAlign.Center, maxLines = 1)
            }
            Icon(
                painterResource(id = R.mipmap.like),
                contentDescription = "like",
                Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DogDetail(dogItem: DogItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        val context = LocalContext.current
        Image(
            painterResource(dogItem.avatar),
            "dog avatar ",
            Modifier.aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Text(dogItem.name, fontWeight = FontWeight.Bold)
        Text("Age:${dogItem.age}", Modifier.padding(5.dp), textAlign = TextAlign.Left)
        Text(
            "Description:${dogItem.discription}",
            Modifier
                .padding(5.dp),
            textAlign = TextAlign.Left,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )
        Button(
            onClick = {
                dogItem.isAdopted = true
                Toast.makeText(context, "Adopted", Toast.LENGTH_SHORT).show()
            },
            Modifier.width(IntrinsicSize.Max)
        ) {
            if (!dogItem.isAdopted) {
                Text("Take Home!", fontSize = 18.sp, textAlign = TextAlign.Center, maxLines = 1)
            } else {
                Text("Adopted!", fontSize = 18.sp, textAlign = TextAlign.Center, maxLines = 1)
            }
            Icon(
                painterResource(id = R.mipmap.like),
                contentDescription = "like",
                Modifier.size(20.dp)
            )
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Cute Dogs!", fontStyle = FontStyle.Italic) })
        }
    ) {
        val dogViewModel: DogViewModel = viewModel()
        LoadDogs(dogViewModel.dogList) {
            Log.v("TAG", "dog----")
            if (dogViewModel.curDog == null) {
                dogViewModel.curDog = it
            }
        }
        if (dogViewModel.curDog != null) {
            DogDetail(dogItem = dogViewModel.curDog!!)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
