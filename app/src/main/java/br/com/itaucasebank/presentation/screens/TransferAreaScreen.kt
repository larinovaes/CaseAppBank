package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ContactCardComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.presentation.model.Contact
import br.com.itaucasebank.ui.theme.Cinza
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Purple40

@Composable
fun TransferAreaScreen(
    navController: NavController,
) {
    TransferAreaScreen(
        title = stringResource(id = R.string.transfer_area_screen_title_toolbar),
        onBackClick = { navController.popBackStack() },
        onAddNewContactButton = {},
        onContactButton = {},

    )
}

@Composable
private fun TransferAreaScreen(
    title: String,
    onBackClick: () -> Unit = {},
    onAddNewContactButton: () -> Unit,
    onContactButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        ToolbarComponent(
            title = title,
            onClick = onBackClick
        )

        AccountSession(
            onClick =  onAddNewContactButton
        )
        ContactSession(
            onClick = onContactButton,
            contacts = listOf(
                Contact(
                    profileImage = R.mipmap.image_maria,
                    nameContact = "Maria"
                ),
                Contact(
                    profileImage = R.drawable.ic_icon_person,
                    nameContact = "Maria"
                )
            )
        )
    }
}

@Composable
private fun ContactSession(
    onClick: () -> Unit,
    contacts: List<Contact>
) {
    LazyRow {
        items(contacts) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp)
            ) {
                ContactCardComponent(
                    onClick = { onClick() },
                    contacts = it
                )
            }
        }
    }
}

@Composable
private fun AccountSession(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier
            .weight(1f),
            text = stringResource(id = R.string.transfer_area_screen_title_account_session),
            color = Cinza,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier
                .clickable { onClick() },
            text = stringResource(id = R.string.transfer_area_screen_button_account_session),
            color = Purple40,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun TransferAreaPreview() {
    ItaucasebankTheme {
        TransferAreaScreen(
            title = "Transferência",
            onBackClick = {},
            onAddNewContactButton = {},
            onContactButton = {},
        )
    }
}