package com.arjya.smartcampus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjya.smartcampus.data.SampleData
import com.arjya.smartcampus.ui.components.StatusBadge
import com.arjya.smartcampus.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(onBack: () -> Unit) {
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All") + SampleData.campusEvents.map { it.category }.distinct()
    val filteredEvents = if (selectedCategory == "All") SampleData.campusEvents
    else SampleData.campusEvents.filter { it.category == selectedCategory }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Events & Activities", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CardEvents,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Category Filter Chips
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CardEvents,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Upcoming Events (${filteredEvents.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Event Cards
            items(filteredEvents) { event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = event.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            StatusBadge(
                                text = event.category,
                                color = getCategoryColor(event.category)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = event.description,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Event details row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            EventDetail(icon = Icons.Default.CalendarToday, text = event.date)
                            EventDetail(icon = Icons.Default.AccessTime, text = event.time)
                            EventDetail(icon = Icons.Default.LocationOn, text = event.venue)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EventDetail(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun getCategoryColor(category: String): Color {
    return when (category) {
        "Technical" -> CardAttendance
        "Cultural" -> CardNotices
        "Workshop" -> CardLibrary
        "Sports" -> CardCanteen
        "Seminar" -> CardTransport
        "Placement" -> CardComplaint
        else -> CardAbout
    }
}
