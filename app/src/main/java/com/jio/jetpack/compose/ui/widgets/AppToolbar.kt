package com.jio.jetpack.compose.ui.widgets

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.ui.theme.*
import com.jio.jetpack.compose.utils.findActivity
import com.jio.jetpack.compose.utils.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    context: Context? = null,
    containerColor: Color = primaryColorDark,
    titleContentColor: Color = Color.White,
    toolbarTitle: String = "Dashboard",
    isDrawerIcon: Boolean = true,
    isBackButtonIcon: Boolean = false,
    isNotification: Boolean = true,
    isAddDeviceIcon: Boolean = true,
    isMenuIcon: Boolean = false,
    openDrawer: () -> Unit = {},
    openAddDeviceBottomSheet: () -> Unit = {}
) {
    Column(modifier = Modifier.height(48.dp)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = toolbarTitle,
                    fontSize = 16.sp
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = containerColor,
                titleContentColor = titleContentColor,
            ),
            modifier = Modifier
                .background(containerColor)
                .align(Alignment.CenterHorizontally),
            navigationIcon = {
                if (isDrawerIcon) {
                    IconButton(onClick = {
                        openDrawer()
                        context?.showToast("Open Drawer")
                    }) {
                        Icon(
                            Icons.Filled.Menu, "drawerIcon",
                            tint = Color.White
                        )
                    }
                } else if (isBackButtonIcon) {
                    IconButton(onClick = {
                        context?.findActivity()?.finish()
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft, "backIcon",
                            tint = Color.White
                        )
                    }
                }
            },
            actions = {
                if (isNotification) {
                    IconButton(onClick = {
                        context?.showToast("Notification")
                    }) {
                        Icon(
                            Icons.Default.Notifications, "notification",
                            tint = Color.White
                        )
                    }
                }
                if (isAddDeviceIcon) {
                    IconButton(onClick = {
                        context?.showToast("Add Device")
                        openAddDeviceBottomSheet()
                    }) {
                        Icon(
                            Icons.Filled.Add, "addDeviceIcon",
                            tint = Color.White
                        )
                    }
                }
                if (isMenuIcon) {
                    IconButton(onClick = {
                        context?.showToast("Option Menu")
                    }) {
                        Icon(
                            Icons.Default.MoreVert, "Option Menu",
                            tint = Color.White
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun AppToolbarPreview() {
    Column {
        AppToolbar(openDrawer = {})
    }
}