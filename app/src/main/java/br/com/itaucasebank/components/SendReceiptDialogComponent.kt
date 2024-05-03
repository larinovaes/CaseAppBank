package br.com.itaucasebank.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.itaucasebank.R
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SendReceiptDialogComponent(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                TitleSection()
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    SocialButton(
                        text = stringResource(id = R.string.dialog_button_text_twitter),
                        icon = R.drawable.ic_twitter,
                        onClick = { onDismissRequest() },
                    )
                    SocialButton(
                        text = stringResource(id = R.string.dialog_button_text_facebook),
                        icon = R.drawable.ic_facebook,
                        onClick = { onDismissRequest() },
                    )
                    SocialButton(
                        text = stringResource(id = R.string.dialog_button_text_reddit),
                        icon = R.drawable.ic_reddit,
                        onClick = { onDismissRequest() },
                    )
                    SocialButton(
                        text = stringResource(id = R.string.dialog_button_text_whatsapp),
                        icon = R.drawable.ic_whatsapp,
                        onClick = { onDismissRequest() },
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun TitleSection() {
    Column(modifier = Modifier.padding(24.dp)) {
        TextComponent(
            text = stringResource(id = R.string.title_section_text),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
        TextComponent(
            text = stringResource(id = R.string.title_section_subtitle),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF888888),
        )
    }
}

@Composable
private fun SocialButton(
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .clip(CircleShape)
                .background(color = Color(0xFF222222).copy(alpha = 0.06F))
                .size(64.dp),
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        TextComponent(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF888888),
        )
    }
}

@Composable
@Preview
private fun SendReceiptDialogComponentPreview() {
    ItauCaseBankTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            SendReceiptDialogComponent(
                onDismissRequest = {},
            )
        }
    }
}