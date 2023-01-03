package furkan.hello_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import furkan.hello_compose.ui.theme.APP_BG
import furkan.hello_compose.ui.theme.HellocomposeTheme
import furkan.hello_compose.ui.theme.TV_BLACK
import furkan.hello_compose.ui.theme.TV_WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HellocomposeTheme {
                MyAppNavHost()
            }
        }
    }
}

@Composable
fun HelloCompose(navController: NavController) {

    val mContext = LocalContext.current
    var text by remember { mutableStateOf("") }
    var textResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(APP_BG)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, true),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyTextView(text = "Hello Compose", color = TV_WHITE, 32.sp, fontWeight = FontWeight.ExtraBold)
            MyTextView(
                text = "What are your 2023 expectations?",
                color = TV_WHITE,
                24.sp,
                fontWeight = FontWeight.Normal
            )
            MyTextField(text) { text = it }
            Column() {
                MyButton("ADD", color = TV_BLACK) {

                    val count = textResult.filter {
                        it == '-'
                    }
                    if (count.length < 30 && text != "") {
                        textResult += "- $text - \n"
                        Toast.makeText(mContext, "Successfully added", Toast.LENGTH_SHORT).show()
                        return@MyButton
                    }
                    if (text == "") {
                        Toast.makeText(mContext, "Please enter a value", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(mContext, "You entered too many values", Toast.LENGTH_SHORT)
                            .show()
                        textResult = ""
                    }
                }
                MyButton("SHOW RESULT", color = TV_BLACK) {
                    if (textResult.isNotEmpty()) {
                        navController.navigate("${ScreenNames.RESULT_PAGE.name}/${textResult}")
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    HellocomposeTheme {
    }
}