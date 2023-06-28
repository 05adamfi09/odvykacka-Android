package cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.model.Profile
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.BackArrowScreen
import cz.mendelu.xadamek5.project.ui.elements.MyButton
import cz.mendelu.xadamek5.project.ui.elements.ProfileImage
import cz.mendelu.xadamek5.project.ui.elements.TextInputField
import cz.mendelu.xadamek5.project.ui.screens.mainScreen.MainScreenViewModel
import cz.mendelu.xadamek5.project.ui.screens.newLog.NewLogData
import cz.mendelu.xadamek5.project.ui.screens.settingsScreen.SettingsScreenUIState
import cz.mendelu.xadamek5.project.ui.theme.smallMargin
import org.koin.androidx.compose.getViewModel


@Composable
fun UpdateProfileScreen(navigation: INavigationRouter, viewModel: UpdateProfileScreenViewModel = getViewModel()) {

    var data: UpdateProfileScreenData by remember {
        mutableStateOf(UpdateProfileScreenData())
    }

    viewModel.updateProfileScreenUIState.value.let {
        when(it){
            is UpdateProfileScreenUIState.Default -> {
                LaunchedEffect(it){
                    viewModel.loadProfile()
                }
            }
            is UpdateProfileScreenUIState.Success -> {
                data = viewModel.data
            }

            is UpdateProfileScreenUIState.ProfileUpdated -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
            is UpdateProfileScreenUIState.ProfileStateChanged -> {
                data = viewModel.data
            }
        }
    }

    BackArrowScreen(topBarTitle = stringResource(R.string.update_profile), onBackClick = { navigation.returnBack() }) {
        UpdateProfileScreenContent(paddingValues = it, data = data, actions = viewModel)
    }


}


@Composable
fun UpdateProfileScreenContent(paddingValues: PaddingValues,
    data: UpdateProfileScreenData,
actions: UpdateProfileScreenActions) {


    val imageUri = rememberSaveable { mutableStateOf(data.profile.imageUri) }



    Column(modifier = Modifier.padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally) {


        ProfileImage(imageUri)

        Text(
            text = if (data.emptyProfilePic != null) {
                "Please select your profile picture"
            } else {
                ""
            },
            modifier = Modifier
                .alpha(if (data.emptyProfilePic != null) 100f else 0f)
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, smallMargin()),
            color = Color.Red,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        TextInputField(
            value = data.profile.nickname,
            hint = stringResource(R.string.set_your_nick),
            onValueChange = { actions.onNickChange(it) },
            errorMessage = if (data.emptyNick != null) {
                stringResource(R.string.please_set_your_nick)
            } else {
                ""
            },
            maxLines = 1
        )

        TextInputField(
            value = data.profile.addiction,
            hint = stringResource(R.string.set_your_addiction),
            onValueChange = { actions.onAddictionChange(it) },
            errorMessage = if (data.emptyAddition != null) {
                stringResource(R.string.set_your_nick)
            } else {
                ""
            },
            maxLines = 1
        )


        MyButton(
            onClick = { actions.updateProfile(imageUri.value) },
            text = stringResource(id = R.string.update_profile)
        )


    }
}

