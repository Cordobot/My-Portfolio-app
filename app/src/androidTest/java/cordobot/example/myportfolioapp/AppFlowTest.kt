package cordobot.example.myportfolioapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import cordobot.example.myportfolioapp.presentation.MainScreen
import cordobot.example.myportfolioapp.ui.theme.MyPortfolioAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class AppFlowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_homeScreen_is_displayed() {
        composeTestRule.setContent {
            MyPortfolioAppTheme {
                MainScreen()
            }
        }

        // Verifica que tu nombre aparezca en la pantalla de inicio
        composeTestRule.onNodeWithText("Adrián Alvarez").assertIsDisplayed()
        
        // Verifica que el rol aparezca
        composeTestRule.onNodeWithText("Android Developer.").assertIsDisplayed()
    }
}
