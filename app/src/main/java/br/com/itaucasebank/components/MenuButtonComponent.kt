package br.com.itaucasebank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.enums.MenuCard
import br.com.itaucasebank.ui.theme.IntenseOrange

@Composable
fun MeuButtonComponent(
    menuCard: MenuCard,
    onItemClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(120.dp)
            .height(100.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(IntenseOrange)
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                painter = painterResource(id = menuCard.icon),
                contentDescription = null,
                tint = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = menuCard.title,
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun NewTransferButtonPreview() {
    MeuButtonComponent(
        menuCard = MenuCard.NEW_TRANSFER,
        onItemClick = {}
    )
}