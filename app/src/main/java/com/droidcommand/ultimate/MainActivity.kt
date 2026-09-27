package com.droidcommand.ultimate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
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

data class LocalDeviceStatus(
    val model: String,
    val androidVersion: String,
    val battery: String,
    val network: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var status by remember { mutableStateOf(readStatus()) }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text("DroidCommand Ultimate", style = MaterialTheme.typography.headlineMedium)
                        Text("Device management and consent-based remote support")
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("LOCAL DEVICE STATUS", style = MaterialTheme.typography.titleSmall)
                                Text("Model: ${status.model}")
                                Text("Android: ${status.androidVersion}")
                                Text("Battery: ${status.battery}")
                                Text("Network: ${status.network}")
                            }
                        }
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("MANAGEMENT", style = MaterialTheme.typography.titleSmall)
                                Text("Enrollment: Not enrolled")
                                Text("Trusted administrator: Not paired")
                                Text("Remote support: Off")
                                Text("No remote session is active.")
                            }
                        }
                        Button(onClick = { status = readStatus() }, modifier = Modifier.fillMaxWidth()) {
                            Text("Refresh device status")
                        }
                        Text(
                            "This MVP reads local device and connectivity information. It does not yet connect to a management server, enroll as Device Owner, capture the screen, or inject remote input. Those capabilities require backend configuration and separate Android-managed enrollment or explicit user authorization.",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            "Safety: pairing and device management must be authorized by the device owner. Screen sharing must remain visible and stoppable; persistent trust never silently restarts capture or control.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }

    private fun readStatus(): LocalDeviceStatus {
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val level = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivity.activeNetwork
        val caps = network?.let { connectivity.getNetworkCapabilities(it) }
        val networkName = when {
            caps == null -> "Offline"
            caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "Wi-Fi"
            caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Cellular"
            caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "Ethernet"
            else -> "Connected (other)"
        }
        return LocalDeviceStatus(
            model = "${Build.MANUFACTURER} ${Build.MODEL}",
            androidVersion = "${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})",
            battery = if (level in 0..100) "$level%" else "Unavailable",
            network = networkName
        )
    }
}
