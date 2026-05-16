package cordobot.example.myportfolioapp.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Terminal
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cordobot.example.myportfolioapp.R
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import cordobot.example.myportfolioapp.ui.theme.Emerald500

@Composable
fun TechScreen(viewModel: PortfolioViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Emerald500, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.Terminal, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
            Text("Tecnologías", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
        }

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Emerald500)
            }
        } else {
            // Agrupar en filas de 2 manualmente para evitar problemas de scroll anidado
            uiState.techSkills.chunked(2).forEach { rowSkills ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowSkills.forEach { skill ->
                        TechCard(
                            name = skill.name,
                            iconRes = skill.iconRes ?: R.drawable.ic_tech_kotlin,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowSkills.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun TechCard(name: String, iconRes: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = name,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
