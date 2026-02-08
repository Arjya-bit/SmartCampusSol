package com.arjya.smartcampus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.arjya.smartcampus.ui.theme.*

data class Complaint(
    val id: String,
    val title: String,
    val category: String,
    val status: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintsScreen(onBack: () -> Unit) {
    val sampleComplaints = listOf(
        Complaint("C001", "Wi-Fi not working in Hostel Block B", "IT", "In Progress", "02 Feb 2026"),
        Complaint("C002", "Water cooler broken near Lab 201", "Maintenance", "Resolved", "28 Jan 2026"),
        Complaint("C003", "Streetlight not working near parking", "Electrical", "Pending", "05 Feb 2026")
    )

    var showDialog by remember { mutableStateOf(false) }
    var complaintTitle by remember { mutableStateOf("") }
    var complaintCategory by remember { mutableStateOf("") }
    var complaintDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Complaints & Issues", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CardComplaint,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = CardComplaint
            ) {
                Icon(Icons.Default.Add, contentDescription = "New Complaint", tint = Color.White)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Stats
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF9800).copy(alpha = 0.1f))
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("1", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color(0xFFFF9800))
                            Text("Pending", fontSize = 11.sp, color = Color(0xFFFF9800))
                        }
                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = PrimaryBlue.copy(alpha = 0.1f))
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("1", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = PrimaryBlue)
                            Text("In Progress", fontSize = 11.sp, color = PrimaryBlue)
                        }
                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = AccentGreen.copy(alpha = 0.1f))
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("1", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = AccentGreen)
                            Text("Resolved", fontSize = 11.sp, color = AccentGreen)
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Your Complaints",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            items(sampleComplaints.size) { index ->
                val complaint = sampleComplaints[index]
                val statusColor = when (complaint.status) {
                    "Resolved" -> AccentGreen
                    "In Progress" -> PrimaryBlue
                    else -> Color(0xFFFF9800)
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
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
                                text = complaint.id,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = statusColor.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = complaint.status,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    color = statusColor,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = complaint.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Category, null, Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(Modifier.width(4.dp))
                                Text(complaint.category, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CalendarToday, null, Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(Modifier.width(4.dp))
                                Text(complaint.date, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }

    // New Complaint Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Raise New Complaint", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = complaintTitle,
                        onValueChange = { complaintTitle = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = complaintCategory,
                        onValueChange = { complaintCategory = it },
                        label = { Text("Category (IT / Maintenance / Electrical)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = complaintDescription,
                        onValueChange = { complaintDescription = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        complaintTitle = ""
                        complaintCategory = ""
                        complaintDescription = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CardComplaint)
                ) {
                    Text("Submit")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
