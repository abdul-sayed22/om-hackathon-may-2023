package com.om.hackathon.collaborate.login

import android.content.Intent
import android.hardware.lights.LightState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.constraintlayout.compose.ConstraintLayout
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.om.hackathon.collaborate.MainActivity
import com.om.hackathon.collaborate.R
import com.om.hackathon.collaborate.dashboard.DashboardActivity
import com.om.hackathon.collaborate.data.HustleDatabase
import kotlinx.coroutines.flow.collectLatest


class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollaborateTheme {
                Surface(
                    modifier = Modifier.fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.4f))
                    loginForm()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginForm(viewModel: LoginViewModel = viewModel()) {
    var username by rememberSaveable { mutableStateOf("") }
    var inputError by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
    ) {
        val (backgroundImage, formContainer, topImage) = createRefs()

        Image(painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Background",
            contentScale = ContentScale.FillWidth,
            alpha = 0.5f,
            modifier = Modifier.constrainAs(topImage) {
                top.linkTo(parent.top)
                bottom.linkTo(backgroundImage.top, -20.dp)
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
            })

        Image(painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.constrainAs(backgroundImage) {
                top.linkTo(parent.top, 350.dp)
                bottom.linkTo(parent.bottom)
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
            })

        Box(modifier = Modifier.constrainAs(formContainer) {
            top.linkTo(backgroundImage.top, margin = 20.dp)
            bottom.linkTo(parent.bottom, margin = 40.dp)
            absoluteLeft.linkTo(parent.absoluteLeft, margin = 40.dp)
            absoluteRight.linkTo(parent.absoluteRight, margin = 40.dp)
        }) {
            Column() {
                Row() {
                    Text(text = "Start your", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "HUSTLE",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.size(15.dp))
                Row() {
                    Text(
                        text = "Empowering businesses to",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "HUSTLE",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = "with purpose.",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )
                }

                Spacer(modifier = Modifier.size(55.dp))
                OutlinedTextField(value = username, onValueChange = {
                    username = it
                    inputError = false
                }, isError = inputError, label = { Text("Username") }, supportingText = {
                    if (inputError) {
                        Text("Please input the correct username")
                    } else {
                        Text("")
                    }
                }, modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.size(20.dp))
                OutlinedTextField(value = password,
                    onValueChange = { password = it },
                    isError = inputError,
                    singleLine = true,
                    label = { Text("Password") },
                    supportingText = {
                        if (inputError) {
                            Text("Please input the correct password")
                        } else {
                            Text("")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) Icons.Filled.Lock else Icons.Filled.Face
                            // Please provide localized description for accessibility services
                            val description =
                                if (passwordHidden) "Show password" else "Hide password"
                            Icon(imageVector = visibilityIcon, contentDescription = description)
                        }
                    })
                Spacer(modifier = Modifier.size(60.dp))
                FilledIconButton(
                    onClick = {
                        viewModel.attemptLogin(username = username, password = password)
                        if (viewModel.loginState.isLoggedIn) {
                            HustleDatabase.currentLoggedInUserId = viewModel.loginState.userId!!
                            context.startActivity(Intent(context, DashboardActivity::class.java))
                        } else {
                            inputError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text("Sign up")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CollaborateTheme {
        Surface(
            modifier = Modifier.fillMaxHeight(), color = MaterialTheme.colorScheme.background
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.4f))
            loginForm()
        }
    }
}
