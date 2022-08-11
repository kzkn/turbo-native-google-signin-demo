package com.kazkn.turbogooglesignindemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.widget.Button
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.tasks.Task
import dev.hotwire.turbo.fragments.TurboFragment
import dev.hotwire.turbo.nav.TurboNavGraphDestination
import dev.hotwire.turbo.visit.TurboVisitAction
import dev.hotwire.turbo.visit.TurboVisitOptions


@TurboNavGraphDestination("turbo://fragment/sign_in")
class SignInFragment : TurboFragment() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val signInLauncher = registerForActivityResult(
        GoogleSignInActivityResultContract()
    ) { res ->
        this.handleSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("<YOUR OAUTH CLIENT ID HERE>")
            .requestEmail()
            .requestProfile()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        view.findViewById<Button>(R.id.sign_in_button).setOnClickListener { handleSignInButtonClick() }
        return view
    }

    private fun handleSignInButtonClick() {
        val intent = googleSignInClient.signInIntent
        signInLauncher.launch(intent)
    }

    private fun handleSignInResult(resultTask: Task<GoogleSignInAccount>) {
        val account = resultTask.result
        CookieManager.getInstance().setCookie(MainSessionNavHostFragment.BASE_URL, "id_token=${account.idToken}") {
            navigate(
                "${MainSessionNavHostFragment.BASE_URL}/app_sign_in",
                TurboVisitOptions(action = TurboVisitAction.REPLACE)
            )
        }
    }
}
