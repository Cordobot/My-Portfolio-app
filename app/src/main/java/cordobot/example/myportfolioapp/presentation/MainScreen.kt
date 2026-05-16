package cordobot.example.myportfolioapp.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cordobot.example.myportfolioapp.presentation.screens.*
import cordobot.example.myportfolioapp.ui.theme.*
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Inicio", Icons.Rounded.Person)
    object Tech : Screen("tech", "Stack", Icons.Rounded.Memory)
    object Experience : Screen("experience", "Experiencia", Icons.Rounded.Work)
    object Projects : Screen("projects", "Proyectos", Icons.Rounded.Code)
    object Contact : Screen("contact", "Contacto", Icons.Rounded.Email)
}

val navItems = listOf(Screen.Home, Screen.Tech, Screen.Experience, Screen.Projects, Screen.Contact)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: PortfolioViewModel = koinViewModel()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 0.dp,
                modifier = Modifier
                    .shadow(elevation = 16.dp, spotColor = Color.Black.copy(alpha = 0.3f))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItems.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title,
                                modifier = Modifier.size(if (selected) 26.dp else 22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        selected = selected,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Emerald500,
                            selectedTextColor = Emerald500,
                            indicatorColor = Emerald500.copy(alpha = 0.12f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        onClick = {
                            if (currentDestination?.route != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { 
                HomeScreen(viewModel, onNavigateToProjects = { 
                    navController.navigate(Screen.Projects.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) 
            }
            composable(Screen.Tech.route) { TechScreen(viewModel) }
            composable(Screen.Experience.route) { ExperienceScreen(viewModel) }
            composable(Screen.Projects.route) { ProjectsScreen(viewModel) }
            composable(Screen.Contact.route) { ContactScreen(viewModel) }
        }
    }
}
