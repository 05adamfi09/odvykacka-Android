package cz.mendelu.xadamek5.project.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import cz.mendelu.xadamek5.project.ui.theme.getBasicTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackArrowScreen(
    topBarTitle: String,
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit
){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = topBarTitle, color = getBasicTextColor())
        },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }

            }, actions = actions)
    }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                content(it)
            }
        }
    }
}