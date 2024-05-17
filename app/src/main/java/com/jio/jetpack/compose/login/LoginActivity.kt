package com.jio.jetpack.compose.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.ui.theme.JetPackComposeTheme
import com.jio.jetpack.compose.ui.theme.buttonEnableColor
import com.jio.jetpack.compose.ui.theme.editTextColor
import com.jio.jetpack.compose.ui.theme.primaryColorDark
import com.jio.jetpack.compose.ui.theme.textColorOne
import com.jio.jetpack.compose.ui.widgets.AppToolbar
import com.jio.jetpack.compose.utils.Utils

class LoginActivity : ComponentActivity() {

    private lateinit var launchActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                launchActivity = LocalContext.current as Activity
                LoginMain(launchActivity)
            }
        }
    }
}

@Composable
fun LoginMain(activity: Activity) {
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
            LoginContent(activity)
        }
    }
}

@Composable
fun LoginContent(activity: Activity) {
    val mobileNumber = remember { mutableStateOf("") }
    val termsAndCondition = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
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
                    .align(Alignment.CenterHorizontally)
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
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Please enter your 10 digit mobile number",
                    fontWeight = FontWeight.Normal,
                    color = textColorOne,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp, 10.dp, 24.dp, 0.dp)
            ) {
                MobileNumberEditText(mobileNumber)
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "I agree to ",
                    fontWeight = FontWeight.Normal,
                    color = textColorOne,
                    fontSize = 14.sp
                )
                Text(
                    text = "Terms and Conditions",
                    fontWeight = FontWeight.Normal,
                    color = editTextColor,
                    fontSize = 14.sp
                )
                Box(modifier = Modifier.padding(start = 24.dp)) {
                    CircularCheckbox(
                        checked = termsAndCondition.value,
                        onCheckedChange = { termsAndCondition.value = it }
                    )
                }
            }
        }
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 0.dp, 24.dp, 24.dp)
            ) {
                ElevatedButton(
                    onClick = { openNextActivity(activity, mobileNumber.value) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonEnableColor
                    )
                ) {
                    Text(
                        text = "Generate OTP",
                        color = Color.White
                    )
                }
            }
        }
    }
}

private fun openNextActivity(activity: Activity, mobileNumber: String) {
    val navigate = Intent(activity, OTPActivity::class.java)
    navigate.putExtra(Utils.EXTRA_DATA_MOBILE_NUMBER, mobileNumber)
    activity.startActivity(navigate)
    activity.finish()
}

@Composable
fun CircularCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(
                color = if (checked) {
                    primaryColorDark
                } else {
                    Color.White
                },
                shape = CircleShape
            )
            .border(1.dp, color = Color.Black, shape = CircleShape)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileNumberEditText(mobileNumber: MutableState<String>) {
    TextField(
        // below line is used to get
        // value of text field,
        value = mobileNumber.value,
        onValueChange = {
            if (it.length <= 10) {
                mobileNumber.value = it
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        // below line is used to add placeholder
        // for our text field.
        placeholder = {
            Text(
                text = "Mobile Number",
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
fun LoginPreview() {
    JetPackComposeTheme {
        LoginMain(Activity())
    }
}