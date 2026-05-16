package cordobot.example.myportfolioapp

import cordobot.example.myportfolioapp.data.remote.ContactRequest
import cordobot.example.myportfolioapp.data.remote.FormspreeApi
import cordobot.example.myportfolioapp.domain.model.Project
import cordobot.example.myportfolioapp.domain.repository.PortfolioRepository
import cordobot.example.myportfolioapp.presentation.ContactState
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: PortfolioRepository
    private lateinit var api: FormspreeApi
    private lateinit var viewModel: PortfolioViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        api = mockk()
        
        coEvery { repository.getProjects() } returns listOf(
            Project(1, "Test", "Desc", emptyList(), "")
        )
        coEvery { repository.getExperience() } returns emptyList()
        coEvery { repository.getTechSkills() } returns emptyList()
        
        viewModel = PortfolioViewModel(repository, api)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadData populates uiState correctly`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.uiState.value
        assertEquals(false, state.isLoading)
        assertEquals(1, state.projects.size)
        assertEquals("Test", state.projects[0].title)
    }

    @Test
    fun `sendMessage success updates contactState to Success`() = runTest {
        coEvery { api.sendMessage(any()) } returns Response.success(Unit)
        
        viewModel.sendMessage("Adrian", "test@test.com", "Hello")
        testDispatcher.scheduler.advanceUntilIdle()
        
        val state = viewModel.uiState.value
        assertTrue(state.contactState is ContactState.Success)
    }
}
