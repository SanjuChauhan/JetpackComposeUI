package com.jio.jetpack.compose.addDevice

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.btmesh.BLEMeshDeviceDetailsScreen
import com.jio.jetpack.compose.ui.theme.JetPackComposeTheme
import com.jio.jetpack.compose.ui.theme.blackTextColor
import com.jio.jetpack.compose.ui.theme.errorOne
import com.jio.jetpack.compose.ui.widgets.AppToolbar

class AddDeviceActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                AddDeviceScreen()
            }
        }
    }
}

@Composable
fun AddDeviceScreen() {
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
                toolbarTitle = "Add BT Mesh Devices",
                context = context,
                isDrawerIcon = false,
                isBackButtonIcon = true,
                isNotification = false,
                isAddDeviceIcon = false,
                isMenuIcon = false
            )
            LoadAnimationMain()
        }
    }
}

@Composable
fun LoadAnimationMain() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.all_icons))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(top = 100.dp),
            alignment = Alignment.Center,
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        Text(
            text = "Scanning for BT Mesh Devices",
            fontSize = 16.sp,
            color = blackTextColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddDeviceScreenPreview() {
    JetPackComposeTheme {
        AddDeviceScreen()
    }
}