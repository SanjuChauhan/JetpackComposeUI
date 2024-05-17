package com.jio.jetpack.compose.dashboard.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.grayDotTint
import com.jio.jetpack.compose.ui.theme.sensorBackground
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SensorMonitoringCard(
    modifier: Modifier = Modifier,
    cardTitle: String = "Sensor Monitoring",
    cardSubTitle: String = "Monitor all sensors",
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    var expandedState by remember { mutableStateOf(true) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )
    val coroutineScope = rememberCoroutineScope()
    ElevatedCard(
        modifier = modifier
            .height(if (expandedState) 240.dp else 80.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = sensorBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_sensor_logo),
                        contentDescription = "sensor icon",
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1.2f)
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Text(
                            text = cardTitle,
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .wrapContentSize()
                        )
                        Text(
                            text = cardSubTitle,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .wrapContentSize()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.2f)
                            .paint(painterResource(id = R.drawable.ic_expand_on))
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Three dot icon",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Center)
                        )
                    }
                }
                if (expandedState) {
                    Row(
                        modifier = Modifier
                            .weight(2f)
                            .padding(10.dp)
                            .animateContentSize { initialValue, targetValue ->
                            }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            HorizontalPager(
                                state = pagerState
                            ) { page ->
                                // Our page content
                                SensorMonitoringPagerItem(page)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1.2f)
                            .padding(start = 10.dp, top = 6.dp),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (expandedState) {
                            Image(
                                painterResource(id = R.drawable.azc_prev),
                                contentDescription = "previous icon",
                                modifier = Modifier
                                    .alpha(if (pagerState.canScrollBackward) 10f else 255f)
                                    .clickable(enabled = pagerState.canScrollBackward) {
                                        coroutineScope.launch {
                                            // Call scroll to on pagerState
                                            if (pagerState.canScrollBackward) {
                                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                            }
                                        }
                                    },
                            )
                            Image(
                                painterResource(id = R.drawable.azc_next),
                                contentDescription = "next icon",
                                modifier = Modifier
                                    .padding(start = 40.dp)
                                    .alpha(if (pagerState.canScrollForward) 10f else 255f)
                                    .clickable(enabled = pagerState.canScrollForward) {
                                        coroutineScope.launch {
                                            // Call scroll to on pagerState
                                            if (pagerState.canScrollForward) {
                                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                            }
                                        }
                                    },
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.2f)
                            .paint(painterResource(id = R.drawable.ic_expand_off))
                            .rotate(rotationState)
                            .clickable {
                                expandedState = !expandedState
                            },
                    ) {
                        Icon(
                            imageVector = if (expandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Three dot icon",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SensorMonitoringPagerItem(page: Int) {
    Column {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
        ) {
            Text(
                text = "Ground floor $page",
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Three dot icon",
                tint = grayDotTint,
                modifier = Modifier.weight(0.2f)
            )
        }
        ElevatedCard(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(start = 16.dp, end = 16.dp)
                    .align(CenterHorizontally)
            ) {
                Text(
                    text = "Temperature",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(CenterHorizontally)
                )
                Image(
                    painterResource(id = R.drawable.azc_temperature),
                    contentDescription = "temperature icon",
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(CenterHorizontally)
                )
                Text(
                    text = "0 ℃",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(CenterHorizontally)
                )
            }
        }
    }
}

@Preview
@Composable
fun SensorMonitoringCardPreview() {
    Column {
        SensorMonitoringCard()
    }
}