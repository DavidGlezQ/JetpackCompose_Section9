package com.davidgllez.jetpackcompose_section9.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
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
fun EtCustom(paddingTop: Dp = dimensionResource(id = R.dimen.common_padding_default),
             label: String,
             icon: Painter,
             onValueChanged: (String) -> Unit) {
    var textValue by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(value = textValue,
            onValueChange = {
                textValue = it
                isError = it.isEmpty()
                onValueChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingTop),
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Words),
            leadingIcon = { Icon(painter = icon, contentDescription = null) }
        )
        Text(text = stringResource(id = R.string.help_required),
            style = MaterialTheme.typography.caption,
            color = if (isError) MaterialTheme.colors.error
            else MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.common_padding_default),
                top = dimensionResource(id = R.dimen.common_padding_micro)
            ))
    }
}