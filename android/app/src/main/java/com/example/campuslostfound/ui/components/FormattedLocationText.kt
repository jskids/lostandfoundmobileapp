package com.example.campuslostfound.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.campuslostfound.R
import com.example.campuslostfound.domain.model.Building
import com.example.campuslostfound.domain.model.Floor
import com.example.campuslostfound.domain.model.LocationFormatter
import com.example.campuslostfound.domain.model.Room

/**
 * Reusable Jetpack Compose component that renders a formatted campus location string
 * alongside a Lucide location pin icon.
 *
 * @param locationText Pre-formatted string describing the location.
 * @param modifier Custom layout modifier.
 * @param showIcon Whether to display the Lucide location pin icon before the text.
 * @param iconSize Sizing dimension for the location icon.
 * @param iconTint Color tint applied to the location icon.
 * @param textStyle Compose [TextStyle] applied to the formatted text.
 * @param textColor Color applied to the formatted text.
 * @param maxLines Maximum line count allowed before truncating.
 */
@Composable
fun FormattedLocationText(
    locationText: String,
    modifier: Modifier = Modifier,
    showIcon: Boolean = true,
    iconSize: Dp = 16.dp,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = LocalContentColor.current,
    maxLines: Int = 1
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showIcon) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_pin),
                contentDescription = stringResource(id = R.string.location_label),
                tint = iconTint,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        Text(
            text = locationText,
            style = textStyle,
            color = textColor,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Overload accepting typed domain entities ([Building], [Floor], [Room]).
 */
@Composable
fun FormattedLocationText(
    building: Building?,
    floor: Floor?,
    room: Room?,
    modifier: Modifier = Modifier,
    showIcon: Boolean = true,
    useShortFormat: Boolean = true,
    iconSize: Dp = 16.dp,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = LocalContentColor.current,
    maxLines: Int = 1
) {
    val locationText = if (useShortFormat) {
        LocationFormatter.formatShort(building, floor, room)
    } else {
        LocationFormatter.formatFull(building, floor, room)
    }

    FormattedLocationText(
        locationText = locationText,
        modifier = modifier,
        showIcon = showIcon,
        iconSize = iconSize,
        iconTint = iconTint,
        textStyle = textStyle,
        textColor = textColor,
        maxLines = maxLines
    )
}

/**
 * Overload resolving persistent IDs directly.
 */
@Composable
fun FormattedLocationText(
    buildingId: String?,
    floorId: String?,
    roomInputOrId: String?,
    modifier: Modifier = Modifier,
    showIcon: Boolean = true,
    useShortFormat: Boolean = true,
    iconSize: Dp = 16.dp,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = LocalContentColor.current,
    maxLines: Int = 1
) {
    val locationText = LocationFormatter.formatFromIds(
        buildingId = buildingId,
        floorId = floorId,
        roomInputOrId = roomInputOrId,
        shortFormat = useShortFormat
    )

    FormattedLocationText(
        locationText = locationText,
        modifier = modifier,
        showIcon = showIcon,
        iconSize = iconSize,
        iconTint = iconTint,
        textStyle = textStyle,
        textColor = textColor,
        maxLines = maxLines
    )
}
