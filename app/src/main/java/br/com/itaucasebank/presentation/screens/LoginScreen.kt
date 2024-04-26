package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimary
import br.com.itaucasebank.presentation.LoginViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Black
import br.com.itaucasebank.ui.theme.ColorDivider
import br.com.itaucasebank.ui.theme.ColorInput
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Orange
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
        onEmailChanged = { viewModel.setEmail(it) },
        onPasswordChanged = { viewModel.setPassword(it) },
        onButtonAccessClicked = { navController.navigate(Route.HOME.name) },
        onButtonRegisterClicked = { }
    )
}

@Composable
private fun LoginScreen(
    emailValue: String,
    passwordValue: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonAccessClicked: () -> Unit,
    onButtonRegisterClicked: () -> Unit,
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
        Spacer(modifier = Modifier.height(150.dp))
        ButtonPrimary(
            text = stringResource(id = R.string.login_screen_title),
            icon = null,
            onClicked = onButtonAccessClicked
        )
        RegisterButton(onButtonRegisterClicked)
        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = ColorDivider, thickness = 1.dp)
    }
}

@Composable
private fun TitleAndSubtitle() {
    Column(
        modifier = Modifier
            .padding(top = 150.dp)
    ) {
        Text(
            text = stringResource(id = R.string.login_screen_title),
            style = MaterialTheme.typography.h5,
            color = Black,
            fontWeight = FontWeight.Bold,
            minLines = 1,
            modifier = Modifier
                .padding(horizontal = 34.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.login_screen_subtitle),
            color = Color.Gray,
            minLines = 1,
            modifier = Modifier
                .padding(horizontal = 34.dp),
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
        label = { Text(text = "E-mail") },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Orange,
            unfocusedIndicatorColor = ColorInput,
            cursorColor = ColorInput,
            focusedLabelColor = Orange,
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
            Text(text = "Password")
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Orange,
            unfocusedIndicatorColor = ColorInput,
            cursorColor = ColorInput,
            focusedLabelColor = Orange,
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
                val description = if (passwordHidden) "Show password" else "Hide password"
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
    ){
        Text(text = "Não tem uma conta? ", color = ColorInput)
        Text(
            modifier = Modifier.clickable { onClick() },
            text = "Cadastre-se",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
    }

}

@Preview
@Composable
private fun LoginPreview() {
    ItaucasebankTheme {
        LoginScreen(
            emailValue = "larissa@email.com",
            passwordValue = "12345",
            onEmailChanged = {},
            onPasswordChanged = {},
            onButtonAccessClicked = {},
            onButtonRegisterClicked = {},
        )
    }
}