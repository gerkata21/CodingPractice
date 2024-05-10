package com.example.codingpractice.codingExercise.presentation

import androidx.lifecycle.ViewModel
import com.example.codingpractice.codingExercise.domain.ReqresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ReqresViewModel @Inject constructor(
    private val reqresUseCase: ReqresUseCase
): ViewModel(), ContainerHost<ReqresViewModel.State, ReqresViewModel.SideEffect>{

    override val container: Container<State, SideEffect> = container(
        initialState = State.NotVisible
    ) {
        getReqres()
    }

    private suspend fun SimpleSyntax<State, SideEffect>.getReqres() {
        reqresUseCase.execute().let {
            reduce {
                State.Visible(
                    text = it.data.first().first_name
                )
            }
        }
    }

    sealed interface State {
        data object NotVisible: State

        data class Visible(
            val text: String
        ): State
    }

    sealed interface SideEffect {
        data class GenericError(
            val message: String
        ): SideEffect
    }

}