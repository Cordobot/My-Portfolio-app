package cordobot.example.myportfolioapp.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cordobot.example.myportfolioapp.ui.theme.Emerald500
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1500)
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2500)
        onSplashComplete()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF020617)),
        contentAlignment = Alignment.Center
    ) {
        // Portal rings
        repeat(3) { index ->
            val infiniteTransition = rememberInfiniteTransition()
            
            Box(
                modifier = Modifier
                    .size(200.dp + (index * 40).dp)
                    .scale(scaleAnim)
                    .alpha(alphaAnim * (0.3f / (index + 1)))
                    .background(
                        brush = Brush.sweepGradient(listOf(Emerald500, Color.Transparent, Emerald500)),
                        shape = CircleShape
                    )
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.scale(scaleAnim).alpha(alphaAnim)) {
            Text(text = "ADRIÁN", fontSize = 42.sp, fontWeight = FontWeight.Black, color = Color.White, letterSpacing = 8.sp)
            Text(text = "ALVAREZ", fontSize = 42.sp, fontWeight = FontWeight.Black, color = Emerald500, letterSpacing = 8.sp)
            Spacer(Modifier.height(16.dp))
            Text(text = "ANDROID DEVELOPER", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.6f), letterSpacing = 4.sp)
        }
    }
}
