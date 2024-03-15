package com.example.p6_fragment_registration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p6_fragment_registration.ui.theme.P6_Fragment_RegistrationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF222831)
            ){
            MyApp()
            }
        }
    }
}
enum class Category {
    TECHNICAL_WORKSHOPS,
    INDUSTRY_TRENDS,
    LEADERSHIP_AND_MANAGEMENT,
    ENTREPRENEURSHIP_AND_STARTUPS
}

@Composable
fun CategoryButtons(onCategoryClicked: (Category) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Category.values().forEach { category ->
            Button(
                onClick = { onCategoryClicked(category) },
                colors = ButtonDefaults.buttonColors(Color(0xFF76ABAE)),
                modifier = Modifier.fillMaxWidth(),
                content = { Text(category.name.replace("_", " "), style = MaterialTheme.typography.bodyMedium) }
            )
        }
    }
}

@Composable
fun CategoryEvents(category: Category) {
    // Dummy data for demonstration
    val events = remember(category) {
        when (category) {
            Category.TECHNICAL_WORKSHOPS -> listOf("Workshop 1", "Workshop 2", "Workshop 3")
            Category.INDUSTRY_TRENDS -> listOf("Trend 1", "Trend 2", "Trend 3")
            Category.LEADERSHIP_AND_MANAGEMENT -> listOf("Leadership 1", "Leadership 2", "Leadership 3")
            Category.ENTREPRENEURSHIP_AND_STARTUPS -> listOf("Startup 1", "Startup 2", "Startup 3")
        }
    }

    Box(modifier =Modifier.padding(20.dp).clip(shape = RoundedCornerShape(20.dp)).background(Color(0xFF76ABAE)).fillMaxWidth() ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Events for ${category.name.replace("_", " ")}", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            events.forEach { event ->
                Text(event, style = MaterialTheme.typography.bodyLarge,color = Color.White)
            }
        }
    }

}

@Composable
fun MyApp() {
    var selectedCategory by remember { mutableStateOf(Category.TECHNICAL_WORKSHOPS) }

    Column {
        CategoryButtons { category ->
            selectedCategory = category
        }
        CategoryEvents(selectedCategory)
    }
}

@Preview(showBackground = true)
@Composable
fun EventProfileFormP() {
    P6_Fragment_RegistrationTheme {
        MyApp()
    }
}