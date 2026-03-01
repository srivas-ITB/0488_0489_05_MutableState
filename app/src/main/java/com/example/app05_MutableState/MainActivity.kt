package com.example.app05_MutableState

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app05_MutableState.ui.theme.MyApp_Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp_Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyStateExample(modifier = Modifier.padding( innerPadding))
                }
            }
        }

    }
}


@Composable
fun MyStateExample(modifier: Modifier = Modifier) {

    Column(
        Modifier.fillMaxSize().verticalScroll( rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        State_NoFunciona()
        Spacer(modifier = Modifier.padding(10.dp))
        State_FuncionaSenseRotacio()
        Spacer(modifier = Modifier.padding(10.dp))
        State_FuncionaAmbRotacio()
        Spacer(modifier = Modifier.padding(10.dp))
        State_FuncionaAmbRotacioBy()

    }
}

@Composable
fun State_NoFunciona(){
    var counter = 0

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Aquest no funciona!!!")
        Button(onClick = { counter += 1 }) {
            Text(text = "Push")
        }
        Text(text = "$counter times clicked")
    }
}

@Composable
fun State_FuncionaSenseRotacio(){
    val counter = remember{ mutableStateOf(0) }
    Column( verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Aquest no funciona amb rotació.")
        Button(onClick = { counter.value += 1 }) {
            Text(text = "Push")
        }
        Text(text = "${counter.value} times clicked")
    }
}

@Composable
fun State_FuncionaAmbRotacio(){
    val counter = rememberSaveable{ mutableStateOf(0) }
    Column( verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Aquest sí que funciona!!!")
        Button(onClick = { counter.value += 1 }) {
            Text(text = "Push")
        }
        Text(text = "${counter.value} times clicked")
    }
}

@Composable
fun State_FuncionaAmbRotacioBy(){
    var counter by rememberSaveable{ mutableStateOf(0) }
    Column( verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Aquest també funciona i amb by és més fàcil!")
        Button(onClick = { counter += 1 }) {
            Text(text = "Push")
        }
        Text(text = "$counter times clicked")
    }
}