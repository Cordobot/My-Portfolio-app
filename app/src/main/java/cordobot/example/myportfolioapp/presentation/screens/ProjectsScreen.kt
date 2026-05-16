package cordobot.example.myportfolioapp.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cordobot.example.myportfolioapp.R
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import cordobot.example.myportfolioapp.ui.theme.Emerald500

@Composable
fun ProjectsScreen(viewModel: PortfolioViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
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
                Icon(Icons.Rounded.Code, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
            Text("Proyectos", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
        }

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Emerald500)
            }
        } else {
            uiState.projects.chunked(2).forEach { rowProjects ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowProjects.forEach { project ->
                        ProjectSmallCard(
                            title = project.title,
                            githubUrl = project.githubUrl,
                            tags = project.tags,
                            imageRes = when(project.title) {
                                "Mallero" -> R.drawable.project_mallero
                                "Arbarrio" -> R.drawable.project_mallero
                                else -> R.drawable.project_portfolio
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowProjects.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun ProjectSmallCard(title: String, githubUrl: String, tags: List<String>, imageRes: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        Column {
            Image(
                painter = painterResource(imageRes),
                contentDescription = title,
                modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    tags.take(2).forEach { tag ->
                        Text(text = tag, style = MaterialTheme.typography.labelSmall, color = Emerald500, fontSize = 9.sp)
                    }
                }
                Spacer(Modifier.height(12.dp))
                IconButton(
                    onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))) },
                    modifier = Modifier.size(32.dp).align(Alignment.End).background(Color.White.copy(alpha = 0.1f), CircleShape)
                ) {
                    Icon(painterResource(R.drawable.ic_github), contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.White)
                }
            }
        }
    }
}
