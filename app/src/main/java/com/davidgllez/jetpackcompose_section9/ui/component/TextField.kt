package com.davidgllez.jetpackcompose_section9.ui.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.davidgllez.jetpackcompose_section9.R
import java.util.*

/**
 * Created by davidgonzalez on 13/02/23
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun showTextField() {
    TfCustom(labelRes = R.string.app_name, iconRes = R.drawable.ic_person) { "" }
}
@Composable
fun TfCustom(modifier: Modifier = Modifier,
             paddingTop: Dp = dimensionResource(id = R.dimen.common_padding_default),
             labelRes: Int,
             iconRes: Int,
             maxLength: Int? = null,
             isRequired: Boolean = false,
             isSingleLine: Boolean = true,
             minValue: Int = 0,
             errorRes: Int = R.string.help_required,
             isLikedButton: Boolean = false,
             keyBoardOption: KeyboardOptions? = null,
             onValueChanged: (String) -> Unit) {

    //Calendar Picker Setting
    var textValue by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    val datePickerDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        textValue = "$dayOfMonth/${month + 1}/$year"
        onValueChanged(textValue)
    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))


    Column(modifier = modifier) {
        OutlinedTextField(value = textValue,
            onValueChange = {
                if (maxLength == null) {
                    textValue = it
                } else {
                    if (it.length <= maxLength) {
                        textValue = it
                    }
                }
                isError = it.isEmpty() && isRequired
                //Validacion para convertir el valor a cero en caso de ser diferente
                if (minValue > 0) isError = (textValue.toIntOrNull() ?: 0) < minValue
                onValueChanged(textValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingTop)
                //Validacion para que al momento de dar click fuera de la caja de texto no se lance el evento
                .clickable { if (isLikedButton) datePickerDialog.show() },
            label = { Text(text = stringResource(id = labelRes)) },
            keyboardOptions = KeyboardOptions(keyboardType = keyBoardOption?.keyboardType ?: KeyboardType.Text,
                 capitalization = keyBoardOption?.capitalization ?: KeyboardCapitalization.Sentences),
            leadingIcon = { Icon(painter = painterResource(id = iconRes), contentDescription = null) },
            singleLine = isSingleLine,
            isError = isError,
            readOnly = isLikedButton,
            enabled = !isLikedButton
        )
        if (isRequired) {

            Text(text = if (isError) stringResource(id = errorRes)
            else stringResource(id = R.string.help_required),
                style = MaterialTheme.typography.caption,
                color = if (isError) MaterialTheme.colors.error
                else MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    top = dimensionResource(id = R.dimen.common_padding_micro)
                ))
        }
    }
}