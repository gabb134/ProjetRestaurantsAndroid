package ca.qc.cgodin.restaurant.Authentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.User
import ca.qc.cgodin.restaurant.RoomDatabase.UserViewModel
import ca.qc.cgodin.restaurant.RoomDatabase.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory(application)).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        btnEnregistrer.setOnClickListener {
            Enregistrer()
        }

    }
    private fun Enregistrer(){
        val username = edUsername.text.toString()
        val password = edPassword.text.toString()
        val confirmPassword = edPassword2.text.toString()
        val email = edEmail.text.toString()

        val user = User(
            0,
            username,
            password,
            email
        )

        if(inputCheck(username,password,confirmPassword,email)){
            if(password == confirmPassword){
                userViewModel.insert(user)

                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)

                Toast.makeText(applicationContext,"User a été ajouté dans la BD",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(applicationContext,"SVP remplir tout les champs!",Toast.LENGTH_LONG).show()
        }



    }
    private fun inputCheck(username:String,password:String,confirmPassword:String,email:String):Boolean{
        return !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(email))
    }
}