package com.davidgllez.jetpackcompose_section9

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidgllez.jetpackcompose_section9.ui.Utils.joinData
import com.davidgllez.jetpackcompose_section9.ui.component.AlertDialogInfo
import com.davidgllez.jetpackcompose_section9.ui.component.TfCustom
import com.davidgllez.jetpackcompose_section9.ui.component.ToolbarForm
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
                        CFrom {
                            userData = it
                        }
                    }
                )

                if (openDialog) {
                    AlertDialogInfo(
                        info = userData,
                        onDialogChange = { openDialog = false })
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
fun CFrom(inputCallBack: (String) -> Unit) { // Formulario
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
            label = stringResource(id = R.string.hint_name),
            icon = painterResource(id = R.drawable.ic_person),
            maxLength = integerResource(id = R.integer.name_max_length),
            isRequired = true) { nameValue = it }
        //Surname
        TfCustom(label = stringResource(id = R.string.hint_surname),
            icon = painterResource(id = R.drawable.ic_person),
            isRequired = true) { surNameValue = it }
        //Height
        TfCustom(label = stringResource(id = R.string.hint_height),
            icon = painterResource(id = R.drawable.ic_height),
            maxLength = integerResource(id = R.integer.height_max_length),
            isRequired = true,
            minValue = integerResource(id = R.integer.name_min_length),
            errorRes = R.string.help_min_height_valid) { heightValue = it }
        //Birth Place
        TfCustom(label = stringResource(id = R.string.hint_birth_place),
            icon = painterResource(id = R.drawable.ic_place)) { birthPlaceValue = it }
    }
}

