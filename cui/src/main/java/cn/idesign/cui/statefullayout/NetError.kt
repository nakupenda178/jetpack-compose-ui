package cn.idesign.cui.statefullayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NetError(
    modifier: Modifier = Modifier,
    text: String = "网络错误",
    textStyle: TextStyle = MaterialTheme.typography.body2,
    secondaryText: String? = "请检查网络连接后重试",
    secondaryTextStyle: TextStyle = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
    textColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
    secondaryTextColor: Color = MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled),
    image: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.WifiOff,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = textColor
        )
    },
    onClick: (() -> Unit)? = null,
) {
    val clickState = rememberUpdatedState(onClick)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        image()
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
        secondaryText?.let {
            Text(
                text = secondaryText,
                style = secondaryTextStyle,
                color = secondaryTextColor,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = { clickState.value?.invoke() },
            modifier = Modifier
                .width(80.dp)
                .padding(top = 8.dp)
        ) {
            Text(text = "重试")
        }
    }
}