package br.com.itaucasebank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.R
import br.com.itaucasebank.presentation.uistate.ExtractUIState
import br.com.itaucasebank.ui.theme.Cinza2
import br.com.itaucasebank.ui.theme.Purple40

@Composable
fun ExtractComponent(
    extractUIState: ExtractUIState
) {
    Card(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text =  extractUIState.category,
                    color = Color.Black,
                    minLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = extractUIState.name,
                    color = Color.Black,
                    minLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.extract_screen_text_value),
                    color = Cinza2,
                    fontSize = 12.sp
                )
                Text(
                    text = extractUIState.amount,
                    color = Purple40,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 1
                )

            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.extract_screen_text_date_time),
                    color = Cinza2,
                    fontSize = 12.sp
                )
                Text(
                    text = extractUIState.createdAt,
                    color = Purple40,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 1

                )

            }
        }


    }
}

@Composable
@Preview
fun ExtractUIStatePreview() {
    val extract =
        ExtractUIState(
            id = "1",
            name = "Maria",
            category = "PIX",
            amount = "R\$ 1.000,00",
            createdAt = "25/05/2024, 10:15 am",
        )
    ExtractComponent(extract)
}