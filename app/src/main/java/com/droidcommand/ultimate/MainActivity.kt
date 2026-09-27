package com.droidcommand.ultimate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var refreshed by remember { mutableIntStateOf(0) }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        Text("DroidCommand Ultimate", style = MaterialTheme.typography.headlineMedium)
                        Text("Enterprise device management and consent-based remote support")
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("Device status: Ready (local demo)")
                                Text("Enrollment: Not configured")
                                Text("Remote support: Off")
                                Text("Status refresh: $refreshed")
                            }
                        }
                        Text(
                            "This build is a transparent starter shell. It does not silently capture the screen, control input, or claim Device Owner privileges. Enterprise enrollment and any remote-support session require supported provisioning, explicit on-device authorization, visible indicators, and an accessible stop control."
                        )
                        Button(onClick = { refreshed++ }) { Text("Refresh status") }
                    }
                }
            }
        }
    }
}
