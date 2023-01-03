package furkan.hello_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import furkan.hello_compose.ui.theme.APP_BG
import furkan.hello_compose.ui.theme.TV_BLACK

@Composable
fun ResultScreen(navController: NavController, result: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(APP_BG),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTextView(text = result, color = Color.White, 22.sp, fontWeight = FontWeight.Black)
            MyButton(text = "BACK", TV_BLACK) { navController.navigate(ScreenNames.MAIN_PAGE.name) }
        }
    }
}