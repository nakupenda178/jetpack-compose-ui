package cn.idesign.cui.collapse

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Collapse(
    modifier: Modifier = Modifier,
    accordion: Boolean = false,
    activeKeys: Array<String> = arrayOf(),
    titleModifier: Modifier = Modifier,
    onChange: ((activeKeys: Array<String>) -> Unit)? = null,
    content: CollapseScope.() -> Unit,
) {
    val collapseScopeImpl = CollapseScopeImpl().apply(content)
    val itemScope = CollapseItemScopeImpl()
    var _activeKeys by remember(activeKeys) {
        val mutableListOf = mutableListOf<String>()
        mutableListOf.addAll(activeKeys)
        mutableStateOf(mutableListOf)
    }

    Column(modifier = modifier) {
        repeat(collapseScopeImpl.intervals.size) { index ->
            val item = collapseScopeImpl.intervals[index]
            val open = checkOpen(_activeKeys, item.key, accordion)
            CollapseItem(
                title = item.title,
                open = open,
                titleModifier = titleModifier,
                onClick = { innerOpen ->
                    if (accordion) {
                        if (innerOpen) {
                            _activeKeys = mutableListOf(item.key)
                        } else {
                            _activeKeys = mutableListOf()
                        }
                    } else {
                        if (innerOpen) {
                            _activeKeys.add(item.key)
                        } else {
                            _activeKeys =
                                _activeKeys.filter { it !== item.key } as MutableList<String>
                        }
                    }

                    onChange?.let { it(_activeKeys.toTypedArray()) }
                }
            ) {
                item.content.invoke(itemScope)()
            }
        }
    }
}

fun checkOpen(activeKeys: List<String>, key: String, accordion: Boolean): Boolean {
    if (activeKeys.isNullOrEmpty()) {
        return false
    }
    if (accordion) {
        return activeKeys[0] == key
    }
    return activeKeys.contains(key)
}

