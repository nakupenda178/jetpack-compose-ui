package cn.idesign.cui.addresslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddressItem(
    modifier: Modifier = Modifier,
    data: AddressModel,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium),
    secondaryTextStyle: TextStyle = MaterialTheme.typography.caption.copy(
        MaterialTheme.colors.onSurface.copy(
            ContentAlpha.disabled
        )
    ),
    divider: Boolean = true,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 10.dp)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (data.defaultAddress) {
                    Box(Modifier.padding(end = 5.dp)) {
                        Text(
                            text = "默认",
                            style = secondaryTextStyle.copy(Color.White),
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colors.primary, RoundedCornerShape(2.dp)
                                )
                                .padding(horizontal = 5.dp)
                        )
                    }
                }
                data.tag?.let { tag ->
                    Box(Modifier.padding(end = 5.dp)) {
                        Text(
                            text = tag,
                            style = secondaryTextStyle.copy(Color.White),
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colors.primary, RoundedCornerShape(2.dp)
                                )
                                .padding(horizontal = 5.dp)
                        )
                    }
                }
                Text(text = data.area, style = secondaryTextStyle)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            ) {
                Text(text = data.address, style = textStyle)
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .size(16.dp)
                        .padding(10.dp)
                        .alpha(ContentAlpha.medium)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = data.receiver, style = secondaryTextStyle)
                Spacer(modifier = Modifier.width(48.dp))
                Text(text = data.phoneNumber, style = secondaryTextStyle)
            }

        }
        if (divider) {
            Divider(modifier = Modifier.align(Alignment.BottomStart))

        }
    }
}