package com.example.w01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w01.ui.theme.MYUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYUITheme {

                    MainScreen()

            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold (modifier = Modifier.fillMaxSize()){ innerPadding ->
        Column (modifier = Modifier.fillMaxSize().padding(32.dp)){
            Text(
                text = "Hello Compose",
                modifier = Modifier
                    .padding(innerPadding)
                    .border(width = 2.dp, Color.Red)
                    .padding(12.dp)
                    .background(Green)
            )
            Text(
                text = "I'm tired",
                modifier = Modifier
                    .padding(16.dp)

            )
            Row {
                Text("this is left." , modifier = Modifier.weight(1f))
                Text("this is right." , modifier = Modifier.weight(1f))

            }
            Button (onClick = {}){
                Text("Click me!!")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    MYUITheme {
        MainScreen()
    }
}