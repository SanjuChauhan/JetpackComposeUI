package com.jio.jetpack.compose.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.jio.jetpack.compose.addDevice.AddDeviceActivity
import com.jio.jetpack.compose.btmesh.BLEMeshDeviceListActivity
import com.jio.jetpack.compose.data.DataProvider
import com.jio.jetpack.compose.ui.theme.JetPackComposeTheme
import com.jio.jetpack.compose.ui.theme.*
import com.jio.jetpack.compose.ui.widgets.AppToolbar
import com.jio.jetpack.compose.dashboard.widgets.DashboardCard
import com.jio.jetpack.compose.dashboard.widgets.DashboardStatusCard
import com.jio.jetpack.compose.dashboard.widgets.DrawerContentComponent
import com.jio.jetpack.compose.dashboard.widgets.FavoriteItemCard
import com.jio.jetpack.compose.dashboard.widgets.SensorMonitoringCard
import com.jio.jetpack.compose.dashboard.widgets.WeatherCard
import com.jio.jetpack.compose.ui.widgets.AddDeviceBottomSheet
import com.jio.jetpack.compose.utils.findActivity
import kotlinx.coroutines.launch

class DashboardActivity : ComponentActivity() {

    private lateinit var launchActivity: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                launchActivity = LocalContext.current

                DashboardMain(launchActivity)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardMain(context: Context) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
//    var showSheet by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContentComponent(closeDrawer = {
                coroutineScope.launch { drawerState.close() }
            })
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AppToolbar(
                    context = LocalContext.current,
                    toolbarTitle = "Dashboard",
                    isDrawerIcon = true,
                    isNotification = true,
                    isAddDeviceIcon = true,
                    openDrawer = {
                        coroutineScope.launch { drawerState.open() }
                    },
                    openAddDeviceBottomSheet = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) {
                                sheetState.hide()
                            } else {
                                sheetState.expand()
                            }
                        }
                    }
                )
                GateWayInfo()
                if (sheetState.isVisible) {
                    AddDeviceBottomSheet(
                        bottomSheetState = sheetState,
                        itemClick = {
                            if (it.contains("Add Device")) {
                                coroutineScope.launch {
                                    openAddDeviceActivity(context)
                                    sheetState.hide()
                                }
                            }
                        }
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        FirmwareUpdateUI()
                        StatusUI()
                        WeatherCard(
                            modifier = Modifier
                                .height(100.dp)
                                .padding(start = 8.dp, top = 10.dp, end = 8.dp)
                        )
//                        HandleDashboardCards(context = context)
                        HandleDashboardCards(true, context)
                        SensorMonitoringCard(
                            modifier = Modifier
                                .padding(start = 8.dp, top = 10.dp, end = 8.dp)
                        )
                        SensorMonitoringCard(
                            modifier = Modifier
                                .padding(start = 8.dp, top = 10.dp, end = 8.dp)
                        )
                    }
                    item {
                        FavoriteGrid()
                    }
                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GateWayInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                Modifier.weight(0.5f)
            ) {
                Image(
                    painterResource(id = R.drawable.smartcable_online),
                    contentDescription = "gateway icon",
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(
                Modifier.weight(5f)
            ) {
                Text(
                    text = "Home Name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
                )
            }
            Column(
                Modifier.weight(0.5f)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_gateway_settings_blue),
                    contentDescription = "gateway icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(dividerColor)
        )
    }
}

@Composable
fun FirmwareUpdateUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 10.dp, end = 8.dp, bottom = 0.dp)
            .background(buttonEnableColorOne, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .background(Color.White, shape = CircleShape)
        ) {
            Image(
                painterResource(id = R.drawable.jsl_update_icon_dash),
                contentDescription = "Update icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            )
        }
        Text(
            text = "Devices Firmware Updates are available",
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, top = 8.dp)
        )
    }
}

@Composable
fun StatusUI() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(DataProvider.getInstance().getStatusDataList()) {
                DashboardStatusCard(
                    modifier = Modifier,
                    cardTitle = it.statusName,
                    icon = it.icon,
                    isEnabled = it.isEnabled
                )
            }
        },
        modifier = Modifier
            .height(80.dp)
            .padding(start = 8.dp, top = 10.dp, end = 8.dp)
    )
}

@Composable
fun HandleDashboardCards(isBridgeAvailable: Boolean = false, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp, 10.dp, 8.dp, 8.dp)
        ) {
            DashboardCard(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                cardBackground = roomBackground,
                cardTitle = "Rooms",
                count = "5",
                icon = R.drawable.ic_resource_white_rooms,
                onClick = {}
            )
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(10.dp)
                    .background(Color.White)
            ) {}
            DashboardCard(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                cardBackground = deviceBackground,
                cardTitle = "Devices",
                count = "2",
                icon = R.drawable.ic_resource_white_device,
                onClick = {}
            )
            if (!isBridgeAvailable) {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(10.dp)
                        .background(Color.White)
                ) {}
                DashboardCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                    cardBackground = sceneBackground,
                    cardTitle = "Scenes",
                    count = "4",
                    icon = R.drawable.ic_resource_white_scenes,
                    onClick = {}
                )
            }
        }
        if (isBridgeAvailable) {
            Row(
                modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
            ) {
                DashboardCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                    cardBackground = sceneBackground,
                    cardTitle = "Scenes",
                    count = "0",
                    icon = R.drawable.ic_resource_white_scenes,
                    onClick = {}
                )
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(10.dp)
                        .background(Color.White)
                ) {}
                DashboardCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                    cardBackground = bleBridgeBackground,
                    cardTitle = "BLE bridge",
                    count = "3",
                    icon = R.drawable.btmesh_dashboard_bridge,
                    onClick = {
                        openBleBridgeNextActivity(context)
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteGrid() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 1000.dp)
            .padding(start = 8.dp, top = 10.dp, end = 8.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(DataProvider.getInstance().getFavoriteList()) {
                FavoriteItemCard(
                    it.favoriteName,
                    it.roomName,
                    it.icon,
                    it.isEnabled
                )
            }
        },
    )
}

/**
 * Navigate to Ble Mesh Device List
 */
fun openBleBridgeNextActivity(context: Context) {
    context.findActivity().startActivity(
        Intent(context, BLEMeshDeviceListActivity::class.java)
    )
}

/**
 * Navigate to Add Device page
 */
fun openAddDeviceActivity(context: Context) {
    context.findActivity().startActivity(
        Intent(context, AddDeviceActivity::class.java)
    )
}

@Preview
@Composable
fun DashboardMainPreview() {
    JetPackComposeTheme {
        DashboardMain(LocalContext.current)
    }
}