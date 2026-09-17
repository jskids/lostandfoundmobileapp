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
import com.example.campuslostfound.domain.model.Floor
import com.example.campuslostfound.ui.theme.Radius

/**
 * Reusable Material 3 floor selector component allowing users to choose a campus floor level from a dropdown menu.
 *
 * @param selectedFloor Currently selected [Floor], or null if unselected.
 * @param onFloorSelected Callback triggered when a floor level is selected.
 * @param modifier Custom layout modifier.
 * @param label Label string displayed on the outline field.
 * @param placeholder Placeholder text when no floor is selected.
 * @param isError Indicates if an error outline should be displayed.
 * @param errorMessage Optional error helper text.
 * @param enabled Whether the dropdown menu interaction is enabled.
 * @param availableFloors List of floors to display in the dropdown menu options (defaults to [Floor.values]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloorSelector(
    selectedFloor: Floor?,
    onFloorSelected: (Floor) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.floor_label),
    placeholder: String = stringResource(R.string.select_floor),
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    availableFloors: List<Floor> = Floor.values
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && enabled,
        onExpandedChange = { if (enabled) expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedFloor?.let {
                "${stringResource(it.nameResId)} (${it.code})"
            } ?: "",
            onValueChange = {},
            readOnly = true,
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
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled)
        )

        ExposedDropdownMenu(
            expanded = expanded && enabled,
            onDismissRequest = { expanded = false }
        ) {
            availableFloors.forEach { floor ->
                val floorName = stringResource(floor.nameResId)
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "$floorName (${floor.code})",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        onFloorSelected(floor)
                        expanded = false
                    }
                )
            }
        }
    }
}
