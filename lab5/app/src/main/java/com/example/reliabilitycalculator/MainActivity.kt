package com.example.reliabilitycalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NavigationButton(
            buttonText = "Перейти до Завдання 1",
            onClick = { navigateToActivity(context, Task1Activity::class.java) }
        )
        NavigationButton(
            buttonText = "Перейти до Завдання 2",
            onClick = { navigateToActivity(context, Task2Activity::class.java) }
        )
    }
}

@Composable
fun NavigationButton(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(buttonText)
    }
}

fun <T> navigateToActivity(context: android.content.Context, destination: Class<T>) {
    val intent = Intent(context, destination)
    context.startActivity(intent)
}