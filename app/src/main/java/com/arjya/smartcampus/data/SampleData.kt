package com.arjya.smartcampus.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.arjya.smartcampus.ui.theme.*

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val route: String
)

data class AttendanceRecord(
    val subject: String,
    val totalClasses: Int,
    val attended: Int,
    val professorName: String
) {
    val percentage: Float get() = if (totalClasses > 0) (attended.toFloat() / totalClasses) * 100f else 0f
}

data class LibraryBook(
    val title: String,
    val author: String,
    val isbn: String,
    val available: Boolean,
    val category: String
)

data class CampusEvent(
    val title: String,
    val date: String,
    val time: String,
    val venue: String,
    val description: String,
    val category: String
)

data class NoticeItem(
    val title: String,
    val content: String,
    val date: String,
    val priority: String
)

object SampleData {

    val features = listOf(
        FeatureItem("Attendance", "Track your class attendance", Icons.Default.CheckCircle, CardAttendance, "attendance"),
        FeatureItem("Library", "Browse & reserve books", Icons.Default.MenuBook, CardLibrary, "library"),
        FeatureItem("Events", "Campus events & activities", Icons.Default.Event, CardEvents, "events"),
        FeatureItem("Notices", "Important announcements", Icons.Default.Notifications, CardNotices, "notices"),
        FeatureItem("Canteen", "Today's menu & orders", Icons.Default.Restaurant, CardCanteen, "canteen"),
        FeatureItem("Transport", "Bus routes & schedules", Icons.Default.DirectionsBus, CardTransport, "transport"),
        FeatureItem("Complaints", "Raise & track issues", Icons.Default.Report, CardComplaint, "complaints"),
        FeatureItem("About", "Developer information", Icons.Default.Info, CardAbout, "about")
    )

    val attendanceRecords = listOf(
        AttendanceRecord("Data Structures & Algorithms", 42, 38, "Dr. S. Kumar"),
        AttendanceRecord("Operating Systems", 40, 35, "Prof. R. Sharma"),
        AttendanceRecord("Database Management", 38, 36, "Dr. A. Patel"),
        AttendanceRecord("Computer Networks", 44, 40, "Prof. M. Singh"),
        AttendanceRecord("Software Engineering", 36, 30, "Dr. P. Gupta"),
        AttendanceRecord("Discrete Mathematics", 40, 37, "Prof. N. Das")
    )

    val libraryBooks = listOf(
        LibraryBook("Introduction to Algorithms", "Cormen, Leiserson, Rivest, Stein", "978-0262033848", true, "Computer Science"),
        LibraryBook("Clean Code", "Robert C. Martin", "978-0132350884", true, "Software Engineering"),
        LibraryBook("Design Patterns", "Gang of Four", "978-0201633610", false, "Software Engineering"),
        LibraryBook("Operating System Concepts", "Silberschatz, Galvin, Gagne", "978-1119800361", true, "Computer Science"),
        LibraryBook("Computer Networking", "Kurose, Ross", "978-0133594140", false, "Networking"),
        LibraryBook("Artificial Intelligence: A Modern Approach", "Stuart Russell, Peter Norvig", "978-0134610993", true, "AI/ML"),
        LibraryBook("Database System Concepts", "Silberschatz, Korth, Sudarshan", "978-0078022159", true, "Database"),
        LibraryBook("The Pragmatic Programmer", "Hunt, Thomas", "978-0135957059", false, "Software Engineering")
    )

    val campusEvents = listOf(
        CampusEvent("TechFest 2026", "15 Feb 2026", "10:00 AM", "Main Auditorium", "Annual technical festival with coding competitions, robotics, and hackathons.", "Technical"),
        CampusEvent("Cultural Night", "20 Feb 2026", "6:00 PM", "Open Air Theatre", "An evening of music, dance, and drama performances by students.", "Cultural"),
        CampusEvent("AI/ML Workshop", "22 Feb 2026", "2:00 PM", "CS Lab 301", "Hands-on workshop on machine learning with Python and TensorFlow.", "Workshop"),
        CampusEvent("Sports Day", "25 Feb 2026", "8:00 AM", "Sports Ground", "Annual inter-department sports competition.", "Sports"),
        CampusEvent("Guest Lecture: Cybersecurity", "28 Feb 2026", "11:00 AM", "Seminar Hall", "Industry expert talk on latest trends in cybersecurity.", "Seminar"),
        CampusEvent("Placement Drive", "5 Mar 2026", "9:00 AM", "Placement Cell", "Campus recruitment drive by top tech companies.", "Placement")
    )

    val notices = listOf(
        NoticeItem("Mid-Semester Examination Schedule", "Mid-semester examinations will commence from March 10, 2026. Detailed schedule has been uploaded to the portal.", "05 Feb 2026", "High"),
        NoticeItem("Library Timing Change", "Library will remain open till 10 PM during examination period starting from March 1, 2026.", "04 Feb 2026", "Medium"),
        NoticeItem("Hostel Fee Payment Deadline", "Last date for hostel fee payment for Spring semester is February 28, 2026. Late fee will be applicable after the deadline.", "03 Feb 2026", "High"),
        NoticeItem("Workshop Registration Open", "Registrations are open for the AI/ML Workshop on Feb 22. Limited seats available. Register on the portal.", "02 Feb 2026", "Low"),
        NoticeItem("Campus Wi-Fi Maintenance", "Campus Wi-Fi will undergo scheduled maintenance on Feb 12 from 2 AM to 6 AM.", "01 Feb 2026", "Medium")
    )

    val canteenMenu = mapOf(
        "Breakfast" to listOf("Idli Sambar - Rs.30", "Poha - Rs.20", "Bread Omelette - Rs.35", "Tea/Coffee - Rs.10"),
        "Lunch" to listOf("Veg Thali - Rs.60", "Non-Veg Thali - Rs.80", "Fried Rice - Rs.50", "Biryani - Rs.70"),
        "Snacks" to listOf("Samosa - Rs.15", "Sandwich - Rs.30", "Maggi - Rs.25", "Cold Drink - Rs.20"),
        "Dinner" to listOf("Roti + Sabzi - Rs.40", "Dal Rice - Rs.35", "Paratha - Rs.30", "Noodles - Rs.45")
    )

    val busRoutes = listOf(
        Triple("Route 1", "Campus - Railway Station", "7:00 AM, 12:00 PM, 5:00 PM, 8:00 PM"),
        Triple("Route 2", "Campus - City Center", "7:30 AM, 1:00 PM, 5:30 PM"),
        Triple("Route 3", "Campus - Airport", "6:00 AM, 2:00 PM, 9:00 PM"),
        Triple("Route 4", "Campus - Shopping Mall", "10:00 AM, 3:00 PM, 7:00 PM")
    )
}
