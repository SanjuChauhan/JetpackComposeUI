package com.jio.jetpack.compose.dashboard.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardCard(
    modifier: Modifier,
    cardBackground: Color = deviceBackground,
    cardTitle: String = "Devices",
    count: String = "032423432",
    icon: Int = R.drawable.ic_gateway_settings_blue,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        onClick = onClick
    ) {
        Column {
            Text(
                text = cardTitle,
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp, 8.dp, 0.dp, 0.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(
                        text = count,
                        fontSize = 26.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .align(Bottom)
                            .padding(8.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Box(
                        modifier = Modifier.align(Bottom)
                    ) {
                        Image(
                            painterResource(id = icon),
                            contentDescription = "icon",
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                                .size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardCardPreview() {
    Column {
        DashboardCard(
            modifier = Modifier
                .height(100.dp), onClick = {}
        )
    }
}