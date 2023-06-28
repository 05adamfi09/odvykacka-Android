package cz.mendelu.xadamek5.project.ui.elements

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.xadamek5.project.ui.theme.getBasicTextColor

@Composable
fun MyButton(
    onClick: () -> Unit,
    text: String
){
    Button(onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
    }

}