package com.kazkn.turbogooglesignindemo

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.hotwire.turbo.config.TurboPathConfiguration
import dev.hotwire.turbo.session.TurboSessionNavHostFragment
import kotlin.reflect.KClass

class MainSessionNavHostFragment : TurboSessionNavHostFragment() {
    companion object {
        const val BASE_URL = "https://<YOUR SERVER DOMAIN>"
    }

    override val sessionName = "main"

    override val startLocation = BASE_URL

    override val registeredActivities: List<KClass<out AppCompatActivity>>
        get() = listOf(
        )

    override val registeredFragments: List<KClass<out Fragment>>
        get() = listOf(
            WebFragment::class,
            SignInFragment::class
        )

    override val pathConfigurationLocation: TurboPathConfiguration.Location
        get() = TurboPathConfiguration.Location(
            assetFilePath = "json/configuration.json"
        )
}
