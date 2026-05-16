package cordobot.example.myportfolioapp.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cordobot.example.myportfolioapp.R
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import cordobot.example.myportfolioapp.ui.theme.*

@Composable
fun HomeScreen(viewModel: PortfolioViewModel, onNavigateToProjects: () -> Unit) {
    val context = LocalContext.current
    
    // Animation states
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { startAnimation = true }

    val infiniteTransition = rememberInfiniteTransition()
    val floatAnim by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 15f,
        animationSpec = infiniteRepeatable(tween(2000, easing = EaseInOutSine), RepeatMode.Reverse)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))

        // Profile Image with Emerald Border (Using your new photo)
        Box(
            modifier = Modifier
                .offset(y = floatAnim.dp)
                .size(200.dp)
                .background(
                    Brush.linearGradient(listOf(Emerald400, Emerald600)),
                    RoundedCornerShape(60.dp)
                )
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.hero_avatar),
                contentDescription = "Adrián Alvarez",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(56.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(32.dp))

        // Status Badge
        Surface(
            color = Emerald500.copy(alpha = 0.1f),
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(1.dp, Emerald500.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(8.dp).background(Emerald500, CircleShape))
                Spacer(Modifier.width(8.dp))
                Text(
                    "Disponible para proyectos Android",
                    color = Emerald700,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Name & Role
        Text(
            text = "Adrián Alvarez",
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 36.sp),
            fontWeight = FontWeight.Black,
            color = Emerald600
        )
        Text(
            text = "Android Developer.",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Ingeniero de Software enfocado en el desarrollo de aplicaciones móviles eficientes, modernas y escalables. Especialista en el ecosistema Android con Kotlin y arquitecturas robustas.",
            style = MaterialTheme.typography.bodyMedium.copy(
                lineHeight = 22.sp,
                letterSpacing = 0.5.sp
            ),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(Modifier.height(40.dp))

        // Main Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onNavigateToProjects,
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Emerald600)
            ) {
                Text("Proyectos", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/573003003030")) // Actualizar con tu número real
                    context.startActivity(intent)
                },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366))
            ) {
                Icon(painterResource(R.drawable.ic_whatsapp), contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("WhatsApp", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Social Links
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Cordobot"))) }) {
                Icon(painterResource(R.drawable.ic_github), contentDescription = "GitHub", modifier = Modifier.size(32.dp))
            }
            IconButton(onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/adrian-alvarez"))) }) {
                Icon(painterResource(R.drawable.ic_linkedin), contentDescription = "LinkedIn", modifier = Modifier.size(32.dp), tint = Color(0xFF0A66C2))
            }
            IconButton(onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/users/your-id"))) }) {
                Icon(painterResource(R.drawable.ic_stackoverflow), contentDescription = "StackOverflow", modifier = Modifier.size(32.dp), tint = Color(0xFFF48024))
            }
        }
    }
}
