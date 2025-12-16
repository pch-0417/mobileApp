package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RandomPickerApp()
                }
            }
        }
    }
}

@Composable
fun RandomPickerApp() {
    var inputText by remember { mutableStateOf("") }
    val optionList = remember { mutableStateListOf<String>() }
    var showDialog by remember { mutableStateOf(false) }
    var pickedResult by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text =  "책임 없는 결정 ",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("후보 입력 (예: 치킨)") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        optionList.add(inputText)
                        inputText = "" // 입력창 비우기
                    }
                }
            ) {
                Text("추가")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        LazyColumn(
            modifier = Modifier
                .weight(1f) // 남은 공간 꽉 채우기
                .fillMaxWidth()
        ) {
            items(optionList) { option ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = option, fontSize = 18.sp)
                        IconButton(onClick = { optionList.remove(option) }) {
                            Icon(Icons.Default.Delete, contentDescription = "삭제", tint = Color.Gray)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (optionList.isNotEmpty()) {
                    pickedResult = optionList.random() // 랜덤 뽑기
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6200))
        ) {
            Text("하나만 골라줘! (랜덤)", fontSize = 20.sp)
        }
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "당신의 책임이 0%인 결정") },
            text = {
                Text(
                    text = "결과 \n\n $pickedResult ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("확인")
                }
            }
        )
    }
}