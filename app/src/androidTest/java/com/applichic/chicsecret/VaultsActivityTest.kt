package com.applichic.chicsecret

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.applichic.chicsecret.ui.vaults.NewVaultActivity
import com.applichic.chicsecret.ui.vaults.VaultsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class VaultsActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<VaultsActivity>
            = ActivityScenarioRule(VaultsActivity::class.java)


    @Test
    fun checkClickOnTopBarAddVault() {
        onView(withId(R.id.vaults_add_fab))
            .perform(click())

        onView(withId(R.id.new_vault_toolbar)).check(matches(isDisplayed()));
    }
}