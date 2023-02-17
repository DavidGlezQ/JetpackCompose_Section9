package com.davidgllez.jetpackcompose_section9.ui.component

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.davidgllez.jetpackcompose_section9.R
import java.util.*

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

fun datePickerTextField(context: Context, onValueChanged: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    val datePickerDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        val textValue = "$dayOfMonth/${month + 1}/$year"
        onValueChanged(textValue)
    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    datePickerDialog.show()
}