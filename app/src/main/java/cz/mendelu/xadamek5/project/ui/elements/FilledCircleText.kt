package cz.mendelu.xadamek5.project.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilledCircleText(text: String, backgroundColor: Color, textColor: Color, size: Int, fontSize: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .fillMaxSize()
            .then(Modifier.background(color = backgroundColor, shape = CircleShape)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}