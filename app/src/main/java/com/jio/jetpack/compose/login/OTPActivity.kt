package com.jio.jetpack.compose.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.dashboard.DashboardActivity
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.JetPackComposeTheme
import com.jio.jetpack.compose.ui.theme.buttonEnableColor
import com.jio.jetpack.compose.ui.theme.editTextColor
import com.jio.jetpack.compose.ui.theme.textColorOne
import com.jio.jetpack.compose.ui.widgets.AppToolbar
import com.jio.jetpack.compose.utils.Utils

class OTPActivity : ComponentActivity() {

    private lateinit var launchActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                launchActivity = LocalContext.current as Activity
                val mobileNumber = intent.getStringExtra(Utils.EXTRA_DATA_MOBILE_NUMBER)
                OTPMain(launchActivity, mobileNumber)
            }
        }
    }
}

@Composable
fun OTPMain(activity: Activity, mobileNumber: String? = "8971078078") {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            AppToolbar(
                toolbarTitle = "",
                isDrawerIcon = false,
                isNotification = false,
                isAddDeviceIcon = false
            )
            OTPContent(activity, mobileNumber)
        }
    }
}

@Composable
fun OTPContent(activity: Activity, mobileNumber: String?) {
    val otp = remember { mutableStateOf("") }
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
                    text = "SMS with OTP has been sent",
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
                    text = "to +91 $mobileNumber",
                    fontWeight = FontWeight.Normal,
                    color = textColorOne,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(CenterHorizontally)
                    .padding(24.dp, 10.dp, 24.dp, 0.dp)
            ) {
                OTPEditText(otp)
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(CenterHorizontally)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Didn‘t get the OTP?",
                    fontWeight = FontWeight.Normal,
                    color = textColorOne,
                    fontSize = 14.sp
                )
                Text(
                    text = "Resend OTP",
                    fontWeight = FontWeight.Normal,
                    color = editTextColor,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 0.dp, 24.dp, 0.dp)
                ) {
                    ElevatedButton(
                        onClick = { openNextActivity(activity, otp.value) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonEnableColor
                        )
                    ) {
                        Text(
                            text = "Login",
                            color = Color.White
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .padding(14.dp, 10.dp, 10.dp, 14.dp)
                ) {
                    Text(
                        text = "Note: You will be locked for 5 minutes after 2 consecutive failed OTP attempts",
                        fontWeight = FontWeight.Normal,
                        color = textColorOne,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

private fun openNextActivity(activity: Activity, otp: String) {
    val navigate = Intent(activity, DashboardActivity::class.java)
    activity.startActivity(navigate)
    activity.finish()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPEditText(otp: MutableState<String>) {
    TextField(
        // below line is used to get
        // value of text field,
        value = otp.value,
        onValueChange = {
            if (it.length <= 4) {
                otp.value = it
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        // below line is used to add placeholder
        // for our text field.
        placeholder = {
            Text(
                text = "Enter OTP",
                color = editTextColor,
                modifier = Modifier.padding(0.dp)
            )
        },
        textStyle = TextStyle(
            color = editTextColor,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp
        ),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White
        )
    )
}


@Preview
@Composable
fun OTPPreview() {
    JetPackComposeTheme {
        OTPMain(Activity())
    }
}