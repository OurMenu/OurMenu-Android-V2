package com.kuit.ourmenu.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun PermissionHandler(
    permission: String,
    rationaleMessage: String,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val showRationaleDialog = remember { mutableStateOf(false) }
    
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }
            (context as? Activity)?.shouldShowRequestPermissionRationale(permission) == true -> {
                showRationaleDialog.value = true
            }
            else -> {
                launcher.launch(permission)
            }
        }
    }

    if (showRationaleDialog.value) {
        AlertDialog(
            onDismissRequest = { showRationaleDialog.value = false },
            title = { Text("Permission Required") },
            text = { Text(rationaleMessage) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showRationaleDialog.value = false
                        launcher.launch(permission)
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { 
                        showRationaleDialog.value = false
                        onPermissionDenied()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
