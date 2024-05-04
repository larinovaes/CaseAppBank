package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.TextComponent
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Blue
import br.com.itaucasebank.ui.theme.Brown
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.GrayLight
import br.com.itaucasebank.ui.theme.OrangePrimary

@Composable
fun InitialScreen(
    navController: NavController = rememberNavController()
) {
    InitialScreen(
        onButtonAccessClicked = { navController.navigate(Route.LOGIN.name) }
    )
}

@Composable
private fun InitialScreen(
    onButtonAccessClicked: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CircleComponent()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InitialTitle()
            Spacer(modifier = Modifier.height(34.dp))
            InitialContent()
            Spacer(modifier = Modifier.height(24.dp))
            ButtonPrimaryComponent(
                text = stringResource(id = R.string.init_screen_button),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                icon = R.drawable.ic_arrow,
                onClicked = onButtonAccessClicked
            )
        }
    }
}

@Composable
private fun CircleComponent() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
    ) {
        drawCircle(
            color = Brown,
            center = Offset(x = size.width * 0.0f, y = size.height * 0.3f),
            radius = size.minDimension * 0.3f,
        )

        drawCircle(
            color = OrangePrimary,
            center = Offset(x = size.width * 0.8f, y = size.height * 0.1f),
            radius = size.minDimension * 0.54f,
        )
    }
}

@Composable
private fun InitialTitle() {
    val fontFamily = FontFamily(Font(R.font.archivo_narrow_regular, FontWeight.Normal))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = stringResource(id = R.string.init_screen_content_description_logo),
            Modifier.size(110.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        TextComponent(
            fontFamily = fontFamily,
            text = stringResource(id = R.string.init_screen_title),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}

@Composable
private fun InitialContent() {
    TextComponent(
        text = stringResource(id = R.string.init_screen_subtitle),
        color = MaterialTheme.colors.onPrimary,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(34.dp))

    TextComponent(
        text = stringResource(id = R.string.init_screen_text),
        color = GrayLight,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        modifier = Modifier.padding(horizontal = 24.dp),
    )
}

@Preview
@Composable
private fun SplashScreenPreview() {
    ItauCaseBankTheme {
        InitialScreen()
    }
}