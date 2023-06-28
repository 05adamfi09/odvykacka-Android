package cz.mendelu.xadamek5.project.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.xadamek5.project.ui.theme.smallMargin


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(value: String,
                   hint: String,
                   onValueChange: ((String) -> Unit),
                   errorMessage: String,
                    maxLines: Int){
    OutlinedTextField(
        value = value,
        label = { Text(text = hint) },
        onValueChange = onValueChange,
        isError = false,
        minLines = maxLines,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp))

    Text(
        text = errorMessage,
        modifier = Modifier
            .alpha(if (errorMessage.isNotEmpty()) 100f else 0f)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, smallMargin()),
        color = Red,
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )

}