package ca.qc.cgodin.restaurant.Authentification

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.UserViewModel
import ca.qc.cgodin.restaurant.RoomDatabase.UserViewModelFactory
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory(application)).get(UserViewModel::class.java)
    }

    companion object{
        private const val RC_SIGN_IN = 120
    }


    private lateinit var mCallbackManager : CallbackManager

    private lateinit var loginButton : LoginButton

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        mAuth = FirebaseAuth.getInstance()
        //Initialise Facebook SDK
        FacebookSdk.sdkInitialize(applicationContext)
        // Initialize Facebook Login button

        // Initialize Facebook Login button

        mCallbackManager = CallbackManager.Factory.create()
        loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                // App code
                Log.d("FacebookLogin", "facebook:onSuccess:" + loginResult);
                loginResult?.accessToken?.let { handleFacebookAccessToken(it) };
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })
// ...

/*********************************************************************************************************/
// Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)




        sign_in_btn.setOnClickListener {
            signIn()
        }
        //S'enregistrer localement
        Senregistrer.setOnClickListener {
            senregistrer()
        }

        //Se connecter localement
        btnConnecter.setOnClickListener {
            SeConnecter()
        }
    }
    private fun SeConnecter(){ //Login local, je dois comparé avec le user qui se trouve dans la bd
        val emailLogin = edUserNameEmailLogin.text.toString()
        val passwordLogin = edPasswordLogin.text.toString()

       // Toast.makeText(applicationContext, "Email: ${emailLogin}", Toast.LENGTH_SHORT).show()

        if(inputCheck(emailLogin, passwordLogin)){
            userViewModel.searchUser(emailLogin).observe(
                this, {
                        users -> users.let{
                    //Toast.makeText(applicationContext, "Element dans la liste : ${it.first().Email}", Toast.LENGTH_SHORT).show()

                    if(it.first().Email==emailLogin && it.first().Password == passwordLogin){ //si ils se trouvenet dans la BD

                        val intent = Intent(this, NearbyRestoActivity::class.java)
                        startActivity(intent)
                        edUserNameEmailLogin.text.clear()
                        edPasswordLogin.text.clear()
                        Toast.makeText(applicationContext, "Bienvenue  ${it.first().Username} dans l'application", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(applicationContext,"Ce compte n'existe pas!",Toast.LENGTH_LONG).show()
                    }

                }
                  //test  Toast.makeText(applicationContext, "${it.first().toString()}", Toast.LENGTH_SHORT).show()

                }
            )

        }else{
            Toast.makeText(applicationContext,"Le courriel et/ou le mot de passe ne peuvent pas être vide!",Toast.LENGTH_LONG).show()
        }




    }
    private fun inputCheck(emailLogin:String,passwordLogin:String):Boolean{
        return !(TextUtils.isEmpty(emailLogin) || TextUtils.isEmpty(passwordLogin) )
    }
    private fun senregistrer(){
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }
    private fun signIn() {
        Toast.makeText(applicationContext, "Dans fontion SignIn ", Toast.LENGTH_SHORT).show()
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mCallbackManager.onActivityResult(requestCode, resultCode, data)
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
                Log.i("SignInActivity", exception.toString())
                Toast.makeText(
                    applicationContext,
                    "ERROR : ${exception.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
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
                    Toast.makeText(
                        applicationContext,
                        "Connecter sans probleme",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, NearbyRestoActivity::class.java)
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

    //********************************************FACEBOOK***********************************//

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }*/
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("FacebookLogin", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FacebookLogin", "signInWithCredential:success")
                    val user = mAuth.currentUser

                        updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("FacebookLogin", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

                // ...
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user!=null){
            val intent = Intent(this, NearbyRestoActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Login pour continuer", Toast.LENGTH_SHORT).show()
        }

    }


}