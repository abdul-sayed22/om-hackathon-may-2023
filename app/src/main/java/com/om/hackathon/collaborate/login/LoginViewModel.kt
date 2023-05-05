package com.om.hackathon.collaborate.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.om.hackathon.collaborate.data.HustleDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val userId: Int? = null,
    val isLoggedIn: Boolean = false,
    val error: String? = null
)

class LoginViewModel : ViewModel() {
    var loginState = LoginState()

    fun attemptLogin(username: String, password: String) {
        HustleDatabase.users.find { it.username.lowercase() == username.lowercase() }?.let {
            loginState = LoginState(userId = it.id, isLoggedIn = true)
            return
        }

        loginState = LoginState(error = "No user found with those credentials", isLoggedIn = false)
    }
}