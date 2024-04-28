package br.com.itaucasebank.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.BankCardComponent
import br.com.itaucasebank.components.MeuButtonComponent
import br.com.itaucasebank.enums.MenuCard
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Blue
import br.com.itaucasebank.ui.theme.Cinza
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Pink

@Composable
fun HomeScreen(
    navController: NavController,
) {
    HomeScreen(
        profileImage = R.drawable.ic_icon_person,
        nameUser = stringResource(id = R.string.home_screen_title),
        numberAccount = stringResource(id = R.string.home_screen_number_account),
        buttonNotification = {},
        balanceValue = "R\$ 3.450,00",
        accountStatementButton = {
            navController.navigate(Route.EXTRACT.name)
        },
        notificationCount = 3,
        isNotification = true,
        onItemClickMenuCard = {
            navController.navigate(Route.TRANSFER_AREA.name)
        },
        onClick = {}
    )
}

@Composable
private fun HomeScreen(
    @DrawableRes profileImage: Int,
    nameUser: String,
    numberAccount: String,
    balanceValue: String,
    buttonNotification: () -> Unit,
    accountStatementButton: () -> Unit,
    isNotification: Boolean = false,
    notificationCount: Int = 3,
    onItemClickMenuCard: () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Profile(profileImage = profileImage)
                UserInformationSection(
                    nameUser = nameUser,
                    numberAccount = numberAccount
                )
                SectionNotification(
                    buttonNotification = buttonNotification,
                    isNotification = isNotification,
                    notificationCount = notificationCount
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            FinancialSection(
                nameUser = nameUser,
                balanceValue = balanceValue,
                accountStatementButton = accountStatementButton,
                onItemClick = onItemClickMenuCard
            )
        }
        BottomNavigationMenu(onClick)
    }
}

@Composable
private fun Profile(
    @DrawableRes profileImage: Int,
) {
    Surface(
        modifier = Modifier
            .size(54.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Transparent),
        elevation = 4.dp,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            alignment = Alignment.Center,
            painter = painterResource(id = profileImage),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun SectionNotification(
    buttonNotification: () -> Unit,
    isNotification: Boolean = false,
    notificationCount: Int = 0
) {
    IconButton(
        onClick = { buttonNotification() },
        enabled = isNotification
    ) {
        Box {
            Icon(
                modifier = Modifier
                    .padding(24.dp)
                    .size(24.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White,
            )
            if (isNotification) {
                Text(
                    text = notificationCount.toString(),
                    modifier = Modifier
                        .padding(start = 35.dp, top = 20.dp)
                        .background(color = Pink, shape = CircleShape)
                        .padding(horizontal = 5.dp, vertical = 1.dp),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

    }
}


@Composable
private fun UserInformationSection(
    nameUser: String,
    numberAccount: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        Row {
            Text(
                text = "Olá, ",
                color = Color.White,
                minLines = 1,
            )
            Text(
                text = nameUser,
                color = Color.White,
                minLines = 1,
            )
        }
        Row {
            Text(
                text = "Ag. ",
                color = Color.White,
                minLines = 1,
            )

            Text(
                text = numberAccount,
                color = Color.White,
                minLines = 1,
            )
        }
    }
}

@Composable
private fun FinancialSection(
    nameUser: String,
    balanceValue: String,
    accountStatementButton: () -> Unit,
    onItemClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(topStartPercent = 6, topEndPercent = 6),
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            BankCardComponent(
                nameUser = nameUser,
                balanceValue = balanceValue,
                accountStatementButton = accountStatementButton
            )
            Spacer(modifier = Modifier.height(24.dp))
            MeuButtonComponent(
                menuCard = MenuCard.NEW_TRANSFER,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
private fun BottomNavigationMenu(
    onClick: () -> Unit
) {
    Card(
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .padding(24.dp)
                        .clickable { onClick() },
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = null,
                    tint = Cinza,
                )
                Icon(
                    modifier = Modifier
                        .padding(24.dp)
                        .clickable { onClick() },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = Cinza,
                )
                Icon(
                    modifier = Modifier
                        .padding(24.dp)
                        .clickable { onClick() },
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null,
                    tint = Cinza,
                )

                Icon(
                    modifier = Modifier
                        .padding(24.dp)
                        .clickable { onClick() },
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null,
                    tint = Cinza,
                )
            }
        }
    }

}

@Preview
@Composable
private fun HomePreview() {
    ItaucasebankTheme {
        HomeScreen(
            profileImage = R.drawable.ic_icon_person,
            nameUser = stringResource(id = R.string.home_screen_title),
            numberAccount = stringResource(id = R.string.home_screen_number_account),
            buttonNotification = {},
            balanceValue = "R\$ 3.450,00",
            accountStatementButton = {},
            isNotification = true,
            notificationCount = 3,
            onItemClickMenuCard = {},
            onClick = {}
        )
    }
}