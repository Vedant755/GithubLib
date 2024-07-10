package com.hadiyarajesh.mad_s05

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hadiyarajesh.mad_s05.testing.instrumented.ui.TestComposeUiScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeUiScreenTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testOutlinedTextFieldAndButton() {
        composeTestRule.activity.setContent {
            TestComposeUiScreen()
        }

        // Type in TextField
        composeTestRule.onNodeWithTag("textField")
            .performTextInput("This is a random text written into TextField")

        // Click on button
        composeTestRule.onNodeWithText("Show Text").performClick()

        // CHeck if text is displayed or not
        composeTestRule.onNodeWithTag("text").assertIsDisplayed()
    }
}
