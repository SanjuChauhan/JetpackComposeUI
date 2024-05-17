package com.jio.jetpack.compose.dashboard.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.grayTwo

@Composable
fun WeatherCard(
    modifier: Modifier,
    temp: String = "0",
    sunrise: String = "00:00 AM",
    sunset: String = "00:00 PM",
    humidity: String = "0",
    pressure: String = "0",
    windSpeed: String = "0"
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .paint(
                    painter = painterResource(id = R.drawable.weather_tile_bg),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Text(
                text = "Add Address",
                fontSize = 16.sp,
                color = grayTwo,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Center)
            )
            Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painterResource(id = R.drawable.ic_mode_sunny_filled),
                        contentDescription = "icon",
                        colorFilter = ColorFilter.tint(grayTwo),
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = "$temp ℃",
                        fontSize = 16.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painterResource(id = R.drawable.ic_mode_sunrise_filled),
                        contentDescription = "icon",
                        colorFilter = ColorFilter.tint(grayTwo),
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = "$sunrise",
                        fontSize = 8.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painterResource(id = R.drawable.ic_mode_sunset_filled),
                        contentDescription = "icon",
                        colorFilter = ColorFilter.tint(grayTwo),
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = "$sunset",
                        fontSize = 8.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)
                    .align(BottomCenter)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "$humidity %",
                        fontSize = 10.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                    Text(
                        text = "Outdoor Humidity",
                        fontSize = 6.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "$pressure hPa",
                        fontSize = 10.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                    Text(
                        text = "Outdoor Air Pressure",
                        fontSize = 6.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "$windSpeed m/s",
                        fontSize = 10.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                    Text(
                        text = "Outdoor Wind Speed",
                        fontSize = 6.sp,
                        color = grayTwo,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherCardPreview() {
    Column {
        WeatherCard(
            modifier = Modifier
                .height(100.dp)
        )
    }
}