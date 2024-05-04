package br.com.itaucasebank.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.itaucasebank.presentation.screens.ExtractScreen
import br.com.itaucasebank.presentation.screens.HomeScreen
import br.com.itaucasebank.presentation.screens.InitialScreen
import br.com.itaucasebank.presentation.screens.LoginScreen
import br.com.itaucasebank.presentation.screens.SplashScreen
import br.com.itaucasebank.presentation.screens.TransferAreaScreen
import br.com.itaucasebank.presentation.screens.TransferConfirmationScreen
import br.com.itaucasebank.presentation.screens.TransferReceiptScreen
import br.com.itaucasebank.presentation.viewmodel.TransferSharedViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransparentStatusBar()

        val sharedViewModel: TransferSharedViewModel = get()
        setContent {
            ItauCaseBankTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.SPLASH.name
                ) {
                    composable(Route.SPLASH.name) {
                        SplashScreen(navController = navController)
                    }
                    composable(Route.INITIAL.name) {
                        InitialScreen(navController = navController)
                    }
                    composable(Route.LOGIN.name) {
                        LoginScreen(navController = navController)
                    }
                    composable(Route.HOME.name) {
                        HomeScreen(navController = navController)
                    }
                    composable(Route.EXTRACT.name) {
                        ExtractScreen(navController = navController)
                    }
                    composable(Route.TRANSFER_AREA.name) {
                        TransferAreaScreen(navController = navController, sharedViewModel)
                    }
                    composable(Route.TRANSFER_CONFIRMATION.name) {
                        TransferConfirmationScreen(navController = navController, sharedViewModel)
                    }
                    composable(Route.TRANSFER_RECEIPT.name) {
                        TransferReceiptScreen(navController = navController, sharedViewModel)
                    }
                }
            }
        }
    }

    private fun Activity.setupTransparentStatusBar() {
        window.apply {
            // Define a cor da barra de status como transparente
            statusBarColor = android.graphics.Color.TRANSPARENT
            // Configura as flags do sistema UI para que o conteúdo seja renderizado abaixo da barra de status
            decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ItauCaseBankTheme {
            Text(text = "Bem vindo")
        }
    }
}