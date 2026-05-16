package cordobot.example.myportfolioapp.presentation.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import cordobot.example.myportfolioapp.ui.theme.Emerald500

@Composable
fun ExperienceScreen(viewModel: PortfolioViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // --- SECCIÓN ESPECIALIDADES ---
        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Emerald500.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.CheckCircle, contentDescription = null, tint = Emerald500, modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(12.dp))
                    Text("Especialidades", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(Modifier.height(16.dp))
                val specialties = listOf("Desarrollo Android Nativo", "UI Declarativa (Compose)", "Consumo de APIs REST", "Persistencia de Datos (Room)", "Testing (JUnit)")
                specialties.forEach { spec ->
                    Row(modifier = Modifier.padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(6.dp).background(Emerald500, CircleShape))
                        Spacer(Modifier.width(12.dp))
                        Text(spec, color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        // --- SECCIÓN EXPERIENCIA ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Box(Modifier.size(36.dp).background(Emerald500, RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                Icon(Icons.Rounded.Work, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(12.dp))
            Text("Experiencia Laboral", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
        }

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Emerald500)
            }
        } else {
            uiState.experience.forEachIndexed { index, exp ->
                ExperienceItem(
                    title = exp.title,
                    company = exp.company,
                    period = exp.dateRange,
                    description = exp.description,
                    isLast = index == uiState.experience.lastIndex
                )
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun ExperienceItem(title: String, company: String, period: String, description: String, isLast: Boolean) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(32.dp)) {
            Box(modifier = Modifier.size(12.dp).background(Emerald500, CircleShape).border(2.dp, Color.White, CircleShape))
            if (!isLast) {
                Box(modifier = Modifier.width(2.dp).weight(1f).background(Emerald500.copy(alpha = 0.2f)))
            }
        }
        
        Column(modifier = Modifier.padding(start = 12.dp, bottom = 24.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = company, style = MaterialTheme.typography.bodyMedium, color = Emerald500, fontWeight = FontWeight.Medium)
            Text(text = period, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(8.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 20.sp)
        }
    }
}
