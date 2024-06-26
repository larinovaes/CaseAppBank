package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.LoadingComponent
import br.com.itaucasebank.components.TextComponent
import br.com.itaucasebank.presentation.viewmodel.LoginViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Black
import br.com.itaucasebank.ui.theme.ColorDivider
import br.com.itaucasebank.ui.theme.ColorInput
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.OrangePrimary
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    LoginScreen(
        emailValue = uiState.value.email,
        passwordValue = uiState.value.password,
        isLoading = uiState.value.isLoading,
        isCredentialErrorVisible = uiState.value.isCredentialErrorVisible,
        onEmailChanged = { viewModel.setEmail(it) },
        onPasswordChanged = { viewModel.setPassword(it) },
        onButtonAccessClicked = { viewModel.authenticate() },
        onButtonRegisterClicked = { },
    )
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                LoginViewModel.UiEvent.NavigateToHome -> navController.navigate(Route.HOME.name) {
                    popUpTo(0)
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun LoginScreen(
    emailValue: String,
    passwordValue: String,
    isLoading: Boolean,
    isCredentialErrorVisible: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonAccessClicked: () -> Unit,
    onButtonRegisterClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TitleAndSubtitle()
            Spacer(modifier = Modifier.height(44.dp))
            SectionInputFields(
                emailValue = emailValue,
                passwordValue = passwordValue,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged
            )
            if (isCredentialErrorVisible) {
                TextComponent(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    text = stringResource(id = R.string.login_screen_message_error),
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(150.dp))
            ButtonPrimaryComponent(
                text = stringResource(id = R.string.login_screen_title),
                icon = null,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                onClicked = onButtonAccessClicked
            )
            RegisterButton(onButtonRegisterClicked)
            Spacer(modifier = Modifier.height(24.dp))
            Divider(color = ColorDivider, thickness = 1.dp)
        }
        if (isLoading) LoadingComponent(isOverlayEnabled = isLoading)
    }

}

@Composable
private fun TitleAndSubtitle() {
    Column(
        modifier = Modifier
            .padding(top = 150.dp)
    ) {
        TextComponent(
            modifier = Modifier
                .padding(horizontal = 34.dp),
            text = stringResource(id = R.string.login_screen_title),
            fontSize = 24.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
            minLines = 1,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextComponent(
            modifier = Modifier
                .padding(horizontal = 34.dp),
            text = stringResource(id = R.string.login_screen_subtitle),
            color = Color.Gray,
            minLines = 1,
        )
    }
}

@Composable
private fun SectionInputFields(
    emailValue: String,
    passwordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailTextField(
            email = emailValue,
            onValueChange = onEmailChanged,
        )
        Spacer(modifier = Modifier.height(24.dp))

        PasswordTextField(
            password = passwordValue,
            onValueChange = onPasswordChanged,
        )
    }
}

@Composable
private fun EmailTextField(
    email: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = email,
        onValueChange = onValueChange,
        label = { TextComponent(text = stringResource(id = R.string.login_screen_email_password)) },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = OrangePrimary,
            unfocusedIndicatorColor = ColorInput,
            cursorColor = ColorInput,
            focusedLabelColor = OrangePrimary,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_icon_person),
                contentDescription = null,
                tint = Color.Black
            )
        }
    )
}

@Composable
private fun PasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 14.dp),
        value = password,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        label = {
            TextComponent(text = stringResource(id = R.string.login_screen_label_password))
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = OrangePrimary,
            unfocusedIndicatorColor = ColorInput,
            cursorColor = ColorInput,
            focusedLabelColor = OrangePrimary,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = null,
                tint = Color.Black
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) painterResource(id = R.drawable.ic_eye_close) else painterResource(
                        id = R.drawable.ic_eye
                    )
                val description = if (passwordHidden)
                    stringResource(id = R.string.login_screen_show_password)
                else stringResource(id = R.string.login_screen_hide_password)
                Image(
                    painter = visibilityIcon,
                    contentDescription = description,
                    modifier = Modifier.size(24.dp)
                )
            }
        })
}

@Composable
private fun RegisterButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextComponent(
            text = stringResource(id = R.string.login_screen_text_register),
            color = Color(0xFF171717).copy(alpha = 0.60F),
        )
        TextComponent(
            modifier = Modifier
                .clickable { onClick() }
                .padding(start = 4.dp),
            text = stringResource(id = R.string.login_screen_button_register),
            color = Color.Black,
        )
    }

}

@Preview
@Composable
private fun LoginPreview() {
    ItauCaseBankTheme {
        LoginScreen(
            emailValue = "larissa@email.com",
            passwordValue = "12345",
            isLoading = false,
            isCredentialErrorVisible = false,
            onEmailChanged = {},
            onPasswordChanged = {},
            onButtonAccessClicked = {},
            onButtonRegisterClicked = {}
        )
    }
}