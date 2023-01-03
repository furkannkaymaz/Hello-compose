package furkan.hello_compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun MyTextView(text: String, color: Color, fontSize: TextUnit, fontWeight: FontWeight) {
    Text(
        text =text,
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
fun MyButton(text: String, color: Color, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(text, color = color)
    }

}