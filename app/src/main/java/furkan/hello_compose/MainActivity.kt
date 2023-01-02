package furkan.hello_compose

import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import furkan.hello_compose.ui.theme.HellocomposeTheme
import furkan.hello_compose.ui.theme.TV_BLACK

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HellocomposeTheme {
                HelloCompose()
            }
        }
    }
}

@Composable
fun HelloCompose() {

    val mContext = LocalContext.current
    var text by remember { mutableStateOf("Compose") }
    var textResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextView(text = "Hello Compose", color = TV_BLACK, 22.sp)
        MyTextView(text = "What are your 2023 expectations?", color = TV_BLACK, 24.sp)
        MyTextField(text) { text = it }
        MyButton("ADD") {
            val count = textResult.filter {
                it == ','
            }
            if (count.length < 5) {
                textResult += "$text , \n"
                return@MyButton
            }
            Toast.makeText(mContext, "You entered too many values", Toast.LENGTH_SHORT).show()
            textResult = ""

        }
        MyTextView(text = textResult, color = TV_BLACK, 24.sp)
        Log.d("text123", text.toString())
    }

}

@Composable
fun MyTextView(text: String, color: Color, fontSize: TextUnit) {
    Text(text, color = color, fontSize = fontSize)
}

@Composable
fun MyTextField(text: String, onValueChange: (String) -> Unit) {
    TextField(value = text, onValueChange = onValueChange)
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
        HelloCompose()
    }
}