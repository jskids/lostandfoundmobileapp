package com.example.campuslostfound.ui.home

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
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.campuslostfound.navigation.Screen
import com.example.campuslostfound.ui.theme.CampusLostFoundTheme
import com.example.campuslostfound.ui.theme.Dimensions
import com.example.campuslostfound.ui.theme.spacing

/**
 * Representation of a found item on campus.
 */
data class FoundItem(
    val id: String,
    val title: String,
    val category: String,
    val status: ItemStatus,
    val location: String,
    val timePosted: String,
    val custody: String,
    val reporter: String,
    val isAnonymous: Boolean,
    val placeholderGradient: List<Color>
)

/**
 * Custom enum for found item status with custom colors for branding.
 */
enum class ItemStatus(
    val label: String,
    val dotColor: Color,
    val containerColor: Color,
    val contentColor: Color
) {
    UNSETTLED("Unsettled", Color(0xFFEF4444), Color(0xFFFEE2E2), Color(0xFF991B1B)),
    PENDING("Pending", Color(0xFFF59E0B), Color(0xFFFEF3C7), Color(0xFF92400E)),
    CLAIMED("Claimed", Color(0xFF3B82F6), Color(0xFFDBEAFE), Color(0xFF1E3A8A))
}

/**
 * Primary Home Screen containing main browse capabilities.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Local mock data reflecting the items in the prompt instructions
    val mockItems = remember {
        listOf(
            FoundItem(
                id = "1",
                title = "Navy Blue College Blazer",
                category = "Blazer",
                status = ItemStatus.UNSETTLED,
                location = "Floor 3 • Main Academic Bldg • RM 304",
                timePosted = "Today, 10:45 AM (Oct 24, 2024)",
                custody = "Turned in to Discipline Office",
                reporter = "alex.m@univ.edu",
                isAnonymous = false,
                placeholderGradient = listOf(Color(0xFF1E3A8A), Color(0xFF3B82F6))
            ),
            FoundItem(
                id = "2",
                title = "Stainless Steel Hydro Tumbler...",
                category = "Tumbler",
                status = ItemStatus.PENDING,
                location = "Floor 1 • University Cafeteria • Near Booth 7",
                timePosted = "Yesterday, 3:20 PM",
                custody = "With Finder: Pending Handover",
                reporter = "Posted Anonymously",
                isAnonymous = true,
                placeholderGradient = listOf(Color(0xFFB45309), Color(0xFFF59E0B))
            ),
            FoundItem(
                id = "3",
                title = "Student RFID ID Card (BS CS)",
                category = "School ID",
                status = ItemStatus.CLAIMED,
                location = "Floor 2 • Science Complex • Lab 202",
                timePosted = "Oct 22, 2024, 11:15 AM",
                custody = "Secured at DO - Window 2",
                reporter = "prof.reyes@univ.edu",
                isAnonymous = false,
                placeholderGradient = listOf(Color(0xFF047857), Color(0xFF10B981))
            ),
            FoundItem(
                id = "4",
                title = "Portable Mini Fan (Pastel Pink)",
                category = "Mini Fan",
                status = ItemStatus.UNSETTLED,
                location = "Floor 4 • Central Library • Quiet Study Area 4B",
                timePosted = "Oct 23, 2024, 5:10 PM",
                custody = "In Finder custody",
                reporter = "Posted Anonymously",
                isAnonymous = true,
                placeholderGradient = listOf(Color(0xFFEC4899), Color(0xFFF472B6))
            )
        )
    }

    // Interactive states for UI filters
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedStatus by remember { mutableStateOf("All Status") }
    var selectedBuilding by remember { mutableStateOf("All Buildings") }
    var selectedFloor by remember { mutableStateOf("All Floors") }
    var roomQuery by remember { mutableStateOf("") }

    val categories = listOf("All", "Blazer", "Tumbler", "School ID", "Mini Fan")
    val statuses = listOf("All Status", "Unsettled", "Pending", "Claimed")

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            HomeBottomNavigation(
                currentRoute = Screen.Home.route,
                onNavigate = { route ->
                    if (route != Screen.Home.route) {
                        navController.navigate(route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Pin the entire filter panel at the top
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .padding(top = MaterialTheme.spacing.medium, bottom = MaterialTheme.spacing.small)
            ) {
                // 1. Top Header
                HomeTopHeader()

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                // 2. Search Section
                HomeSearchSection(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                // 3. Category Filters
                HomeCategoryRow(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                // 4. Location Filters (Building, Floor, Room)
                HomeLocationFilters(
                    selectedBuilding = selectedBuilding,
                    onBuildingChange = { selectedBuilding = it },
                    selectedFloor = selectedFloor,
                    onFloorChange = { selectedFloor = it },
                    roomQuery = roomQuery,
                    onRoomChange = { roomQuery = it }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                // 5. Status Filters
                HomeStatusRow(
                    statuses = statuses,
                    selectedStatus = selectedStatus,
                    onStatusSelected = { selectedStatus = it }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }

            // Divider between top filters and feed
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)

            // Feed Contents (Content Header & Found Item Cards)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                // 6. Content Header
                item {
                    HomeContentHeader(itemCount = mockItems.size)
                }

                // 7. Found Item Cards
                items(mockItems, key = { it.id }) { item ->
                    FoundItemCard(
                        item = item,
                        onClaimClick = { /* UI Only */ },
                        onDetailsClick = {
                            navController.navigate(Screen.ItemDetails.createRoute(item.id))
                        }
                    )
                }
            }
        }
    }
}

