package cordobot.example.myportfolioapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cordobot.example.myportfolioapp.data.remote.ContactRequest
import cordobot.example.myportfolioapp.data.remote.FormspreeApi
import cordobot.example.myportfolioapp.domain.model.Experience
import cordobot.example.myportfolioapp.domain.model.Project
import cordobot.example.myportfolioapp.domain.model.TechSkill
import cordobot.example.myportfolioapp.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PortfolioUiState(
    val isLoading: Boolean = true,
    val projects: List<Project> = emptyList(),
    val experience: List<Experience> = emptyList(),
    val techSkills: List<TechSkill> = emptyList(),
    val contactState: ContactState = ContactState.Idle
)

sealed class ContactState {
    object Idle : ContactState()
    object Loading : ContactState()
    object Success : ContactState()
    data class Error(val message: String) : ContactState()
}

class PortfolioViewModel(
    private val repository: PortfolioRepository,
    private val formspreeApi: FormspreeApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioUiState())
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val projects = repository.getProjects()
            val experience = repository.getExperience()
            val techSkills = repository.getTechSkills()
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    projects = projects,
                    experience = experience,
                    techSkills = techSkills
                ) 
            }
        }
    }

    fun sendMessage(name: String, email: String, message: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(contactState = ContactState.Loading) }
            try {
                val response = formspreeApi.sendMessage(ContactRequest(name, email, message))
                if (response.isSuccessful) {
                    _uiState.update { it.copy(contactState = ContactState.Success) }
                } else {
                    _uiState.update { it.copy(contactState = ContactState.Error("Error al enviar")) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(contactState = ContactState.Error(e.message ?: "Error")) }
            }
        }
    }

    fun resetContactState() {
        _uiState.update { it.copy(contactState = ContactState.Idle) }
    }
}
