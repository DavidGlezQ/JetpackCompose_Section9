package com.davidgllez.jetpackcompose_section9.ui.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
@OptIn(ExperimentalComposeUiApi::class)
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
             clearValue: Boolean = false,
             onValueChanged: (String) -> Unit) {

    val context = LocalContext.current

    //Calendar Picker Setting
    var textValue by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    if(clearValue) textValue = ""

    //OutLinedTextField
    Column(modifier = modifier) {
        val keyboard = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
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
                .clickable { if (isLikedButton) datePickerTextField(context) {
                    textValue = it
                    onValueChanged(textValue)
                } },
            label = { Text(text = stringResource(id = labelRes)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyBoardOption?.keyboardType ?: KeyboardType.Text,
                capitalization = keyBoardOption?.capitalization ?: KeyboardCapitalization.Sentences,
                imeAction =
                if (keyBoardOption == null || keyBoardOption.imeAction == ImeAction.Default)
                    ImeAction.Next
                else keyBoardOption.imeAction),
            keyboardActions = KeyboardActions(
                onDone = { keyboard?.hide() },
                //Diferentes opciones de teclado
                onNext = { focusManager.moveFocus(FocusDirection.Next /* FocusDirection.Up */) }),
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

@Composable
fun CounterMaxLength(currentLength: Int, maxLengthRes: Int) {
    Text(text = "$currentLength/${integerResource(id = maxLengthRes)}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.common_padding_micro)),
        textAlign = TextAlign.Right,
        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        style = MaterialTheme.typography.caption
    )
}