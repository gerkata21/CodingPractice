package com.example.codingpractice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codingpractice.codingExercise.presentation.ReqresViewModel
import com.example.codingpractice.ui.theme.CodingPracticeTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodingPracticeTheme {
                Scaffold { paddingValues ->
                    Surface(
                        Modifier.fillMaxSize().padding(paddingValues)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp)
                        ) {
                            InitiateView()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun InitiateView(
        reqresViewModel: ReqresViewModel = viewModel()
    ) {
        reqresViewModel.collectSideEffect {
            handleSideEffect(it)
        }


        when (val state = reqresViewModel.collectAsState().value) {
            ReqresViewModel.State.NotVisible -> {
                ShowLoading()
            }
            is ReqresViewModel.State.Visible -> {
                ShowContent(state.text)
            }
        }
    }

    @Composable
    fun ShowContent(message: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $message!",
            modifier = modifier
        )
    }

    @Composable
    fun ShowLoading(modifier: Modifier = Modifier) {
        Text(
            text = "Loading",
            modifier = modifier
        )
    }

    private fun handleSideEffect(sideEffect: ReqresViewModel.SideEffect) {
        when (sideEffect) {
            is ReqresViewModel.SideEffect.GenericError -> {
                Toast.makeText(this, sideEffect.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}


