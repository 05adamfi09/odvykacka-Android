package cz.mendelu.xadamek5.project.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.lifecycleScope
import cz.mendelu.xadamek5.project.architecture.BaseActivity
import cz.mendelu.xadamek5.project.navigation.Destination
import cz.mendelu.xadamek5.project.navigation.NavGraph
import cz.mendelu.xadamek5.project.ui.activities.viewmodels.CreateProfileActivityViewModel
import cz.mendelu.xadamek5.project.ui.elements.MyButton
import cz.mendelu.xadamek5.project.ui.theme.ProjectTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.functions.ImagePicker
import cz.mendelu.xadamek5.project.functions.saveImageToLocal
import cz.mendelu.xadamek5.project.model.Profile
import cz.mendelu.xadamek5.project.navigation.INavigationRouter
import cz.mendelu.xadamek5.project.ui.elements.ProfileImage
import cz.mendelu.xadamek5.project.ui.elements.TextInputField
import cz.mendelu.xadamek5.project.ui.theme.smallMargin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel



class CreateProfileActivity : AppCompatActivity() {

    val viewModel: CreateProfileActivityViewModel by viewModel()

    companion object {
        fun createIntent(context: AppCompatActivity): Intent {
            return Intent(context, CreateProfileActivity::class.java)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme() {

                Scaffold(topBar = { TopAppBar(title = { Text(text = getString(R.string.create_new_profile)) }) },
                    content = {
                        NewProfileScreenActivityContent(paddingValues = it)
                    }
                )


            }
        }
    }

    private fun continueToMainActivity() {
        lifecycleScope.launch {
            viewModel.setFirstRun()
        }.invokeOnCompletion {
            startActivity(MainActivity.createIntent(this))
            finish()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NewProfileScreenActivityContent(paddingValues: PaddingValues ) {

        var nickname by remember {
            mutableStateOf("")
        }

        var addiction by remember {
            mutableStateOf("")
        }


        val imageUri = rememberSaveable { mutableStateOf("") }

        var nicknameEmpty by remember {
            mutableStateOf(0)
        }
        var addictionEmpty by remember {
            mutableStateOf(0)
        }
        var profilePicEmpty by remember {
            mutableStateOf(0)
        }



        Column(modifier = Modifier.padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally) {


            ProfileImage(imageUri)

            Text(
                text = if (profilePicEmpty != 0) {
                    stringResource(R.string.select_your_profile_picture)
                } else {
                    ""
                },
                modifier = Modifier
                    .alpha(if (profilePicEmpty != 0) 100f else 0f)
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, smallMargin()),
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            TextInputField(
                value = nickname,
                hint = stringResource(R.string.set_your_nick),
                onValueChange = { nickname = it },
                errorMessage = if (nicknameEmpty != 0) {
                    stringResource(R.string.please_set_your_nick)
                } else {
                    ""
                },
                maxLines = 1
            )

            TextInputField(
                value = addiction,
                hint = stringResource(R.string.set_your_addiction),
                onValueChange = { addiction = it },
                errorMessage = if (addictionEmpty != 0) {
                    stringResource(R.string.set_your_addiction)
                } else {
                    ""
                },
                maxLines = 1
            )


            MyButton(
                onClick = {
                    if (imageUri.value != "" && addiction != "" && nickname != "") {
                        viewModel.saveProfile(Profile(imageUri.value, nickname, addiction))
                        continueToMainActivity()
                    } else {
                        nicknameEmpty = 0
                        addictionEmpty = 0
                        profilePicEmpty = 0

                        if (nickname == "") {
                            nicknameEmpty = 1
                        }
                        if (addiction == "") {
                            addictionEmpty = 1
                        }
                        if (imageUri.value == "") {
                            profilePicEmpty = 1
                        }
                    }
                },
                text = stringResource(R.string.save)
            )


        }
    }



}


