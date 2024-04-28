package br.com.itaucasebank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.R
import br.com.itaucasebank.presentation.model.ContactModel
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import coil.compose.SubcomposeAsyncImage

@Composable
fun ContactCardComponent(
    onClick: () -> Unit,
    contacts: ContactModel
) {
    Card(
        modifier = Modifier
            .width(110.dp)
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ContactPhoto(profileImageUrl = contacts.profileImageUrl)
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                text = contacts.name,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }

}

@Composable
private fun ContactPhoto(profileImageUrl: String) {
    SubcomposeAsyncImage(
        model = profileImageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(64.dp),
        loading = {
            CircularProgressIndicator()
        }
    )
}

@Preview
@Composable
private fun ContactCardComponentPreview() {
    val contacts =
        ContactModel(
            id = "1",
            profileImageUrl = "https://media.licdn.com/dms/image/D4D03AQEIBryHBC4dKw/profile-displayphoto-shrink_400_400/0/1696031035294?e=1720051200&v=beta&t=fqLQ--hMjKeYcrIGUKn_TYmMsTbFh_eQcbugRY-cqos",
            name = "Maria"
        )
    ItaucasebankTheme {
        ContactCardComponent(
            contacts = contacts,
            onClick = {},
        )
    }
}