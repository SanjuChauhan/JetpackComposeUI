package com.jio.jetpack.compose.dashboard.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.*

@Composable
fun DashboardStatusCard(
    modifier: Modifier,
    cardBackground: Color = grayTwo,
    cardTitle: String = "Armed",
    icon: Int = R.drawable.ic_mode_parciallyarmed_on,
    isEnabled: Boolean = false
) {
    ElevatedCard(
        modifier = modifier
            .width(80.dp)
            .height(80.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = if (isEnabled) blueOne else cardBackground)
    ) {
        Box(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 10.dp)
        ) {
            Column {
                Image(
                    painterResource(id = icon),
                    contentDescription = "icon",
                    colorFilter = ColorFilter.tint(if (isEnabled) Color.White else grayOne),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .size(32.dp)
                )
                Text(
                    text = cardTitle,
                    fontSize = 10.sp,
                    color = if (isEnabled) Color.White else inactiveStatusTextColor,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DashboardStatusCardPreview() {
    Column {
        DashboardStatusCard(
            modifier = Modifier
        )
    }
}