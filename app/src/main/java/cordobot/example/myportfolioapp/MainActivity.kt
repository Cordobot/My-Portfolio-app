package cordobot.example.myportfolioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import cordobot.example.myportfolioapp.presentation.MainScreen
import cordobot.example.myportfolioapp.presentation.SplashScreen
import cordobot.example.myportfolioapp.ui.theme.MyPortfolioAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPortfolioAppTheme {
                var showSplash by remember { mutableStateOf(true) }
                
                if (showSplash) {
                    SplashScreen(onSplashComplete = { showSplash = false })
                } else {
                    MainScreen()
                }
            }
        }
    }
}