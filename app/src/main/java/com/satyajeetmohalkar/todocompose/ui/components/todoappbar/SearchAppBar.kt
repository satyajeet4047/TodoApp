package com.satyajeetmohalkar.todocompose.ui.components.todoappbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarBackgroundColor
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor

@Composable
fun SearchAppbar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { newText ->
                onSearchQueryChange(newText)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search Tasks",
                    color = Color.LightGray
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle2.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier
                    .alpha(ContentAlpha.medium),
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Field Icon",
                        tint = MaterialTheme.colors.topAppBarContentColor)
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if(searchQuery.isEmpty()) {
                        onSearchCloseClicked()
                    } else {
                        onSearchQueryChange("")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close icon",
                        tint = MaterialTheme.colors.topAppBarContentColor)
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchQueryChange(searchQuery)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )

    }
}


@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppbar(searchQuery = "", onSearchQueryChange = {}, onSearchCloseClicked = {})
}