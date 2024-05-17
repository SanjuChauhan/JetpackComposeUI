package com.jio.jetpack.compose.ui.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.models.DeviceModel
import com.jio.jetpack.compose.ui.theme.blackTextColor
import com.jio.jetpack.compose.ui.theme.blueTwo
import com.jio.jetpack.compose.ui.theme.errorOne
import com.jio.jetpack.compose.ui.theme.errorTwo
import com.jio.jetpack.compose.ui.theme.grayTwo
import com.jio.jetpack.compose.ui.theme.primaryColorLight
//import com.smarttoolfactory.colorpicker.slider.SliderHueHSL
//import com.smarttoolfactory.slider.ColorfulSlider
//import com.smarttoolfactory.slider.MaterialSliderColors
//import com.smarttoolfactory.slider.MaterialSliderDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDeviceCard(
    deviceModel: DeviceModel,
    onClick: () -> Unit
) {
    var deviceOnOffStatus by remember { mutableStateOf(deviceModel.status == 2) }
    val isDeviceOffline by remember { mutableStateOf((deviceModel.status == 4 || deviceModel.status == 5)) }

    var hue by remember { mutableFloatStateOf(0f) }
    val saturation by remember { mutableFloatStateOf(1f) }
    val lightness by remember { mutableFloatStateOf(.5f) }
    val alpha by remember { mutableFloatStateOf(1f) }

    val colorHSL =
        Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

    Log.e("ItemDeviceCard", "colorHSL = $colorHSL")
    Log.e("ItemDeviceCard", "hue = $hue  saturation = $saturation  lightness = $lightness")

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 6.dp, bottom = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = if (deviceModel.status == 2) Color.White else errorTwo),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Image(
                        painterResource(id = deviceModel.icon),
                        contentDescription = "icon",
                        modifier = Modifier
                            .background(
                                color = if (deviceModel.status == 2) blueTwo else errorOne,
                                shape = CircleShape
                            )
                            .padding(10.dp)
                            .size(24.dp)
                    )
                    Column {
                        Text(
                            text = deviceModel.deviceName,
                            fontSize = 14.sp,
                            color = if (deviceModel.status == 2) Color.Black else errorOne,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 10.dp)
                        )
                        Text(
                            text = deviceModel.roomName,
                            fontSize = 12.sp,
                            color = if (deviceModel.status == 2) Color.Black else errorOne,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 10.dp, top = 4.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp)
                ) {
                    Divider(
                        color = grayTwo,
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )
                }
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "status",
                            fontSize = 10.sp,
                            color = blackTextColor,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 10.dp, top = 4.dp)
                        )
                        Text(
                            text = if (isDeviceOffline) "Offline" else {
                                if (deviceOnOffStatus) "On" else "Off"
                            },
                            fontSize = 12.sp,
                            color = if (isDeviceOffline) errorOne else blackTextColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 10.dp, top = 4.dp)
                        )
                    }
                    if (!isDeviceOffline) {
                        Switch(
                            checked = deviceOnOffStatus,
                            onCheckedChange = {
                                deviceOnOffStatus = it
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = primaryColorLight,
                                uncheckedBorderColor = primaryColorLight,
                                uncheckedThumbColor = Color.White
                            )
                        )
                    }
                }
                if (!isDeviceOffline) {
                    Row(modifier = Modifier.padding(top = 10.dp)) {
                        Text(
                            text = "Selected Color",
                            fontSize = 10.sp,
                            color = blackTextColor,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp, top = 4.dp)
                        )
                        Icon(
                            imageVector = Icons.Outlined.Circle,
                            contentDescription = "Circle Icon",
                            modifier = Modifier
                                .wrapContentSize()
                                .background(
                                    color = colorHSL,
                                    shape = CircleShape,
                                )
                                .align(Alignment.CenterVertically)
                        )
                    }
//                    SliderHueHSL(
//                        modifier = Modifier
//                            .padding(top = 10.dp),
//                        hue = hue,
//                        saturation = saturation,
//                        lightness = lightness,
//                        onValueChange = {
//                            hue = it
//                        }
//                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemDeviceCardPreview() {
    Column {
        ItemDeviceCard(
            deviceModel = DeviceModel(
                "BLE MESH Smart Bulb 1111",
                R.drawable.ic_resource_white_device,
                2
            ),
            onClick = {}
        )
    }
}