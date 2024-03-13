package com.example.p6_fragment_registration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p6_fragment_registration.ui.theme.P6_Fragment_RegistrationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P6_Fragment_RegistrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EventRegistration()
                }
            }
        }
    }
}

@Composable
fun EventRegistration() {
    var currentStep by remember { mutableStateOf(0) }

    val steps = listOf(
        "Name",
        "Session Preferences",
        "Dietary Restrictions",
        "Networking Interests"
    )

    var name by remember { mutableStateOf("") }
    var sessionPreferences by remember { mutableStateOf("") }
    var dietaryRestrictions by remember { mutableStateOf("") }
    var networkingInterests by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "EventProfileForm",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (currentStep) {
            0 -> NameStep(name = name, onNameChange = { name = it })
            1 -> SessionPreferencesStep(sessionPreferences = sessionPreferences, onSessionPreferencesChange = { sessionPreferences = it })
            2 -> DietaryRestrictionsStep(dietaryRestrictions = dietaryRestrictions, onDietaryRestrictionsChange = { dietaryRestrictions = it })
            3 -> NetworkingInterestsStep(networkingInterests = networkingInterests, onNetworkingInterestsChange = { networkingInterests = it })
        }



        // Next button for all steps except the last one
        if (currentStep < steps.size - 1) {
            Button(
                onClick = {
                    currentStep++
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(5.dp)
            ) {
                Text(text = "Next: ${steps[currentStep + 1]}")
            }
        } else {
            // Last step - Submit Button
            Button(
                onClick = {
                    // Handle form submission here
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(5.dp)
            ) {
                Text(text = "Submit")
            }
        }
        // Back button for all steps except the first one
        if (currentStep > 0) {
            Button(
                onClick = {
                    currentStep--
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(5.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}


@Composable
fun NameStep(name: String, onNameChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text(text = "Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun SessionPreferencesStep(sessionPreferences: String, onSessionPreferencesChange: (String) -> Unit) {
    TextField(
        value = sessionPreferences,
        onValueChange = { onSessionPreferencesChange(it) },
        label = { Text(text = "Session Preferences") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun DietaryRestrictionsStep(dietaryRestrictions: String, onDietaryRestrictionsChange: (String) -> Unit) {
    TextField(
        value = dietaryRestrictions,
        onValueChange = { onDietaryRestrictionsChange(it) },
        label = { Text(text = "Dietary Restrictions ") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun NetworkingInterestsStep(networkingInterests: String, onNetworkingInterestsChange: (String) -> Unit) {
    TextField(
        value = networkingInterests,
        onValueChange = { onNetworkingInterestsChange(it) },
        label = { Text(text = "Networking Interests") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun EventProfileFormPreview() {
    P6_Fragment_RegistrationTheme {
        EventRegistration()
    }
}