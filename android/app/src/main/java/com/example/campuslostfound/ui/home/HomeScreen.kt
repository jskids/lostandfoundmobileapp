package com.example.campuslostfound.ui.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.campuslostfound.common.state.UiState
import com.example.campuslostfound.ui.components.ErrorScreen
import com.example.campuslostfound.ui.components.FormattedLocationText
import com.example.campuslostfound.ui.components.LoadingScreen
import com.example.campuslostfound.ui.theme.*

/**
 * HomeScreen represents the dashboard of the Campus Lost & Found application.
 *
 * ARCHITECTURAL/UI DECISIONS:
 * 1. High-Fidelity UI Design: Implements exact sections including Top Header, Search, Category Filters,
 *    Location Filters, Status Filters, Content Header, FoundItemCards and Bottom Navigation Bar.
 * 2. Reuse of Design Tokens: Layout relies entirely on predefined tokens in Theme (spacing, Radius, Dimensions)
 *    to preserve visual consistency.
 * 3. Bottom Navigation Integration: Interactive tabs linked to Screen routes with callbacks.
 * 4. Dialog Choice for Report: Choosing the report tab triggers a choice Dialog for Lost vs Found, preserving clean navigation callbacks.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onReportLost: () -> Unit,
    onReportFound: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToDetails: (String) -> Unit = {},
    viewModel: HomeViewModel? = null
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val uiState = if (isPreview || viewModel == null) {
        UiState.Success(mockFoundItems)
    } else {
        viewModel.uiState.collectAsState().value
    }

    // Search and Filters States
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedBuilding by remember { mutableStateOf("All Buildings") }
    var selectedFloor by remember { mutableStateOf("All Floors") }
    var roomQuery by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("All Status") }

    // Dialog state
    var showReportDialog by remember { mutableStateOf(false) }

    // Bottom Navigation State
    var selectedTab by remember { mutableStateOf("Browse") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // App Logo Icon inside primary container circle
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(MaterialTheme.colorScheme.primary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Logo icon",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "Campus Lost & Found",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "BROWSE",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.5.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                        }
                    }
                },
                actions = {
                    // Notification Bell with indicator dot
                    IconButton(onClick = {
                        Toast.makeText(context, "Notifications Clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Box(modifier = Modifier.padding(4.dp)) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(Dimensions.IconMedium)
                            )
                            // Small red notification dot on top-right
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(Color.Red, CircleShape)
                                    .align(Alignment.TopEnd)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Circular profile/account avatar with initials
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFFCD34D), Color(0xFFF59E0B))
                                ),
                                shape = CircleShape
                            )
                            .clickable { onNavigateToProfile() }
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AM",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                // Browse
                NavigationBarItem(
                    selected = selectedTab == "Browse",
                    onClick = {
                        selectedTab = "Browse"
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Browse")
                    },
                    label = { Text("Browse") }
                )

                // Report
                NavigationBarItem(
                    selected = selectedTab == "Report",
                    onClick = {
                        showReportDialog = true
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Report")
                    },
                    label = { Text("Report") }
                )

                // Details
                NavigationBarItem(
                    selected = selectedTab == "Details",
                    onClick = {
                        selectedTab = "Details"
                        onNavigateToDetails("1")
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Details")
                    },
                    label = { Text("Details") }
                )

                // Activity
                NavigationBarItem(
                    selected = selectedTab == "Activity",
                    onClick = {
                        Toast.makeText(context, "Activity History Coming Soon!", Toast.LENGTH_SHORT).show()
                    },
                    icon = {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Activity")
                    },
                    label = { Text("Activity") }
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        when (val state = uiState) {
            is UiState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.padding(innerPadding),
                    message = "Loading campus items..."
                )
            }
            is UiState.Error -> {
                ErrorScreen(
                    message = state.message,
                    modifier = Modifier.padding(innerPadding),
                    onRetry = { viewModel?.loadItems() }
                )
            }
            is UiState.Success -> {
                // LazyColumn handles both filtering controls and the results cards efficiently without nested scrolling issues
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding),
                    contentPadding = PaddingValues(MaterialTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    // 2. Search Section
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                modifier = Modifier.weight(1f),
                                placeholder = {
                                    Text(
                                        text = "Search items, locations, IDs...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(Radius.Medium),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f),
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                    focusedBorderColor = MaterialTheme.colorScheme.primary
                                )
                            )

                            // Filter Sliders button in a blue rounded box on the right (reusing Settings icon as a reliable core icon)
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(Radius.Medium))
                                    .clickable {
                                        Toast.makeText(context, "Filters Settings Clicked", Toast.LENGTH_SHORT).show()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Filters",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    // 3. Category Filters (Horizontally Scrollable)
                    item {
                        val categories = listOf("All", "Shoes", "Blazer", "Phone", "Wallet", "School ID", "Jacket", "Uniform", "Tumbler", "Mini Fan", "Umbrella", "Others")
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(categories) { category ->
                                val isSelected = category == selectedCategory
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                                            shape = RoundedCornerShape(Radius.Large)
                                        )
                                        .clickable { selectedCategory = category }
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = category,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
                                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium)
                                    )
                                }
                            }
                        }
                    }

                    // 4. Location Filters
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Building Column
                            Column(modifier = Modifier.weight(1.3f)) {
                                Text(
                                    text = "Building",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                                var buildingExpanded by remember { mutableStateOf(false) }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(44.dp)
                                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f), RoundedCornerShape(Radius.Small))
                                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(Radius.Small))
                                        .clickable { buildingExpanded = true }
                                        .padding(horizontal = 8.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = selectedBuilding,
                                            style = MaterialTheme.typography.bodyMedium,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Dropdown",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = buildingExpanded,
                                        onDismissRequest = { buildingExpanded = false }
                                    ) {
                                        listOf("All Buildings", "Main Academic Bldg", "Science Complex", "Central Library").forEach { b ->
                                            DropdownMenuItem(
                                                text = { Text(b) },
                                                onClick = {
                                                    selectedBuilding = b
                                                    buildingExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            // Floor Column
                            Column(modifier = Modifier.weight(1.1f)) {
                                Text(
                                    text = "Floor",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                                var floorExpanded by remember { mutableStateOf(false) }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(44.dp)
                                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f), RoundedCornerShape(Radius.Small))
                                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(Radius.Small))
                                        .clickable { floorExpanded = true }
                                        .padding(horizontal = 8.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = selectedFloor,
                                            style = MaterialTheme.typography.bodyMedium,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Dropdown",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = floorExpanded,
                                        onDismissRequest = { floorExpanded = false }
                                    ) {
                                        listOf("All Floors", "Floor 1", "Floor 2", "Floor 3", "Floor 4").forEach { f ->
                                            DropdownMenuItem(
                                                text = { Text(f) },
                                                onClick = {
                                                    selectedFloor = f
                                                    floorExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            // Room Column
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Room",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                                OutlinedTextField(
                                    value = roomQuery,
                                    onValueChange = { roomQuery = it },
                                    placeholder = { Text("e.g. 304", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(44.dp),
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyMedium,
                                    shape = RoundedCornerShape(Radius.Small),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                                        focusedBorderColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }
                        }
                    }

                    // 5. Status Filters
                    item {
                        val statuses = listOf("All Status", "Unsettled", "Pending", "Claimed")
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(statuses) { status ->
                                val isSelected = status == selectedStatus
                                val dotColor = when (status) {
                                    "Unsettled" -> Color(0xFFEF4444)
                                    "Pending" -> Color(0xFFF59E0B)
                                    "Claimed" -> Color(0xFF3B82F6)
                                    else -> null
                                }
                                val bgSelectedColor = if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    when (status) {
                                        "Unsettled" -> Color(0xFFFEE2E2)
                                        "Pending" -> Color(0xFFFEF3C7)
                                        "Claimed" -> Color(0xFFDBEAFE)
                                        else -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                                    }
                                }
                                val textSelectedColor = if (isSelected) {
                                    Color.White
                                } else {
                                    when (status) {
                                        "Unsettled" -> Color(0xFFB91C1C)
                                        "Pending" -> Color(0xFFB45309)
                                        "Claimed" -> Color(0xFF1D4ED8)
                                        else -> MaterialTheme.colorScheme.onPrimaryContainer
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .background(color = bgSelectedColor, shape = RoundedCornerShape(Radius.Large))
                                        .clickable { selectedStatus = status }
                                        .padding(horizontal = 12.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    if (dotColor != null) {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .background(dotColor, CircleShape)
                                        )
                                    }
                                    Text(
                                        text = status,
                                        color = textSelectedColor,
                                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium)
                                    )
                                }
                            }
                        }
                    }

                    // 6. Content Header
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Found On Campus",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            // Items Count Badge
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(Radius.Small))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "4 items",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            // DO VERIFIED CUSTODY Badge with CheckCircle shield icon (core icon replacement)
                            Row(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(Radius.Small))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Verified Icon",
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "DO VERIFIED CUSTODY",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                        }
                    }

                    // 7. Found Item Cards
                    // Filter list in UI mock state
                    val filteredItems = state.data.filter { item ->
                        // Category Filter
                        val matchesCategory = selectedCategory == "All" || item.category == selectedCategory
                        // Status Filter
                        val matchesStatus = selectedStatus == "All Status" || item.status.name.equals(selectedStatus, ignoreCase = true)
                        // Search Query
                        val matchesSearch = searchQuery.isEmpty() ||
                                item.title.contains(searchQuery, ignoreCase = true) ||
                                item.location.contains(searchQuery, ignoreCase = true)

                        matchesCategory && matchesStatus && matchesSearch
                    }

                    if (filteredItems.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No items match your filters.",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        items(filteredItems) { item ->
                            FoundItemCard(
                                item = item,
                                onClaimClick = { itemId ->
                                    Toast.makeText(context, "Claiming Item $itemId (UI Only)", Toast.LENGTH_SHORT).show()
                                },
                                onDetailsClick = { itemId ->
                                    onNavigateToDetails(itemId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Modern Choice Dialog when Clicking bottom 'Report' tab
    if (showReportDialog) {
        AlertDialog(
            onDismissRequest = {
                showReportDialog = false
                selectedTab = "Browse" // Reset tab back to active Browse
            },
            title = {
                Text(
                    text = "Report Campus Item",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Text(
                    text = "Would you like to report something you lost (Lost Item) or report something you found on campus (Found Item)?",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            showReportDialog = false
                            selectedTab = "Browse"
                            onReportLost()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Radius.Small)
                    ) {
                        Text("Report Lost Item")
                    }
                    Button(
                        onClick = {
                            showReportDialog = false
                            selectedTab = "Browse"
                            onReportFound()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(Radius.Small)
                    ) {
                        Text("Report Found Item")
                    }
                    TextButton(
                        onClick = {
                            showReportDialog = false
                            selectedTab = "Browse"
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        )
    }
}

/**
 * FoundItemCard is a reusable component representing a single found item card on Campus.
 */
