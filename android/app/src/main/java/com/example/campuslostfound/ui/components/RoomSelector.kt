package com.example.campuslostfound.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.campuslostfound.R
import com.example.campuslostfound.domain.model.Room
import com.example.campuslostfound.ui.theme.Radius

/**
 * Reusable Material 3 room selector/input component allowing users to either select
 * a campus room preset from a dropdown menu or enter a custom room/area location name.
 *
 * @param roomText Current text content displayed in the room input field.
 * @param onRoomTextChanged Callback triggered when the input text changes.
 * @param onRoomSelected Callback triggered when a preset [Room] option is selected from the dropdown menu.
 * @param modifier Custom layout modifier.
 * @param selectedRoom Currently selected preset [Room], if any.
 * @param label Label string displayed on the outline field.
 * @param placeholder Placeholder text when the field is empty.
 * @param isError Indicates if an error outline should be displayed.
 * @param errorMessage Optional error helper text.
 * @param enabled Whether interaction with the component is enabled.
 * @param availableRooms List of room presets to display as options (defaults to [Room.presets]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomSelector(
    roomText: String,
    onRoomTextChanged: (String) -> Unit,
    onRoomSelected: (Room) -> Unit,
    modifier: Modifier = Modifier,
    selectedRoom: Room? = null,
    label: String = stringResource(R.string.room_label),
    placeholder: String = stringResource(R.string.room_placeholder),
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    availableRooms: List<Room> = Room.presets
) {
    var expanded by remember { mutableStateOf(false) }

    val filteredRooms = remember(roomText, availableRooms) {
        if (roomText.isBlank()) {
            availableRooms
        } else {
            availableRooms.filter { room ->
                room.name.contains(roomText, ignoreCase = true) ||
                (room.number != null && room.number.contains(roomText, ignoreCase = true)) ||
                room.displayTitle.contains(roomText, ignoreCase = true)
            }
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded && enabled && filteredRooms.isNotEmpty(),
        onExpandedChange = { if (enabled) expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = roomText,
            onValueChange = { newText ->
                onRoomTextChanged(newText)
                if (enabled) expanded = true
            },
            readOnly = false,
            enabled = enabled,
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            isError = isError,
            supportingText = if (errorMessage != null) {
                { Text(errorMessage) }
            } else null,
            shape = RoundedCornerShape(Radius.Small),
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryEditable, enabled)
        )

        if (filteredRooms.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded && enabled,
                onDismissRequest = { expanded = false }
            ) {
                filteredRooms.forEach { room ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = room.displayTitle,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onRoomSelected(room)
                            onRoomTextChanged(room.displayTitle)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
