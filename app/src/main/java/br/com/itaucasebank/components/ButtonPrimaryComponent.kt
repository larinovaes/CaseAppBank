package br.com.itaucasebank.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.itaucasebank.R
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Orange

@Composable
fun ButtonPrimaryComponent(
    text: String,
    @DrawableRes icon: Int? = null,
    onClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp)),
            onClick = { onClicked() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                )
                if (icon != null) {
                    Icon (
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    ItaucasebankTheme {
        ButtonPrimaryComponent(
            text = "TEXT",
            icon = R.drawable.ic_arrow,
            onClicked = { }
        )
    }
}