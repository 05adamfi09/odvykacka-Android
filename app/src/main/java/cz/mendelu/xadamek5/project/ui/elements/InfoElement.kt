package cz.mendelu.xadamek5.project.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.ui.theme.getTintColor
import cz.mendelu.xadamek5.project.ui.theme.smallMargin


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoElement(
    value: String?,
    hint: String,
    leadingIcon: Int,
    onClick: () -> Unit,
    onClearClick: () -> Unit,
    errorMessage: String){

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    // Je nutné kvůli správnému chování labelu. Jinak po kliknutí na křížek zůstane nahoře.
    val focusManager = LocalFocusManager.current

    if (isPressed) {
        LaunchedEffect(isPressed){
            onClick()
        }
    }

    OutlinedTextField(
        value = if (value != null) value else "",
        onValueChange = {},
        interactionSource = interactionSource,
        leadingIcon = {Icon(
            painter = painterResource(id = leadingIcon),
            tint = getTintColor(),
            contentDescription = null
        )}
        ,
        trailingIcon = if (value != null) {
            {
                IconButton(
                    onClick = {
                        onClearClick()
                        focusManager.clearFocus()
                    }) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Filled.Clear),
                        tint = getTintColor(),
                        contentDescription = stringResource(R.string.clear)
                    )
                }
            }

        } else {
            null
        },
        label = {Text(text = hint)},
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    )

    Text(
        text = errorMessage,
        modifier = Modifier
            .alpha(if (errorMessage.isNotEmpty()) 100f else 0f)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, smallMargin()),
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}