/**
 * Custom Home Screen Header
 */
@Composable
fun HomeTopHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            // Stylized App Logo
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ManageSearch,
                    contentDescription = "App Logo",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column {
                Text(
                    text = "Campus Lost & Found",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "BROWSE",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            // Notification icon with badge
            Box(contentAlignment = Alignment.TopEnd) {
                IconButton(onClick = { /* UI Only */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                // Small red notification indicator dot
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )
            }

            // Circular Profile/Account Icon (Initials styled as image avatar)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AM",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

/**
 * Search section input bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchSection(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
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
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        // Filter / Tune Config Icon
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable { /* UI Only */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Filter Slider",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Horizontally scrollable Category row
 */
@Composable
fun HomeCategoryRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.extraSmall)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            val containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(containerColor)
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = MaterialTheme.spacing.medium, vertical = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = contentColor
                )
            }
        }
    }
}

/**
 * Filter controls for Location: Building, Floor, Room
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLocationFilters(
    selectedBuilding: String,
    onBuildingChange: (String) -> Unit,
    selectedFloor: String,
    onFloorChange: (String) -> Unit,
    roomQuery: String,
    onRoomChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        // Building Spinner Placeholder
        var buildingExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = buildingExpanded,
            onExpandedChange = { buildingExpanded = !buildingExpanded },
            modifier = Modifier.weight(1.2f)
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedBuilding,
                onValueChange = {},
                label = { Text("Building", style = MaterialTheme.typography.labelSmall) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = buildingExpanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline
                ),
                textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                modifier = Modifier
                    .menuAnchor()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp)
            )
            ExposedDropdownMenu(
                expanded = buildingExpanded,
                onDismissRequest = { buildingExpanded = false }
            ) {
                listOf("All Buildings", "Main Academic Bldg", "Science Complex", "Central Library").forEach { selection ->
                    DropdownMenuItem(
                        text = { Text(selection, style = MaterialTheme.typography.bodyMedium) },
                        onClick = {
                            onBuildingChange(selection)
                            buildingExpanded = false
                        }
                    )
                }
            }
        }

        // Floor Spinner Placeholder
        var floorExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = floorExpanded,
            onExpandedChange = { floorExpanded = !floorExpanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedFloor,
                onValueChange = {},
                label = { Text("Floor", style = MaterialTheme.typography.labelSmall) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = floorExpanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline
                ),
                textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                modifier = Modifier
                    .menuAnchor()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp)
            )
            ExposedDropdownMenu(
                expanded = floorExpanded,
                onDismissRequest = { floorExpanded = false }
            ) {
                listOf("All Floors", "Floor 1", "Floor 2", "Floor 3", "Floor 4").forEach { selection ->
                    DropdownMenuItem(
                        text = { Text(selection, style = MaterialTheme.typography.bodyMedium) },
                        onClick = {
                            onFloorChange(selection)
                            floorExpanded = false
                        }
                    )
                }
            }
        }

        // Room text input field
        OutlinedTextField(
            value = roomQuery,
            onValueChange = onRoomChange,
            label = { Text("Room", style = MaterialTheme.typography.labelSmall) },
            placeholder = { Text("e.g. 304", style = MaterialTheme.typography.bodySmall) },
            textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.outline
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

/**
 * Status Filters Pill Row
 */
