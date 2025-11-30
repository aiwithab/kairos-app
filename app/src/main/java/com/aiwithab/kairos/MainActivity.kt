package com.aiwithab.kairos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aiwithab.kairos.presentation.navigation.NavGraph
import com.aiwithab.kairos.ui.theme.KairosTheme

/**
 * Main entry point for the Kairos app
 * Sets up the navigation graph and theme
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KairosTheme {
                NavGraph()
            }
        }
    }
}
