package ru.chiya.gameprices.presentation.games_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.chiya.gameprices.R
import ru.chiya.gameprices.presentation.games_list.GamesListViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GamesListScreen(
    navController: NavController, viewModel: GamesListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val search = remember {
        mutableStateOf("Batman")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = search.value,
            onValueChange = { search.value = it },
            shape = MaterialTheme.shapes.extraLarge,
            placeholder = {
                Text(text = "Search")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search"
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                viewModel.getGames(search.value)
            }),
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
        )

        LazyColumn {
            items(state.games.size) {
                val game = state.games[it]
                GamesListItem(game = game, onClick = {})
            }
        }
        if (state.isLoading) {
            PlaceAtCenter {
                CircularProgressIndicator()
            }
        }
        if (state.games.isEmpty()) {
            PlaceAtCenter {
                Text(text = "Nothing found...", color = MaterialTheme.colorScheme.error)
            }
        }
        if (state.error != "") {
            PlaceAtCenter {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
