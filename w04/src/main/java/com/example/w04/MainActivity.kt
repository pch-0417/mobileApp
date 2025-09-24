package com.example.w04

import android.R.attr.name
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.w04.ui.theme.MYUITheme
import kotlin.contracts.contract
import android.content.res.Configuration
import android.media.tv.TvContract
import android.provider.ContactsContract
import androidx.annotation.ColorInt
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import com.example.w04.ui.theme.PurpleGrey40
import java.nio.file.WatchEvent

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

data class Message( val author: String, val body: String)

data class Profile(val name: String, val intro: String)
@Composable
fun ProfileCard(data: Profile){
    Row(
        modifier = Modifier.padding(all =8.dp),
        verticalAlignment = Alignment.CenterVertically

    ){
        Image(
           painter = painterResource(R.drawable.jiu),
            contentDescription = "연락처 프로필 사진",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)

        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = data.name,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.intro,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            
        }
    }
}
@Composable
fun MessageCard(msg: Message) {
    Row(
        modifier = Modifier.padding(8.dp)
    ){
        Image(
            painter = painterResource(R.drawable.jiu),
            contentDescription = "엔믹스",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = msg.author,
                color = PurpleGrey40,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = msg.body,
                color = Green,
                style = MaterialTheme.typography.bodyMedium
            )




        }
    }

}
@Composable
fun HomeScreen() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
             MessageCard(Message("본영:김지우", "너무 귀엽자나~"))
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Button(oneClick={}){
                Text("상세 정보")
            }

        }
    }
    }




@Preview(
    name = "MessageCard Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewMessageCard() {
    MYUITheme {
        MessageCard(Message("김지우", "많이 매우 귀여움"))
    }
}