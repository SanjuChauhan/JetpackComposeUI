package com.jio.jetpack.compose.btmesh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.JetPackComposeTheme
import com.jio.jetpack.compose.ui.theme.blackTextColor
import com.jio.jetpack.compose.ui.theme.errorOne
import com.jio.jetpack.compose.ui.theme.grayTwo
import com.jio.jetpack.compose.ui.theme.primaryColorLight
import com.jio.jetpack.compose.ui.widgets.AppToolbar
import com.jio.jetpack.compose.utils.Utils
//import com.smarttoolfactory.colorpicker.selector.SelectorRectHueSaturationHSV

class BLEMeshDeviceDetailsActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                BLEMeshDeviceDetailsScreen(intent)
            }
        }
    }
}

@Composable
fun BLEMeshDeviceDetailsScreen(intent: Intent?) {
    var deviceName = intent?.getStringExtra(Utils.EXTRA_DATA_DEVICE_NAME)
    val deviceStatus = intent?.getIntExtra(Utils.EXTRA_DATA_DEVICE_STATUS, 2)

    if (deviceName.isNullOrEmpty()) {
        deviceName = "BLE Mesh Device Details"
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            val context = LocalContext.current
            AppToolbar(
                toolbarTitle = deviceName,
                context = context,
                isDrawerIcon = false,
                isBackButtonIcon = true,
                isNotification = false,
                isAddDeviceIcon = false,
                isMenuIcon = true
            )
            DeviceDetail(deviceName, deviceStatus)
        }
    }
}

@Composable
fun DeviceDetail(deviceName: String, deviceStatus: Int?) {
    var hue by remember { mutableFloatStateOf(0f) }
    var saturation by remember { mutableFloatStateOf(1f) }

    val colorHSL =
        Color.hsl(hue = hue, saturation = saturation, lightness = .5f, alpha = 1f)

    Log.e("ItemDeviceCard", "colorHSL = $colorHSL")
    Log.e("ItemDeviceCard", "hue = $hue  saturation = $saturation")


    var deviceOnOffStatus by remember { mutableStateOf(deviceStatus == 2) }
    val isDeviceOffline by remember { mutableStateOf((deviceStatus == 4 || deviceStatus == 5)) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = deviceName,
                    fontSize = 14.sp,
                    color = blackTextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 4.dp)
                )
                Text(
                    text = "Ground floor",
                    fontSize = 12.sp,
                    color = if (isDeviceOffline) errorOne else blackTextColor,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 4.dp)
                )
            }
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

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Row(
                modifier =
                Modifier
                    .weight(0.5f)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_jsl_left_time),
                    contentDescription = "icon",
                    modifier = Modifier
                        .background(
                            color = primaryColorLight,
                            shape = CircleShape
                        )
                        .padding(6.dp)
                        .size(24.dp)
                )
                Text(
                    text = "Timer",
                    fontSize = 12.sp,
                    color = blackTextColor,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp)
                )
            }
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
//        SelectorRectHueSaturationHSV(
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(4 / 3f)
//                .padding(top = 20.dp),
//            hue = hue,
//            saturation = saturation,
//            onChange = { h, s ->
//                hue = h
//                saturation = s
//            },
//            selectionRadius = 8.dp
//        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .padding(6.dp)
                    .size(24.dp)
                    .clickable {
                        hue = 0f
                        saturation = 1f
                    },
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Green,
                        shape = CircleShape
                    )
                    .padding(6.dp)
                    .size(24.dp)
                    .clickable {
                        hue = 120f
                        saturation = 1f
                    },
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Blue,
                        shape = CircleShape
                    )
                    .padding(6.dp)
                    .size(24.dp)
                    .clickable {
                        hue = 240f
                        saturation = 1f
                    },
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Yellow,
                        shape = CircleShape
                    )
                    .padding(6.dp)
                    .size(24.dp)
                    .clickable {
                        hue = 60f
                        saturation = 1f
                    },
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BLEMeshDeviceDetailsScreenPreview() {
    JetPackComposeTheme {
        BLEMeshDeviceDetailsScreen(null)
    }
}