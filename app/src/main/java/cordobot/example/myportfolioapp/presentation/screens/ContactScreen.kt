package cordobot.example.myportfolioapp.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cordobot.example.myportfolioapp.presentation.ContactState
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import cordobot.example.myportfolioapp.ui.theme.Emerald500

@Composable
fun ContactScreen(viewModel: PortfolioViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

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
                Icon(Icons.Rounded.Email, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            }
            Text("Contacto", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                val textFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Emerald500,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedBorderColor = Emerald500,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )

                OutlinedTextField(
                    value = name, onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = textFieldColors
                )
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = textFieldColors
                )
                OutlinedTextField(
                    value = message, onValueChange = { message = it },
                    label = { Text("Mensaje") },
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = textFieldColors
                )

                Button(
                    onClick = { viewModel.sendMessage(name, email, message) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Emerald500),
                    enabled = uiState.contactState !is ContactState.Loading && name.isNotBlank() && email.isNotBlank()
                ) {
                    if (uiState.contactState is ContactState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                    } else {
                        Icon(Icons.Rounded.Send, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.Black)
                        Spacer(Modifier.width(8.dp))
                        Text("Enviar Mensaje", fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }

                when (val state = uiState.contactState) {
                    is ContactState.Success -> {
                        Text("¡Mensaje enviado con éxito!", color = Emerald500, fontWeight = FontWeight.Bold)
                        LaunchedEffect(Unit) {
                            name = ""; email = ""; message = ""
                            viewModel.resetContactState()
                        }
                    }
                    is ContactState.Error -> {
                        Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                    }
                    else -> {}
                }
            }
        }
    }
}
