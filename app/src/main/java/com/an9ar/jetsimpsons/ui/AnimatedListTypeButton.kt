package com.an9ar.jetsimpsons.ui

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val gridListIconSize = DpPropKey(label = "gridListIconSize")
val linearListIconSize = DpPropKey(label = "linearListIconSize")
val iconOpacity = FloatPropKey(label = "iconOpacity")
val iconRotation = FloatPropKey(label = "iconRotation")

@Composable
fun AnimatedListTypeButton(
        listTypeState: ListType,
        onListTypeChanged: (ListType) -> Unit,
        modifier: Modifier
) {
    val transitionDefinition = transitionDefinition<ListType> {

        state(ListType.GRID) {
            this[gridListIconSize] = 18.dp
            this[linearListIconSize] = 4.dp
            this[iconOpacity] = 1f
            this[iconRotation] = 0f
        }

        state(ListType.LINEAR) {
            this[gridListIconSize] = 4.dp
            this[linearListIconSize] = 18.dp
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
        ListType.GRID -> ListType.LINEAR
        else -> ListType.GRID
    }

    val transitionState2 = transition(
            definition = remember { transitionDefinition },
            initState = listTypeState,
            toState = nextState
    )

    ListTypeButton(
            listTypeState = listTypeState,
            transitionState = transitionState2,
            onListTypeChanged = onListTypeChanged,
            modifier = modifier
    )
}