    package com.example.w06

    import android.annotation.SuppressLint
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
    import androidx.compose.ui.layout.LookaheadScope
    import androidx.compose.ui.platform.LocalDensity
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

    class GameState(
        internalBubbles: List<Bubble> = emptyList()
    ){
        var bubbles by mutableStateOf(internalBubbles)
        var score by mutableStateOf(0)
        var isGameOver by mutableStateOf(false)
        var timeLeft by mutableStateOf(60)
    }

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

    @SuppressLint("UnusedBoxWithConstraintsScope")
    @Composable
    fun BubbleGameScreen() {
        var gameState = remember { GameState() }


        LaunchedEffect(gameState.isGameOver) {
            if (!gameState.isGameOver && gameState.timeLeft > 0) {
                while (true) {
                    delay(1000L)
                    gameState.timeLeft--
                    if (gameState.timeLeft == 0) {
                        gameState.isGameOver = true
                        break
                    }
                    val currentTime = System.currentTimeMillis()
                    gameState.bubbles = gameState.bubbles.filter{
                        currentTime - it.creationTime < 3000
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            GameStatusRow(score = gameState.score, timeLeft = gameState.timeLeft)
            BoxWithConstraints {
                val density = LocalDensity.current
                val canvasWidthPx = with(density) { maxWidth.toPx() }
                val canvasHeightPx = with(density) { maxHeight.toPx() }

                LaunchedEffect(key1 = gameState.isGameOver) {
                    if (!gameState.isGameOver) {
                        while (true) {
                            delay(16)
                            if (gameState.bubbles.isEmpty()) {
                                val newBubbles = List(3) {
                                    makeNewBubble(maxWidth, maxHeight)
                                }
                                gameState.bubbles = newBubbles
                            }
                        }
                    }
                    gameState.bubbles.forEach { bubble ->
                        BubbleComposeable(bubble = bubble) {
                            gameState.score++
                            gameState.bubbles=
                                gameState.bubbles.filterNot {it.id == bubble.id}
                        }
                    }
                }
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