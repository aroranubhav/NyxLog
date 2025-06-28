package com.maxi.nyxlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxi.debuggerduck.Duck
import com.maxi.nyxlog.ui.theme.NyxLogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NyxLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val duck = Duck.getInstance(
        context = context,
        writeToFile = true
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                duck.v(TAG, "This is a verbose log.")
            }
        ) {
            Text(text = "Verbose Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                duck.d(TAG, "This is a debug log.")
            }
        ) {
            Text(text = "Debug Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                duck.i(TAG, "This is an info log.")
            }
        ) {
            Text(text = "Info Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                duck.w(TAG, "This is a warning log.")
            }
        ) {
            Text(text = "Warning Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                duck.e(TAG, "This is an error log.")
            }
        ) {
            Text(text = "Error Log")
        }
    }
}

const val TAG = "MainActivityTAG"