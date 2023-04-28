package ru.chiya.gameprices.presentation.games_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.chiya.gameprices.domain.model.Game

@Composable
fun GamesListItem(
    game: Game,
    onClick: (Game) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(game) }
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = game.external,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(32.dp))
        Text(text = "${game.cheapest}$", maxLines = 1)
    }
}
