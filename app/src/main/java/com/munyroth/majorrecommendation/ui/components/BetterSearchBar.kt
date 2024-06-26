package com.munyroth.majorrecommendation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.munyroth.majorrecommendation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetterSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    result: @Composable () -> Unit
) {
    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var isClear by remember { mutableStateOf(false) }

    DockedSearchBar(
        modifier = modifier
            .animateContentSize()
            .then(
                if (isActive) Modifier.fillMaxSize()
                else Modifier.fillMaxWidth(0.9F)
            ),
        query = query,
        onQueryChange = {
            query = it
            if (it.isNotEmpty()) {
                isClear = false
                onSearch(query)
            } else {
                isClear = true
            }
        },
        onSearch = {
            // This work when user press enter
        },
        active = isActive,
        onActiveChange = { isActive = it },
        placeholder = { Text(text = stringResource(id = R.string.search_hint)) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        trailingIcon = if (isActive) {
            {
                IconButton(
                    onClick = {
                        if (query.isNotEmpty()) {
                            query = ""
                            isClear = true
                        } else isActive = false
                    }) {
                    Icon(Icons.Filled.Close, contentDescription = null)
                }
            }
        } else null
    ) {
        if (!isClear) {
            result()
        }
    }
}