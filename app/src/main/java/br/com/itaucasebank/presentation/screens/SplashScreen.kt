package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.itaucasebank.R
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Blue
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.OrangePrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController = rememberNavController()
) {
    val fontFamily = FontFamily(Font(R.font.archivo_narrow_regular, FontWeight.Normal))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(110.dp),
                painter = painterResource(id = R.mipmap.ic_logo_itau),
                contentDescription = "Logo itau",
            )

            Spacer(modifier = Modifier.height(8.dp))

            CircularProgressIndicator(color = OrangePrimary)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                fontFamily = fontFamily,
                text = stringResource(id = R.string.init_screen_title),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onPrimary,
            )
        }
    }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        navController.navigate(Route.INITIAL.name) {
            popUpTo(0)
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    ItauCaseBankTheme {
        SplashScreen()
    }
}