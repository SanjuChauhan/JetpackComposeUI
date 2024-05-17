package com.jio.jetpack.compose.btmesh

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.data.DataProvider
import com.jio.jetpack.compose.ui.theme.JetPackComposeTheme
import com.jio.jetpack.compose.ui.theme.inactiveStatusTextColor
import com.jio.jetpack.compose.ui.widgets.AppToolbar
import com.jio.jetpack.compose.ui.widgets.ItemDeviceCard
import com.jio.jetpack.compose.utils.Utils
import com.jio.jetpack.compose.utils.findActivity

class BLEMeshDeviceListActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                BLEMeshDeviceListScreen()
            }
        }
    }
}

@Composable
fun BLEMeshDeviceListScreen() {
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
                toolbarTitle = "BLE SIG Mesh Bridge",
                context = context,
                isDrawerIcon = false,
                isBackButtonIcon = true,
                isNotification = false,
                isAddDeviceIcon = true
            )
            DeviceHeaderCard()
            BTMeshDeviceList()
        }
    }
}

@Composable
fun DeviceHeaderCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "All Devices",
                fontSize = 14.sp,
                color = inactiveStatusTextColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 10.dp, top = 10.dp)
            )
            Text(
                text = "${DataProvider.getInstance().getDeviceList().size} devices",
                fontSize = 12.sp,
                color = inactiveStatusTextColor,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 10.dp, top = 2.dp, bottom = 10.dp)
            )
        }
    }
}

@Composable
fun BTMeshDeviceList() {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 10.dp),
        columns = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(DataProvider.getInstance().getDeviceList()) {
                ItemDeviceCard(
                    deviceModel = it,
                    onClick = {
                        openBleMeshDeviceDetailsActivity(context, it.deviceName, it.status)
                    })
            }
        },
    )
}

/**
 * Navigate to Ble Mesh Device Details
 */
fun openBleMeshDeviceDetailsActivity(
    context: Context, deviceName: String,
    deviceStatus: Int
) {
    val nextScreen = Intent(context, BLEMeshDeviceDetailsActivity::class.java)
    nextScreen.putExtra(Utils.EXTRA_DATA_DEVICE_NAME, deviceName)
    nextScreen.putExtra(Utils.EXTRA_DATA_DEVICE_STATUS, deviceStatus)

    context.findActivity().startActivity(nextScreen)
}

@Preview(showBackground = true)
@Composable
fun BLEMeshDeviceListPreview() {
    JetPackComposeTheme {
        BLEMeshDeviceListScreen()
    }
}