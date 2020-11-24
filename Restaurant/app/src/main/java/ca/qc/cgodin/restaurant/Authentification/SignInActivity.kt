package ca.qc.cgodin.restaurant.Authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    companion object{
        private const val RC_SIGN_IN = 120
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


// Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        mAuth = FirebaseAuth.getInstance()

        sign_in_btn.setOnClickListener {
            signIn()
        }
    }
    private fun signIn() {
        Toast.makeText(applicationContext, "Dans fontion SignIn ", Toast.LENGTH_SHORT).show()
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(applicationContext, "Dans onActivityResult", Toast.LENGTH_SHORT).show()
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception  = task.exception
            if(task.isSuccessful){ // si l'auth marche, on obtient un token et on appel firebaseAuthWithGoogle avec le tocken pour avoir les credentials
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.i("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    Toast.makeText(applicationContext, "jeton obtenue ", Toast.LENGTH_SHORT).show()
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.i("SignInActivity", "Google sign in failed", e)
                    Toast.makeText(applicationContext, "Connexion echouee", Toast.LENGTH_SHORT).show()
                    // ...
                }
            }else{
                Log.i("SignInActivity",  exception.toString())
                Toast.makeText(applicationContext, "ERROR : ${exception.toString()}", Toast.LENGTH_SHORT).show()
            }


        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        Toast.makeText(applicationContext, "Dans firebaseAuthWithGoogle", Toast.LENGTH_SHORT).show()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("SignInActivity", "signInWithCredential:success")
                    Toast.makeText(applicationContext, "Connecter sans probleme", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NearbyRestoActivity::class.java )
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.i("SignInActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(applicationContext, "Mauvais ", Toast.LENGTH_SHORT).show()

                }

                // ...
            }
    }


}