package com.example.w05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w05.ui.theme.MYUITheme
import java.lang.String.format

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYUITheme {
                val count = remember { mutableStateOf(0)}
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    CounterApp(count)
                    Spacer(modifier = Modifier.height(32.dp))
                    StopWatchApp()
                }
            }
        }
    }
}
@Composable
fun CounterApp(count : MutableState<Int>){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Count: ${count.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Row{
            Button(onClick = {count.value++}){
                Text("Increase")
            }
            Button(onClick = {count.value = 0}) {
                Text("Reset")
            }
        }
    }
}

@Composable
fun StopWatchApp(){
    var timelnMillis by remember {mutableStateOf(1234L)}
    var isRunning by remember {mutableStateOf(false)}
    LaunchedEffect(isRunning) {
        if (isRunning){
            while(true){
                delay(10L)
                timelnMillis += 10L
            }
        }
    }
    StopWatchScreen(
        timelnMillis = timelnMillis,
        onStartClick = {isRunning = true},
        onStopClick = {isRunning = false},
        onResetClick = {
            isRunning = false
            timelnMillis = 0L
        }
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = formatTime(timelnMillis),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Row{
            Button(onClick = {isRunning = true}) { Text("Start")}
            Button(onClick = {isRunning= false}) { Text("Stop")}
            Button(onClick = {
                isRunning = false
                timelnMillis = 0L
            }) { Text("Reset")}
        }
    }
}
private fun formatTime(timelnMillis: Long): String{
    val minutes = (timelnMillis / 1000) / 60
    val seconds = (timelnMillis / 1000) % 60
    val millis= (timelnMillis % 1000) /10
    return String.format("%02d:%02d:%02d", minutes, seconds, millis)
}

@Composable
fun StopWatchScreen(
    timelnMillis: Long,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit,
    onResetClick: () -> Unit

){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = formatTime(timelnMillis),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Button(onClick = onStartClick) {Text("Start") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) {Text("Stop") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onResetClick) {Text("Reset") }

        }

    }
}
@Preview(showBackground =  true)
@Composable
fun CounterAppPreview(){
    val count = remember { mutableStateOf(0)}
    CounterApp(count)
}
@Preview(showBackground =  true)
@Composable
fun StopWatchPerview(){
    StopWatchApp()
}

