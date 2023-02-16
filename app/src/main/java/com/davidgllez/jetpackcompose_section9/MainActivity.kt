package com.davidgllez.jetpackcompose_section9

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.davidgllez.jetpackcompose_section9.ui.Utils.joinData
import com.davidgllez.jetpackcompose_section9.ui.component.*
import com.davidgllez.jetpackcompose_section9.ui.theme.JetpackCompose_Section9Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_Section9Theme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                var openDialog by remember { mutableStateOf(false) }
                var userData by remember { mutableStateOf("") }

                var clearForm by remember { mutableStateOf(false) }
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = { ToolbarForm {
                        if (userData.isNotEmpty()) {
                            openDialog = true
                        } else {
                            scope.launch { scaffoldState.snackbarHostState
                                .showSnackbar(getString(R.string.app_bar_invalid_form)) }
                        }
                        openDialog = true
                    } },
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    content = {
                        CFrom(clearForm = clearForm) {
                            userData = it
                            if (clearForm && userData.isEmpty()) {
                                clearForm = false
                            }
                        }
                    }
                )

                if (openDialog) {
                    AlertDialogInfo(
                        info = userData,
                        onDialogChange = { clear ->
                            openDialog = false
                            clearForm = clear
                        })
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackCompose_Section9Theme {
        CFrom {

        }
    }
}

@Composable
fun CFrom(clearForm: Boolean = false, inputCallBack: (String) -> Unit) { // Formulario
    var nameValue by remember { mutableStateOf("") }
    var surNameValue by remember { mutableStateOf("") }
    var heightValue by remember { mutableStateOf("") }
    var birthDateValue by remember { mutableStateOf("") }
    var countryValue by remember { mutableStateOf("") }
    var birthPlaceValue by remember { mutableStateOf("") }
    var notesValue by remember { mutableStateOf("") }

    if (nameValue.isEmpty() || surNameValue.isEmpty() || heightValue.isEmpty()) {
        inputCallBack("")
    } else {
        inputCallBack(joinData(nameValue, surNameValue, heightValue, birthDateValue,
            countryValue, birthDateValue, notesValue))
    }

    Column(modifier = Modifier
        .padding(dimensionResource(id = R.dimen.common_padding_default))
        .verticalScroll(rememberScrollState())) {
        //Name
        TfCustom(paddingTop = dimensionResource(id = R.dimen.common_padding_nano),
            labelRes = R.string.hint_name ,
            iconRes = R.drawable.ic_person ,
            maxLength = integerResource(id = R.integer.name_max_length),
            isRequired = true,
            keyBoardOption = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            clearValue = clearForm
        ) { nameValue = it }
        //Surname
        TfCustom(labelRes = R.string.hint_surname,
            iconRes = R.drawable.ic_person,
            isRequired = true,
            keyBoardOption = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            clearValue = clearForm
        ) { surNameValue = it }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.common_padding_default))) {
            //Height
            TfCustom(modifier = Modifier.weight(40f), labelRes = R.string.hint_height,
                iconRes = R.drawable.ic_height,
                maxLength = integerResource(id = R.integer.height_max_length),
                isRequired = true,
                minValue = integerResource(id = R.integer.name_min_length),
                errorRes = R.string.help_min_height_valid,
                keyBoardOption = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                clearValue = clearForm
            ) { heightValue = it }
            //BirthDate
            TfCustom(modifier = Modifier.weight(60f),
                labelRes = R.string.hint_birth_place,
                iconRes = R.drawable.ic_calendar_today,
                isLikedButton = true,
                clearValue = clearForm
            ) { birthDateValue = it }
        }
        //Countries
        SpCountries()
        //Birth Place
        TfCustom(labelRes = R.string.hint_birth_place,
            iconRes = R.drawable.ic_place,
            clearValue = clearForm
        ) { birthPlaceValue = it }
        //Notes
        TfCustom(
            labelRes = R.string.hint_notes,
            iconRes = R.drawable.ic_notes,
            isSingleLine = false,
            maxLength = integerResource(id = R.integer.notes_max_length),
            keyBoardOption = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            clearValue = clearForm
        ) { notesValue = it }
        CounterMaxLength(currentLength = notesValue.length, maxLengthRes = R.integer.notes_max_length)
    }
}

