package com.nikolovlazar.goodbyemoney.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nikolovlazar.goodbyemoney.db
import com.nikolovlazar.goodbyemoney.models.User
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.query.find
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    registerUser(email, password, navController, setError = { errorMessage = it })
                }
            }) {
                Text("Создать")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Уже есть аккаунта?",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

suspend fun registerUser(email: String, password: String, navController: NavController, setError: (String) -> Unit) {
    withContext(Dispatchers.IO) {
        val existingUser: RealmResults<User> = db.query<User>("email == $0", email).find()
        if (existingUser.isNotEmpty()) {
            withContext(Dispatchers.Main) {
                setError("Такой аккаунт уже существует")
            }
        } else {
            db.write {
                val newUser = User().apply {
                    this.email = email
                    this.password = password
                }
                copyToRealm(newUser)
            }
            withContext(Dispatchers.Main) {
                navController.navigate("login")
            }
        }
    }
}