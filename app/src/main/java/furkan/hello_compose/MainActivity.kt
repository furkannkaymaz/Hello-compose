package furkan.hello_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import furkan.hello_compose.ui.theme.HellocomposeTheme
import furkan.hello_compose.ui.theme.TV_BLACK

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
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyTextView(text = "Hello Compose", color = TV_BLACK, 22.sp, fontWeight = FontWeight.Bold)
        MyTextView(
            text = "What are your 2023 expectations?",
            color = TV_BLACK,
            24.sp,
            fontWeight = FontWeight.Normal
        )
        MyTextField(text) { text = it }
        Column() {
            MyButton("ADD") {
                val count = textResult.filter {
                    it == '-'
                }

                if (count.length < 10 && text != "") {
                    textResult += "- $text - \n"
                    return@MyButton
                }
                Toast.makeText(mContext, "You entered too many values", Toast.LENGTH_SHORT).show()
                textResult = ""

            }
            MyButton("SHOW RESULT") {
                if (textResult.isNotEmpty()) {
                    navController.navigate("${ScreenNames.RESULT_PAGE.name}/${textResult}")
                } else {
                    Toast.makeText(mContext, "Please enter a value", Toast.LENGTH_SHORT).show()
                }
            }
        }
        MyTextView(text = textResult, color = TV_BLACK, 24.sp, fontWeight = FontWeight.SemiBold)
    }

}

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNames.MAIN_PAGE.name) {
        composable(ScreenNames.MAIN_PAGE.name) {
            HelloCompose(navController)
        }
        composable(
            "${ScreenNames.RESULT_PAGE.name}/{result}",
            arguments = listOf(navArgument(name = "result") { type = NavType.StringType })
        ) {
            val result = it.arguments?.getString("result")
            ResultScreen(navController, result.toString())
        }
    }
}

@Composable
fun MyTextView(text: String, color: Color, fontSize: TextUnit, fontWeight: FontWeight) {
    Text(
        text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextField(text: String, onValueChange: (String) -> Unit) {
    TextField(value = text, onValueChange = onValueChange, Modifier.fillMaxWidth())
}

@Composable
fun MyButton(text: String, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(text)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    HellocomposeTheme {
        //HelloCompose()
    }
}