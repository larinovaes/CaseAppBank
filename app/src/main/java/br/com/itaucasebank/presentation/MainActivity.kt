package br.com.itaucasebank.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.itaucasebank.presentation.screens.ExtractScreen
import br.com.itaucasebank.presentation.screens.HomeScreen
import br.com.itaucasebank.presentation.screens.InitialScreen
import br.com.itaucasebank.presentation.screens.LoginScreen
import br.com.itaucasebank.presentation.screens.TransferAreaScreen
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.ItaucasebankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItaucasebankTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.INITIAL.name
                ) {
                    composable(Route.INITIAL.name) {
                        InitialScreen(navController)
                    }
                    composable(Route.LOGIN.name) {
                        LoginScreen(navController)
                    }
                    composable(Route.HOME.name) {
                        HomeScreen(navController)
                    }
                    composable(Route.EXTRACT.name) {
                        ExtractScreen(navController = navController)
                    }
                    composable(Route.TRANSFER_AREA.name) {
                        TransferAreaScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ItaucasebankTheme {
            Greeting("Android")
        }
    }
}