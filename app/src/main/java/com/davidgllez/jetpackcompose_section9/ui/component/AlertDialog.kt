package com.davidgllez.jetpackcompose_section9.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.davidgllez.jetpackcompose_section9.R

/**
 * Created by davidgonzalez on 12/02/23
 */
@Composable
fun AlertDialogInfo(info: String, onDialogChange: (Boolean) -> Unit) {
    AlertDialog(onDismissRequest = {onDialogChange(false) },
        title = { Text(text = stringResource(id = R.string.dialog_title))}, 
        text = { Text(text = info)}, 
        confirmButton = { 
            TextButton(onClick = { onDialogChange(true) }) {
                Text(text = stringResource(id = R.string.dialog_ok))
            }
        }, dismissButton = {
            TextButton(onClick = { onDialogChange(false) }) {
                Text(text = stringResource(id = R.string.dialog_cancel))
            }
        })
}