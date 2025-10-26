package com.example.w06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.core.graphics.rotationMatrix
import com.example.w06.ui.theme.MYUITheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import java.text.CollationKey
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYUITheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    BubbleGameScreen()
                }
            }
        }
    }
}

data class Bubble(
    val id: Int,
    var position: Offset,
    val radius: Float,
    val color: Color,
    val creationTime: Long = System.currentTimeMillis(),
    val velocityX: Float = 0f,
    val velocityY: Float = 0f
)
@Composable
fun BubbleComposeable(bubble: Bubble, onClick: () -> Unit){
    Canvas(
        modifier = Modifier
            .size((bubble.radius * 2).dp)
            .offset(x = bubble.position.x.dp, y = bubble.position.y.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ){
        drawCircle(
            color = bubble.color,
            radius = size.width/2,
            center = center
        )
    }
}

@Composable
fun BubbleGameScreen(){
    var score by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(60) }
    var isGameOver by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isGameOver) {
        if(!isGameOver && timeLeft > 0){
            while(true){
                delay(1000L)
                timeLeft--
                if(timeLeft == 0){
                    isGameOver = true
                    break
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GameStatusRow(score = score, timeLeft = timeLeft)
            val newBubble = Bubble(
                id = Random.nextInt(),
                position = Offset(
                    x = Random.nextFloat(),
                    y = Random.nextFloat()
                ),
                radius = Random.nextFloat() * 50 + 50,
                color = Color(
                    red = Random.nextInt(256),
                    green = Random.nextInt(256),
                    blue = Random.nextInt(256),
                    alpha = 200
                )
            )
            BubbleComposeable(bubble = newBubble) {
                score++
            }
    }

}

@Composable
fun GameStatusRow(score: Int, timeLeft:Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text= "Score: $score", fontSize = 24.sp, fontWeight = FontWeight.Bold )
        Text(text= "Time: ${timeLeft}s", fontSize = 24.sp, fontWeight = FontWeight.Bold )
    }

}

@Preview(showBackground =  true)
@Composable
fun BubbleGamePreview(){
    MYUITheme {
        BubbleGameScreen()
    }
}