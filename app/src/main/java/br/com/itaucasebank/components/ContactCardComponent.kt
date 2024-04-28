package br.com.itaucasebank.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.R
import br.com.itaucasebank.presentation.model.Contact
import br.com.itaucasebank.ui.theme.ItaucasebankTheme

@Composable
fun ContactCardComponent(
    onClick: () -> Unit,
    contacts: Contact
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
            ContactPhoto(profileImage = contacts.profileImage)
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                text = contacts.nameContact,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }

}

@Composable
private fun ContactPhoto(
    @DrawableRes profileImage: Int,
) {
   Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
                .fillMaxSize()
                .background(Color.Transparent),
            alignment = Alignment.Center,
            painter = painterResource(id = profileImage),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop
        )

}

@Preview
@Composable
private fun ContactCardComponentPreview() {
    val contacts =
        Contact(
            profileImage = R.mipmap.image_maria,
            nameContact = "Maria"
        )
    ItaucasebankTheme {
        ContactCardComponent(
            contacts = contacts,
            onClick = {}
        )
    }
}