@Composable
fun HomeStatusRow(
    statuses: List<String>,
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        statuses.forEach { status ->
            val isSelected = status == selectedStatus
            val (bgColor, textColor, borderModifier) = if (isSelected) {
                Triple(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.onPrimary,
                    Modifier
                )
            } else {
                Triple(
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    MaterialTheme.colorScheme.onSurfaceVariant,
                    Modifier.border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .then(borderModifier)
                    .background(bgColor)
                    .clickable { onStatusSelected(status) }
                    .padding(horizontal = MaterialTheme.spacing.mediumSmall, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    // Small status dots for indicators
                    if (status != "All Status") {
                        val dotColor = when (status) {
                            "Unsettled" -> Color(0xFFEF4444)
                            "Pending" -> Color(0xFFF59E0B)
                            "Claimed" -> Color(0xFF3B82F6)
                            else -> Color.Gray
                        }
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(dotColor)
                        )
                    }
                    Text(
                        text = status,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                }
            }
        }
    }
}

/**
 * Content Header showing found counts and custody badge
 */
@Composable
fun HomeContentHeader(itemCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Text(
                text = "Found On Campus",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Dynamic badge count
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = MaterialTheme.spacing.small, vertical = 2.dp)
            ) {
                Text(
                    text = "$itemCount items",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        // Custody Verification Badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f))
                .padding(horizontal = MaterialTheme.spacing.small, vertical = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.VerifiedUser,
                contentDescription = "Shield Check",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = "DO VERIFIED CUSTODY",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

/**
 * Found Item card reusable component
 */
@Composable
fun FoundItemCard(
    item: FoundItem,
    onClaimClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetailsClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            // 1. Image Placeholder Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Brush.verticalGradient(item.placeholderGradient)),
                contentAlignment = Alignment.Center
            ) {
                // Large Category Icon Placeholder
                val categoryIcon = when (item.category) {
                    "Blazer" -> Icons.Default.Checkroom
                    "Tumbler" -> Icons.Default.LocalCafe
                    "School ID" -> Icons.Default.Badge
                    "Mini Fan" -> Icons.Default.WindPower
                    else -> Icons.Default.Category
                }

                Icon(
                    imageVector = categoryIcon,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.size(56.dp)
                )

                // Top-Right Status Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(MaterialTheme.spacing.medium)
                        .clip(RoundedCornerShape(12.dp))
                        .background(item.status.containerColor)
                        .padding(horizontal = MaterialTheme.spacing.small, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(item.status.dotColor)
                        )
                        Text(
                            text = item.status.label,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = item.status.contentColor
                        )
                    }
                }

                // Bottom-Left Custody Label
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(MaterialTheme.spacing.medium)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.9f))
                        .padding(horizontal = MaterialTheme.spacing.small, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBalance,
                        contentDescription = "Discipline Office",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = item.custody,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // 2. Info Details Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    // Small Category label
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
                            .padding(horizontal = MaterialTheme.spacing.small, vertical = 2.dp)
                    ) {
                        Text(
                            text = item.category,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                // Metadata Rows
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Pin",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = item.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Clock",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = item.timePosted,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                // Footer section (Claim / Email / Status actions)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                    ) {
                        val reporterIcon = if (item.isAnonymous) Icons.Default.NoAccounts else Icons.Default.AlternateEmail
                        Icon(
                            imageVector = reporterIcon,
                            contentDescription = "Reporter",
                            tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = item.reporter,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Action buttons
                    when (item.status) {
                        ItemStatus.UNSETTLED -> {
                            Button(
                                onClick = onClaimClick,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium, vertical = 0.dp),
                                modifier = Modifier.height(36.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Claim",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Arrow Forward",
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                        ItemStatus.PENDING -> {
                            FilledTonalButton(
                                onClick = onDetailsClick,
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium, vertical = 0.dp),
                                modifier = Modifier.height(36.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Details",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Arrow Forward",
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                        ItemStatus.CLAIMED -> {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .padding(horizontal = MaterialTheme.spacing.medium, vertical = 8.dp)
                            ) {
                                Text(
                                    text = "Resolved",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Reusable Home bottom navigation
 */
@Composable
fun HomeBottomNavigation(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        val navItems = listOf(
            Triple("Browse", Screen.Home.route, Icons.Default.Search),
            Triple("Report", Screen.LostItems.route, Icons.Default.AddCircle),
            // The prompt requests "Details" and "Activity" as bottom nav items
            Triple("Details", Screen.ItemDetails.createRoute("1"), Icons.Default.Description),
            Triple("Activity", Screen.Profile.route, Icons.Default.History)
        )

        navItems.forEach { (label, route, icon) ->
            val isSelected = currentRoute == route
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(route) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CampusLostFoundTheme {
        val mockNavController = rememberNavController()
        HomeScreen(navController = mockNavController)
    }
}
