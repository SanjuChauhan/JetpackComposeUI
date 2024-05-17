package com.jio.jetpack.compose.dashboard.widgets

import android.util.Log
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
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.data.DataProvider
import com.jio.jetpack.compose.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardNavigationDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Open)
    ModalNavigationDrawer(
        drawerContent = {
            DrawerContentComponent()
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
    ) {}
}

@Composable
fun DrawerContentComponent(closeDrawer: () -> Unit = {}) {
    LazyColumn(
        modifier = Modifier
            .requiredWidth(300.dp)
            .fillMaxHeight()
            .background(primaryColorLight),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        content = {
            item {
                Column {
                    Image(
                        painterResource(id = R.drawable.account_profile_pic),
                        contentDescription = "profile icon",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top = 20.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Phone Number",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                    ) {
                        Divider(
                            color = dividerOne,
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
            items(DataProvider.getInstance().getDrawerList()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                        .clickable {
                            closeDrawer()
                            Log.e("DrawerContentComponent", "Drawer item click=  ${it.itemName}")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = it.icon),
                        contentDescription = "nav icon icon",
                        modifier = Modifier
                            .size(35.dp)
                    )
                    Text(
                        text = it.itemName,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp)
                    )
                }
            }
        })
//    Column(
//        modifier = Modifier
//            .requiredWidth(300.dp)
//            .fillMaxHeight()
//            .background(primaryColorLight)
//    ) {
//    }
}

@Preview
@Composable
fun DashboardNavigationDrawerPreview() {
    Column {
        DashboardNavigationDrawer()
    }
}