package com.an9ar.jetsimpsons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> LazyGridFor(
    items: List<T> = listOf(),
    rows: Int = 3,
    hPadding: Int = 0,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val chunkedList = items.chunked(rows)
    LazyColumnForIndexed(items = chunkedList, modifier = Modifier.padding(horizontal = hPadding.dp)) { index, item ->
        if (index == 0) {
            Spacer(modifier = Modifier.preferredHeight(8.dp))
        }

        Row {
            item.forEachIndexed { rowIndex, item ->
                Box(modifier = Modifier.weight(1F).align(Alignment.Top).padding(8.dp), alignment = Alignment.Center) {
                    itemContent(item, index * rows + rowIndex)
                }
            }
            repeat(rows - item.size) {
                Box(modifier = Modifier.weight(1F).padding(8.dp)) {}
            }
        }
    }
}