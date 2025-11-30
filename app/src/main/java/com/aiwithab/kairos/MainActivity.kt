package com.aiwithab.kairos

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalUriHandler


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit
import kotlin.collections.emptyList

data class PlanRequest(
    val user_id: String = "123",
    val category: String,
    val job_description: String,
    val timeline: String
)
data class PlanResponse(
    val skills: List<Skill>
)

data class Skill(
    val skill_name: String,
    val total_days: Int,
    val topics: List<Topic>
)

data class Topic(
    val topic_name: String,
    val study_material: String,
    val timeline: String,
    val priority: String,
    val bonus: Boolean
)



interface ApiService {
    @POST("/career-plan")
    suspend fun generatePlan(@Body request: PlanRequest): PlanResponse
}
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .callTimeout(60, TimeUnit.SECONDS)
    .build()

object ApiClient {
    private const val BASE_URL = "http://192.168.1.25:8000" // Emulator localhost

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
class PlanViewModel : ViewModel() {

    var uiState by mutableStateOf<PlanResponse?>(null)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun generatePlan(category: String, jd: String, timeline: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                error = null
                uiState = ApiClient.api.generatePlan(
                    PlanRequest("1", category, jd, timeline)
                )
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIInterviewPrepApp()
        }
    }
}

@Composable
fun AIInterviewPrepApp() {
    val vm = remember { PlanViewModel() }

    if (vm.uiState == null) {
        InputScreen(vm)
    } else {
        OutputScreen(vm.uiState!!)
    }
}

@Composable
fun InputScreen(vm: PlanViewModel) {
    var category by remember { mutableStateOf("") }
    var jd by remember { mutableStateOf("") }
    var timeline by remember { mutableStateOf("") }

    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("AI Interview Prep", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category (Android, Backend, Web3...)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = jd,
            onValueChange = { jd = it },
            label = { Text("Paste Job Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = timeline,
            onValueChange = { timeline = it },
            label = { Text("Timeline (Days)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (category.isNotBlank() && jd.isNotBlank() && timeline.isNotBlank()) {
                    vm.generatePlan(category, jd, timeline)
                }
            }
        ) {
            Text("Generate Plan")
        }

        if (vm.isLoading) {
            Spacer(Modifier.height(20.dp))
            CircularProgressIndicator()
        }

        vm.error?.let {
            Spacer(Modifier.height(20.dp))
            Text("Error: $it", color = MaterialTheme.colorScheme.error)
        }
    }
}
@Composable
fun OutputScreen(plan: PlanResponse) {

    val topics: MutableList<Topic> = mutableListOf()

    for (skill in plan.skills) {
        topics += skill.topics
    }
    // State to track which items are checked
    var checkedStates by remember {
        mutableStateOf(List(topics.size) { false })
    }

    // Calculate progress
    val checkedCount = checkedStates.count { it }
    val progress = checkedCount / topics.size.toFloat()

    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // Title
        Text(
            text = "Generated Interview Prep Plan",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(20.dp))

        //  Progress Bar
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Spacer(Modifier.height(8.dp))
        Text("$checkedCount / ${topics.size} topics completed")

        Spacer(Modifier.height(24.dp))

        Text(" Topics:", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        //  Topic Cards with Checkboxes
        topics.forEachIndexed { index, topic ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = checkedStates[index],
                        onCheckedChange = { isChecked ->
                            checkedStates = checkedStates.toMutableList().also {
                                it[index] = isChecked
                            }
                        }
                    )

                    Spacer(Modifier.width(12.dp))

                    Column(Modifier.weight(1f)) {
                        Text("${topic.topic_name} (${topic.priority})", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text(topic.timeline)
                    }
                }
            }
        }

        Spacer(Modifier.height(30.dp))

    }
}

