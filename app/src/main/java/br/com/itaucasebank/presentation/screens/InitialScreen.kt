package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Blue
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.LightGray

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue)
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

@Composable
private fun InitialTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = stringResource(id = R.string.init_screen_content_description_logo),
            Modifier.size(110.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = stringResource(id = R.string.init_screen_title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}

@Composable
private fun InitialContent() {
    Text(
        text = stringResource(id = R.string.init_screen_subtitle),
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onPrimary,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.height(34.dp))

    Text(
        text = stringResource(id = R.string.init_screen_text),
        color = LightGray,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 24.dp),
    )
}

@Preview
@Composable
private fun SplashScreenPreview() {
    ItaucasebankTheme {
        InitialScreen()
    }
}