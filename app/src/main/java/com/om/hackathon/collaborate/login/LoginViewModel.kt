package com.om.hackathon.collaborate.login

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
    var loginState = MutableStateFlow(LoginState())

    fun attemptLogin(username: String, password: String) {
        HustleDatabase.users.find { it.username == username }?.let {
            loginState.update {
                LoginState(userId = it.userId, isLoggedIn = true)
            }
            return
        }

        loginState.update {
            LoginState(error =  "No user found with those credentials")
        }
    }
}