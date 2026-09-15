package com.example.campuslostfound.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.campuslostfound.ui.theme.Dimensions
import com.example.campuslostfound.ui.theme.Radius
import com.example.campuslostfound.ui.theme.StatusClaimed
import com.example.campuslostfound.ui.theme.StatusFound
import com.example.campuslostfound.ui.theme.StatusLost
import com.example.campuslostfound.ui.theme.StatusPending
import com.example.campuslostfound.ui.theme.spacing

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.ButtonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(Radius.Small)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.ButtonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(Radius.Small)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = modifier.fillMaxWidth(),
        singleLine = singleLine,
        shape = RoundedCornerShape(Radius.Small)
    )
}

enum class ItemStatus {
    LOST, FOUND, CLAIMED, PENDING
}

@Composable
fun StatusBadge(
    status: ItemStatus,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor, label) = when (status) {
        ItemStatus.LOST -> Triple(StatusLost.copy(alpha = 0.15f), StatusLost, "Lost")
        ItemStatus.FOUND -> Triple(StatusFound.copy(alpha = 0.15f), StatusFound, "Found")
        ItemStatus.CLAIMED -> Triple(StatusClaimed.copy(alpha = 0.15f), StatusClaimed, "Claimed")
        ItemStatus.PENDING -> Triple(StatusPending.copy(alpha = 0.15f), StatusPending, "Pending")
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(Radius.Full)
            )
            .padding(horizontal = MaterialTheme.spacing.mediumSmall, vertical = MaterialTheme.spacing.extraSmall),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}
