package com.davidgllez.jetpackcompose_section9.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.davidgllez.jetpackcompose_section9.R

/**
 * Created by davidgonzalez on 13/02/23
 */

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
             onValueChanged: (String) -> Unit) {

    var textValue by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

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
                .padding(top = paddingTop),
            label = { Text(text = stringResource(id = labelRes)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words),
            leadingIcon = { Icon(painter = painterResource(id = iconRes), contentDescription = null) },
            singleLine = isSingleLine
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