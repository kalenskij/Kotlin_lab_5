package com.example.reliabilitycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Task2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Task2Screen(onBackPressed = { finish() })
            }
        }
    }
}

@Composable
fun Task2Screen(onBackPressed: () -> Unit) {
    // User inputs
    var omega by remember { mutableStateOf("") }
    var tb by remember { mutableStateOf("") }
    var Pm by remember { mutableStateOf("") }
    var Tm by remember { mutableStateOf("") }
    var kp by remember { mutableStateOf("") }
    var zPerA by remember { mutableStateOf("") }
    var zPerP by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Calculation function
    fun calculateTask2() {
        val omegaValue = omega.toDoubleOrNull() ?: 0.0
        val tbValue = tb.toDoubleOrNull() ?: 0.0
        val PmValue = Pm.toDoubleOrNull() ?: 0.0
        val TmValue = Tm.toDoubleOrNull() ?: 0.0
        val kpValue = kp.toDoubleOrNull() ?: 0.0
        val zPerAValue = zPerA.toDoubleOrNull() ?: 0.0
        val zPerPValue = zPerP.toDoubleOrNull() ?: 0.0

        val MWA = omegaValue * tbValue * PmValue * TmValue // Expected outage
        val MWP = kpValue * PmValue * TmValue // Planned outage
        val M = zPerAValue * MWA + zPerPValue * MWP // Total loss expectation

        result = """
            Математичне сподівання аварійного недовідпущення: ${"%.5f".format(MWA)} кВт·год
            Математичне сподівання планового недовідпущення: ${"%.5f".format(MWP)} кВт·год
            Математичне сподівання збитків від перервання електропостачання: ${"%.5f".format(M)} грн
        """.trimIndent()
    }

    // Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Task2InputField(
            label = "Частота відмов ω",
            value = omega,
            onValueChange = { omega = it }
        )
        Task2InputField(
            label = "Середній час відновлення t_b (у роках)",
            value = tb,
            onValueChange = { tb = it }
        )
        Task2InputField(
            label = "Середнє споживання потужності Pm (кВт·год)",
            value = Pm,
            onValueChange = { Pm = it }
        )
        Task2InputField(
            label = "Тривалість Tm (години)",
            value = Tm,
            onValueChange = { Tm = it }
        )
        Task2InputField(
            label = "Коефіцієнт kp",
            value = kp,
            onValueChange = { kp = it }
        )
        Task2InputField(
            label = "Zпер.а (грн/кВт·год)",
            value = zPerA,
            onValueChange = { zPerA = it }
        )
        Task2InputField(
            label = "Zпер.п (грн/кВт·год)",
            value = zPerP,
            onValueChange = { zPerP = it }
        )
        Button(onClick = { calculateTask2() }, modifier = Modifier.fillMaxWidth()) {
            Text("Розрахувати")
        }
        Button(onClick = onBackPressed, modifier = Modifier.fillMaxWidth()) {
            Text("Назад")
        }
        Text(text = result, modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun Task2InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}