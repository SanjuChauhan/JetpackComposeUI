package com.jio.jetpack.compose.login

import android.app.Activity
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.jio.jetpack.compose.ui.theme.buttonEnableColor
import com.jio.jetpack.compose.ui.theme.primaryColorDark
import com.jio.jetpack.compose.ui.theme.textColorOne
import com.jio.jetpack.compose.ui.widgets.AppToolbar

class CountrySelectionActivity : ComponentActivity() {

    private lateinit var launchActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                launchActivity = LocalContext.current as Activity
                CountrySelectionMain()
            }
        }
    }

    @Composable
    fun CountrySelectionMain() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                AppToolbar(
                    toolbarTitle = "Select Region",
                    isDrawerIcon = false,
                    isNotification = false,
                    isAddDeviceIcon = false
                )
                CountrySelectionContent()
            }
        }
    }

    @Composable
    fun CountrySelectionContent() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(CenterHorizontally)
                        .padding(0.dp, 40.dp, 0.dp, 0.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_splash_logo),
                        contentDescription = "app icon",
                        modifier = Modifier
                            .size(120.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(CenterHorizontally)
                        .padding(0.dp, 40.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "JioSmartLiving",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(CenterHorizontally)
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "Please select your region",
                        fontWeight = FontWeight.Normal,
                        color = textColorOne,
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(CenterHorizontally)
                        .padding(0.dp, 24.dp, 0.dp, 0.dp)
                ) {
                    RadioButtonSelection()
                }
            }
            Box(
                contentAlignment = BottomCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 0.dp, 24.dp, 24.dp)
                ) {
                    ElevatedButton(
                        onClick = { openNextActivity() },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonEnableColor
                        )
                    ) {
                        Text(
                            text = "Next",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    private fun openNextActivity() {
        launchActivity.startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    @Composable
    fun RadioButtonSelection() {
        val countrySelectionOption = listOf("India", "Other Country")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Column {
                countrySelectionOption.forEach { text ->
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 10.dp)
                            // below method is use to add
                            // selectable to our radio button.
                            .selectable(
                                // this method is called when
                                // radio button is selected.
                                selected = (text == selectedOption),
                                // below method is called on
                                // clicking of radio button.
                                onClick = { onOptionSelected(text) }
                            )
                    ) {
                        // below line is use to get context.
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                // inside on click method we are setting a
                                // selected option of our radio buttons.
                                onOptionSelected(text)
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier
                                .align(CenterVertically)
                                .padding(start = 10.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun CountrySelectionActivityPreview() {
        JetPackComposeTheme {
            CountrySelectionMain()
        }
    }
}