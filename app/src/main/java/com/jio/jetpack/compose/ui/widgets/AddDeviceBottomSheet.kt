package com.jio.jetpack.compose.ui.widgets

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.data.DataProvider
import com.jio.jetpack.compose.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeviceBottomSheet(
    bottomSheetState: SheetState,
    itemClick: (name: String) -> Unit = {},
    openDismiss: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = { openDismiss() },
        sheetState = bottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Add Options",
                        fontSize = 14.sp,
                        color = greenOne,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            items(DataProvider.getInstance().getAddDeviceBottomSheetList()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            itemClick(it.itemName)
                            Log.e("AddDeviceBottomSheet", "BottomSheet item click=  ${it.itemName}")
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painterResource(id = it.icon),
                            contentDescription = "icon",
                            modifier = Modifier
                                .size(38.dp)
                                .weight(0.1f)
                                .padding(start = 20.dp)
                        )
                        Box(
                            modifier = Modifier
                                .weight(0.8f)
                                .padding(start = 10.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = it.itemName,
                                fontSize = 12.sp,
                                color = textColorOne,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = "Right arrow",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
                                .weight(0.1f)
                                .padding(end = 20.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
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

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddDeviceBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        sheetState.expand()
    }
    Column {
        AddDeviceBottomSheet(bottomSheetState = sheetState)
    }
}