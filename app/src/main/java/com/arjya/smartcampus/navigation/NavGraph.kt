package com.arjya.smartcampus.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arjya.smartcampus.ui.screens.*

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Attendance : Screen("attendance", "Attendance", Icons.Default.CheckCircle)
    object Library : Screen("library", "Library", Icons.Default.MenuBook)
    object Events : Screen("events", "Events", Icons.Default.Event)
    object Notices : Screen("notices", "Notices", Icons.Default.Notifications)
    object Canteen : Screen("canteen", "Canteen", Icons.Default.Restaurant)
    object Transport : Screen("transport", "Transport", Icons.Default.DirectionsBus)
    object Complaints : Screen("complaints", "Complaints", Icons.Default.Report)
    object About : Screen("about", "About", Icons.Default.Info)
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Attendance,
    Screen.Library,
    Screen.Events,
    Screen.About
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartCampusApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = screen.title
                                )
                            },
                            label = {
                                Text(
                                    text = screen.title,
                                    fontWeight = if (currentRoute == screen.route) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(Screen.Home.route) {
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
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onFeatureClick = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.Attendance.route) {
                AttendanceScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Library.route) {
                LibraryScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Events.route) {
                EventsScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Notices.route) {
                NoticesScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Canteen.route) {
                CanteenScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Transport.route) {
                TransportScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Complaints.route) {
                ComplaintsScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.About.route) {
                AboutScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