@Composable
fun FoundItemCard(
    item: MockFoundItem,
    onClaimClick: (String) -> Unit,
    onDetailsClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Generate beautiful custom category colors & safe material icons since no local assets are configured
    val (gradientColors, itemIcon) = when (item.category) {
        "Shoes" -> Pair(listOf(Color(0xFF84CC16), Color(0xFF3F6212)), Icons.Default.Info)
        "Blazer" -> Pair(listOf(Color(0xFF1E40AF), Color(0xFF1E3A8A)), Icons.Default.Home)
        "Phone" -> Pair(listOf(Color(0xFF06B6D4), Color(0xFF155E75)), Icons.Default.Phone)
        "Wallet" -> Pair(listOf(Color(0xFF8B5CF6), Color(0xFF5B21B6)), Icons.Default.Lock)
        "Tumbler" -> Pair(listOf(Color(0xFF059669), Color(0xFF065F46)), Icons.Default.Favorite)
        "School ID" -> Pair(listOf(Color(0xFFD97706), Color(0xFF78350F)), Icons.Default.AccountBox)
        "Mini Fan" -> Pair(listOf(Color(0xFFEA580C), Color(0xFF7C2D12)), Icons.Default.Refresh)
        else -> Pair(listOf(Color(0xFF64748B), Color(0xFF334155)), Icons.Default.Info)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                RoundedCornerShape(Radius.Large)
            ),
        shape = RoundedCornerShape(Radius.Large),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.CardElevation)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Image Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Brush.linearGradient(colors = gradientColors)),
                contentAlignment = Alignment.Center
            ) {
                // Category Icon with label in Center
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = itemIcon,
                        contentDescription = item.category,
                        tint = Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        ),
                        color = Color.White.copy(alpha = 0.75f)
                    )
                }

                // Status Badge in Top Right
                val (statusBg, statusText, statusDotColor) = when (item.status) {
                    FoundItemStatus.UNSETTLED -> Triple(Color(0xFFFEE2E2), "Unsettled", Color(0xFFEF4444))
                    FoundItemStatus.PENDING -> Triple(Color(0xFFFEF3C7), "Pending", Color(0xFFF59E0B))
                    FoundItemStatus.CLAIMED -> Triple(Color(0xFFDBEAFE), "Claimed", Color(0xFF3B82F6))
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .background(statusBg, RoundedCornerShape(Radius.Medium))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(statusDotColor, CircleShape)
                    )
                    Text(
                        text = statusText,
                        color = statusDotColor,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }

                // Custody/Location Badge at Bottom Left (reusing CheckCircle as a shield icon)
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                        .background(Color.White.copy(alpha = 0.92f), RoundedCornerShape(Radius.Medium))
                        .border(0.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(Radius.Medium))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Verified Custody",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = item.custodyLabel,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            // Information Body
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                // Item Title & Category Label on right
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f), RoundedCornerShape(Radius.Small))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = item.category,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                // Location Metadata
                FormattedLocationText(
                    locationText = item.location,
                    iconTint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    textStyle = MaterialTheme.typography.bodySmall,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Time Metadata (using Info icon as standard core replacement)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Clock Info",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = item.timePosted,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(4.dp))

                // Footer Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left: Poster Information (reusing Info icon for anonymous poster)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (item.isAnonymous) Icons.Default.Info else Icons.Default.AccountCircle,
                            contentDescription = "Poster Avatar",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = item.posterInfo,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Right: Action buttons
                    when (item.actionType) {
                        ActionButtonType.CLAIM -> {
                            Button(
                                onClick = { onClaimClick(item.id) },
                                shape = RoundedCornerShape(Radius.Small),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text("Claim", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Arrow",
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                        ActionButtonType.DETAILS -> {
                            Button(
                                onClick = { onDetailsClick(item.id) },
                                shape = RoundedCornerShape(Radius.Small),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text("Details", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Arrow",
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                        ActionButtonType.RESOLVED -> {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(Radius.Small))
                                    .padding(horizontal = 14.dp, vertical = 6.dp)
                                    .height(18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Resolved",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Model Enums & Data Class for mock-ups representation
enum class FoundItemStatus {
    UNSETTLED, PENDING, CLAIMED
}

enum class ActionButtonType {
    CLAIM, DETAILS, RESOLVED
}

data class MockFoundItem(
    val id: String,
    val title: String,
    val category: String,
    val status: FoundItemStatus,
    val custodyLabel: String,
    val location: String,
    val timePosted: String,
    val posterInfo: String,
    val isAnonymous: Boolean,
    val actionType: ActionButtonType
)

// Static high-fidelity mock list
val mockFoundItems = listOf(
    MockFoundItem(
        id = "1",
        title = "Navy Blue College Blazer",
        category = "Blazer",
        status = FoundItemStatus.UNSETTLED,
        custodyLabel = "Turned in to Discipline Office",
        location = "Floor 3 • Main Academic Bldg • RM 304",
        timePosted = "Today, 10:45 AM (Oct 24, 2024)",
        posterInfo = "alex.m@univ.edu",
        isAnonymous = false,
        actionType = ActionButtonType.CLAIM
    ),
    MockFoundItem(
        id = "2",
        title = "Stainless Steel Hydro Tumbler",
        category = "Tumbler",
        status = FoundItemStatus.PENDING,
        custodyLabel = "With Finder: Pending Handover",
        location = "Floor 1 • University Cafeteria • Near Booth 7",
        timePosted = "Yesterday, 3:20 PM",
        posterInfo = "Posted Anonymously",
        isAnonymous = true,
        actionType = ActionButtonType.DETAILS
    ),
    MockFoundItem(
        id = "3",
        title = "Student RFID ID Card (BS CS)",
        category = "School ID",
        status = FoundItemStatus.CLAIMED,
        custodyLabel = "Secured at DO - Window 2",
        location = "Floor 2 • Science Complex • Lab 202",
        timePosted = "Oct 22, 2024, 11:15 AM",
        posterInfo = "prof.reyes@univ.edu",
        isAnonymous = false,
        actionType = ActionButtonType.RESOLVED
    ),
    MockFoundItem(
        id = "4",
        title = "Portable Mini Fan (Pastel Pink)",
        category = "Mini Fan",
        status = FoundItemStatus.UNSETTLED,
        custodyLabel = "In Finder custody",
        location = "Floor 4 • Central Library • Quiet Study Area 4B",
        timePosted = "Oct 23, 2024, 5:10 PM",
        posterInfo = "Posted Anonymously",
        isAnonymous = true,
        actionType = ActionButtonType.CLAIM
    ),
    MockFoundItem(
        id = "5",
        title = "Brown Leather Bi-Fold Wallet",
        category = "Wallet",
        status = FoundItemStatus.UNSETTLED,
        custodyLabel = "Turned in to Student Affairs Office",
        location = "Floor 2 • Administration Bldg • RM 201",
        timePosted = "Just now",
        posterInfo = "john.doe@univ.edu",
        isAnonymous = false,
        actionType = ActionButtonType.CLAIM
    )
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CampusLostFoundTheme {
        HomeScreen(
            onNavigateToProfile = {},
            onReportLost = {},
            onReportFound = {}
        )
    }
}
