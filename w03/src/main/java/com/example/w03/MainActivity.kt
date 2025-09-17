package com.example.w03

import android.R.attr.contentDescription
import android.R.attr.onClick
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w03.ui.theme.MYUITheme
import kotlin.contracts.contract


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYUITheme {
                HomeScreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title= { Text("ComposeLab",
                style = MaterialTheme.typography.headlineMedium)})
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "설명: 샤워하고 드라이룸에 들어가있음(매우억울함)",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Park Mi Ci",
                    style = MaterialTheme.typography.headlineMedium
                )
                Image(
                    painter = painterResource(id = R.drawable.mici),
                    contentDescription = "Jetpack Compose 로고",
                    modifier = Modifier
                        .size(400.dp)
                        .padding(16.dp)
                )
                Text(
                    "위치: 부천 옥길동",
                    style = MaterialTheme.typography.headlineLarge
                )
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                )
                {
                    Button(onClick = {}) {
                        Text("개견 정보")
                    }
                    Button(onClick = {}) {
                        Text("성별 확인")
                    }
                }
            }
        }
    )
}


@Preview(showBackground =  true)
@Composable
fun HomeScreenPreview(){ //github.com/pch-0417/mobileApp
    HomeScreen()
}