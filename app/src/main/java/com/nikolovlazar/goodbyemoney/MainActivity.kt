package com.nikolovlazar.goodbyemoney

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikolovlazar.goodbyemoney.pages.*
import com.nikolovlazar.goodbyemoney.ui.theme.GoodbyeMoneyTheme
import com.nikolovlazar.goodbyemoney.ui.theme.TopAppBarBackground
import io.sentry.compose.withSentryObservableEffect

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    setContent {
      GoodbyeMoneyTheme {
        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        val navController = rememberNavController().withSentryObservableEffect()
        val backStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (backStackEntry?.destination?.route) {
          "settings/categories", "login", "register" -> false
          else -> true
        }

        Scaffold(
          bottomBar = {
            if (showBottomBar) {
              NavigationBar(containerColor = TopAppBarBackground) {
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route == "expenses",
                  onClick = { navController.navigate("expenses") },
                  label = {
                    Text("Затраты")
                  },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.upload),
                      contentDescription = "Upload"
                    )
                  }
                )
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route == "reports",
                  onClick = { navController.navigate("reports") },
                  label = {
                    Text("Отчет")
                  },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.bar_chart),
                      contentDescription = "Reports"
                    )
                  }
                )
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route == "add",
                  onClick = { navController.navigate("add") },
                  label = {
                    Text("Добавить")
                  },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.add),
                      contentDescription = "Add"
                    )
                  }
                )
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route?.startsWith("settings")
                    ?: false,
                  onClick = { navController.navigate("settings") },
                  label = {
                    Text("Настройки")
                  },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.settings_outlined),
                      contentDescription = "Settings"
                    )
                  }
                )
              }
            }
          },
          content = { innerPadding ->
            NavHost(
              navController = navController,
              startDestination = "login"
            ) {
              composable("login") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  LoginScreen(navController)
                }
              }
              composable("register") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  RegisterScreen(navController)
                }
              }
              composable("expenses") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  Expenses(navController)
                }
              }
              composable("reports") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  Reports()
                }
              }
              composable("add") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  Add(navController)
                }
              }
              composable("settings") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  Settings(navController)
                }
              }
              composable("settings/categories") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                ) {
                  Categories(navController)
                }
              }
            }
          }
        )
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
  GoodbyeMoneyTheme {
    Surface {
      Greeting("Android")
    }
  }
}