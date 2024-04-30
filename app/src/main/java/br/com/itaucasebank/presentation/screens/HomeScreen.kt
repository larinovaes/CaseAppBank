package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.components.BankCardComponent
import br.com.itaucasebank.components.ErrorMessageComponent
import br.com.itaucasebank.components.LoadingComponent
import br.com.itaucasebank.components.MeuButtonComponent
import br.com.itaucasebank.enums.Menu
import br.com.itaucasebank.enums.MenuCard
import br.com.itaucasebank.presentation.viewmodel.HomeViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Blue
import br.com.itaucasebank.ui.theme.Cinza
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Pink
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    HomeScreen(
        profileImageUrl = uiState.value.profileImageUrl,
        userName = uiState.value.userName,
        accountNumber = uiState.value.accountNumber,
        agencyNumber = uiState.value.agencyNumber,
        notificationCount = uiState.value.notificationCount,
        balanceValue = uiState.value.balanceValue,
        isLoading = uiState.value.isLoading,
        isError = uiState.value.isError,
        buttonNotification = {},
        accountStatementButton = { navController.navigate(Route.EXTRACT.name) },
        onItemClickMenuCard = { navController.navigate(Route.TRANSFER_AREA.name) },
        onRetry = {},
        onBottomNavigationItemClick = {}
    )
}

@Composable
private fun HomeScreen(
    profileImageUrl: String,
    userName: String,
    accountNumber: String,
    agencyNumber: String,
    balanceValue: String,
    notificationCount: Int,
    isLoading: Boolean,
    isError: Boolean,
    buttonNotification: () -> Unit,
    accountStatementButton: () -> Unit,
    onItemClickMenuCard: () -> Unit,
    onRetry: () -> Unit,
    onBottomNavigationItemClick: () -> Unit,
) {
    val backgroundColor = if (isLoading || isError) Color.White else Blue
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.BottomCenter,
    ) {
        if (isLoading) {
            LoadingComponent()
        } else if (isError) {
            ErrorMessageComponent(onClick = onRetry)
        } else {
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
                    Profile(profileImageUrl = profileImageUrl)
                    UserInformationSection(
                        userName = userName,
                        accountNumber = accountNumber,
                        agencyNumber = agencyNumber,
                    )
                    SectionNotification(
                        buttonNotification = buttonNotification,
                        notificationCount = notificationCount
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                FinancialSection(
                    nameUser = userName,
                    balanceValue = balanceValue,
                    accountStatementButton = accountStatementButton,
                    onItemClick = onItemClickMenuCard
                )
            }
        }
        BottomNavigationMenu(onBottomNavigationItemClick)
    }
}

@Composable
private fun Profile(profileImageUrl: String) {
    Surface(
        modifier = Modifier
            .size(54.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Transparent),
        elevation = 4.dp,
    ) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
        )
    }
}

@Composable
private fun SectionNotification(
    notificationCount: Int = 0,
    buttonNotification: () -> Unit = {},
) {
    val isNotificationCountVisible = notificationCount > 0
    IconButton(
        onClick = { buttonNotification() },
        enabled = isNotificationCountVisible,
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
            if (isNotificationCountVisible) {
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
    userName: String,
    accountNumber: String,
    agencyNumber: String,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        Text(
            text = "Olá, $userName",
            color = Color.White,
            minLines = 1,
        )
        Text(
            text = "Ag. $agencyNumber, CC $accountNumber",
            color = Color.White,
            minLines = 1,
        )
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
private fun BottomNavigationMenu(onClick: () -> Unit) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MenuItem(
                    menu = Menu.HOME,
                    isSelected = true
                )
                MenuItem(
                    menu = Menu.SEARCH,
                    isSelected = false
                )
                MenuItem(
                    menu = Menu.EMAIL,
                    isSelected = false
                )
                MenuItem(
                    menu = Menu.SETTINGS,
                    isSelected = false
                )
            }
        }
    }
}

@Composable
private fun MenuItem(
    menu: Menu,
    isSelected: Boolean,
) {
    if (isSelected) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Blue)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                modifier = Modifier.clickable { },
                painter = painterResource(id = menu.icon),
                contentDescription = null,
                tint = Color.White,
            )
            Text(
                text = stringResource(id = menu.description),
                fontSize = 12.sp,
                color = Color.White,
            )
        }
    } else {
        Icon(
            modifier = Modifier.padding(24.dp),
            painter = painterResource(id = menu.icon),
            contentDescription = null,
            tint = Cinza,
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    ItaucasebankTheme {
        HomeScreen(
            profileImageUrl = "",
            userName = "Carlos Daniel",
            agencyNumber = "342",
            accountNumber = "**2390-0",
            buttonNotification = {},
            balanceValue = "R\$ 3.450,00",
            isLoading = false,
            isError = false,
            accountStatementButton = {},
            notificationCount = 3,
            onItemClickMenuCard = {},
            onRetry = {},
            onBottomNavigationItemClick = {}
        )
    }
}