package com.jio.jetpack.compose.dashboard.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.dashboard.FavoriteGrid
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.blueOne
import com.jio.jetpack.compose.ui.theme.errorOne
import com.jio.jetpack.compose.ui.theme.errorTwo
import com.jio.jetpack.compose.ui.theme.greenOne

@Composable
fun FavoriteItemCard(
    cardTitle: String = "Wise Bridge",
    roomName: String = "Hall",
    icon: Int = R.drawable.ic_mode_parciallyarmed_on,
    isEnabled: Boolean = false
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = if (isEnabled) Color.White else errorTwo)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
        ) {
            Column {
                Image(
                    painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .background(color = if (isEnabled) blueOne else errorOne, shape = CircleShape)
                        .padding(10.dp)
                        .size(24.dp)
                )
                Text(
                    text = cardTitle,
                    fontSize = 14.sp,
                    color = if (isEnabled) Color.Black else errorOne,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 8.dp)
                )
                Text(
                    text = roomName,
                    fontSize = 12.sp,
                    color = if (isEnabled) Color.Black else errorOne,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 4.dp)
                )

                Text(
                    text = if (isEnabled) "Active" else "Offline",
                    fontSize = 12.sp,
                    color = if (isEnabled) greenOne else errorOne,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 4.dp, bottom = 10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoriteGridPreview() {
    Column {
        FavoriteGrid()
    }
}