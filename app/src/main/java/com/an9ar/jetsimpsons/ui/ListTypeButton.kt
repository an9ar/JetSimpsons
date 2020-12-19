package com.an9ar.jetsimpsons.ui

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.vectorResource
import com.an9ar.jetsimpsons.R

@Composable
fun ListTypeButton(
    listTypeState: ListType,
    onListTypeChanged: (ListType) -> Unit,
    transitionState: TransitionState,
    modifier: Modifier
) {

    when (listTypeState) {
        ListType.LINEAR -> {
            Icon(
                    imageVector = vectorResource(id = R.drawable.ic_list_linear),
                    modifier = modifier
                            .alpha(transitionState[iconOpacity])
                            .rotate(transitionState[iconRotation])
                            .clickable(onClick = {
                                onListTypeChanged(ListType.GRID)
                            })
            )
        }
        else -> {
            Icon(
                imageVector = vectorResource(id = R.drawable.ic_list_grid),
                modifier = modifier
                    .alpha(transitionState[iconOpacity])
                    .rotate(transitionState[iconRotation])
                    .clickable(onClick = {
                        onListTypeChanged(ListType.LINEAR)
                    })
            )
        }
    }
}