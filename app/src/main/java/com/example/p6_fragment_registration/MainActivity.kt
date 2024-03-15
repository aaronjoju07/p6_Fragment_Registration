package com.example.p6_fragment_registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p6_fragment_registration.ui.theme.P6_Fragment_RegistrationTheme
import com.example.p6_fragment_registration.ui.theme.Purple80
import com.example.p6_fragment_registration.ui.theme.PurpleGrey40
import com.example.p6_fragment_registration.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            P6_Fragment_RegistrationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF222831)
                ) {
                    EventRegistration(context)
                }
            }
        }
    }
}
@Composable
fun EventRegistration(context: Context) {
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xFF76ABAE))
        ) {
//            Image(painter = painterResource(id = R.drawable.event), contentDescription ="EventImage" )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "EventProfileForm",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color(0xFFEEEEEE)
                )
                when (currentStep) {
                    0 -> NameStep(name = name, onNameChange = { name = it })
                    1 -> SessionPreferencesStep(
                        sessionPreferences = sessionPreferences,
                        onSessionPreferencesChange = { sessionPreferences = it },
                        name = name
                    )
                    2 -> DietaryRestrictionsStep(
                        dietaryRestrictions = dietaryRestrictions,
                        onDietaryRestrictionsChange = { dietaryRestrictions = it },
                        name = name,
                        sessionPreferences = sessionPreferences
                    )
                    3 -> NetworkingInterestsStep(
                        networkingInterests = networkingInterests,
                        onNetworkingInterestsChange = { networkingInterests = it },
                        name = name,
                        sessionPreferences = sessionPreferences,
                        dietaryRestrictions = dietaryRestrictions
                    )
                }

                // Next button for all steps except the last one
                if (currentStep < steps.size - 1) {
                    Button(
                        onClick = {
                            currentStep++
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFF222831)),
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
                            val intent = Intent(context, SecondActivity::class.java)
                            context.startActivity(intent)
                        }, colors = ButtonDefaults.buttonColors(Color(0xFF222831)),
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
                        },colors = ButtonDefaults.buttonColors(Color(0xFF222831)),
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
    }
}

@Composable
fun SessionPreferencesStep(
    sessionPreferences: String,
    onSessionPreferencesChange: (String) -> Unit,
    name: String
) {
    TextField(
        value = sessionPreferences,
        onValueChange = { onSessionPreferencesChange(it) },
        label = { Text(text = "Session Preferences") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Text(
        text = "Name: $name",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp),color = Color.White
    )
}

@Composable
fun DietaryRestrictionsStep(
    dietaryRestrictions: String,
    onDietaryRestrictionsChange: (String) -> Unit,
    name: String,
    sessionPreferences: String
) {
    TextField(
        value = dietaryRestrictions,
        onValueChange = { onDietaryRestrictionsChange(it) },
        label = { Text(text = "Dietary Restrictions") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Text(
        text = "Name: $name\nSession Preferences: $sessionPreferences",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp),color = Color.White
    )
}

@Composable
fun NetworkingInterestsStep(
    networkingInterests: String,
    onNetworkingInterestsChange: (String) -> Unit,
    name: String,
    sessionPreferences: String,
    dietaryRestrictions: String
) {
    TextField(
        value = networkingInterests,
        onValueChange = { onNetworkingInterestsChange(it) },
        label = { Text(text = "Networking Interests") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Text(
        text = "Name: $name\nSession Preferences: $sessionPreferences\nDietary Restrictions: $dietaryRestrictions",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp),
        color = Color.White
    )
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

//@Preview(showBackground = true)
//@Composable
//fun EventProfileFormPreview() {
//    P6_Fragment_RegistrationTheme {
//        EventRegistration()
//    }
//}