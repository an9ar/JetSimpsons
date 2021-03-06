package com.an9ar.jetsimpsons.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import com.an9ar.jetsimpsons.R
import com.an9ar.jetsimpsons.models.ListType

val iconOpacity = FloatPropKey(label = "iconOpacity")
val iconRotation = FloatPropKey(label = "iconRotation")

@SuppressLint("Range")
@Composable
fun AnimatedListTypeButton(
    listTypeState: ListType,
    onListTypeChanged: (ListType) -> Unit,
    modifier: Modifier
) {
    val transitionDefinition = transitionDefinition<ListType> {

        state(ListType.NONE) {
            this[iconOpacity] = 1f
            this[iconRotation] = 0f
        }

        state(ListType.GRID) {
            this[iconOpacity] = 1f
            this[iconRotation] = 0f
        }

        state(ListType.LINEAR) {
            this[iconOpacity] = 1f
            this[iconRotation] = 0f
        }

        transition(ListType.GRID to ListType.LINEAR) {
            iconOpacity using keyframes {
                durationMillis = 500
                0f at 100
                1f at 300
            }
            iconRotation using keyframes {
                durationMillis = 500
                45f at 125
                90f at 250
                135f at 375
                180f at 500
            }
        }

        transition(ListType.LINEAR to ListType.GRID) {
            iconOpacity using keyframes {
                durationMillis = 500
                0f at 100
                1f at 300
            }
            iconRotation using keyframes {
                durationMillis = 500
                45f at 125
                90f at 250
                135f at 375
                180f at 500
            }
        }
    }

    val nextState = when (listTypeState) {
        ListType.LINEAR -> ListType.GRID
        else -> ListType.LINEAR
    }

    val transitionState = transition(
        definition = remember { transitionDefinition },
        initState = listTypeState,
        toState = nextState
    )

    ListTypeButton(
        listTypeState = listTypeState,
        transitionState = transitionState,
        onListTypeChanged = onListTypeChanged,
        modifier = modifier
    )
}

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
                painter = painterResource(id = R.drawable.ic_list_linear),
                contentDescription = null,
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
                painter = painterResource(id = R.drawable.ic_list_grid),
                contentDescription = null,
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