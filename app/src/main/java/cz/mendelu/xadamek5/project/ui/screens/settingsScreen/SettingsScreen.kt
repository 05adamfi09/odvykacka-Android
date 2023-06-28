package cz.mendelu.xadamek5.project.ui.screens.settingsScreen

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import cz.mendelu.xadamek5.project.BuildConfig
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.BackArrowScreen
import cz.mendelu.xadamek5.project.ui.elements.MyButton
import cz.mendelu.xadamek5.project.ui.elements.ProfileImage
import cz.mendelu.xadamek5.project.ui.screens.mainScreen.MainScreenContent
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navigation: INavigationRouter, viewModel: SettingsScreenViewModel = getViewModel()){


    var profile = remember { mutableStateOf(Profile("", "unknown", "unknown")) }


    viewModel.settingsScreenUIState.value.let {
        when(it){
            is SettingsScreenUIState.Default -> {
                LaunchedEffect(it){
                    viewModel.loadProfile()
                }
            }
            is SettingsScreenUIState.Success -> {
                profile.value = it.profile
            }
        }
    }

/*
    BackArrowScreen(topBarTitle = stringResource(id = R.string.settings), onBackClick = { navigation.returnBack() }) {
        SettingsScreenContent(paddingValues = it, profile = profile)
        
    }
    
 */

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.settings))},
                navigationIcon = {
                    IconButton(onClick = { navigation.returnBack()}) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ){
        SettingsScreenContent(paddingValues = it, profile = profile, navigation = navigation)
    }


}



@Composable
fun SettingsScreenContent(paddingValues: PaddingValues,
                          profile: MutableState<Profile>,
                          navigation: INavigationRouter){

    val imageUri = Uri.parse(profile.value.imageUri)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape, modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
            ) {
                Image(
                    painter = rememberImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Text(stringResource(R.string.nickname) + ": " + profile.value.nickname, fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.addiction) + ": " + profile.value.addiction, fontWeight = FontWeight.Bold)
        }




        MyButton(onClick = { navigation.navigateToUpdateProfileScreen() }, text = stringResource(R.string.update_profile))


        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
            Text(BuildConfig.VERSION_NAME)
        }
    }


}