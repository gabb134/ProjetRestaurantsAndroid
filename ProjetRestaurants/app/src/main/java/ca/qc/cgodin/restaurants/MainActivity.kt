package ca.qc.cgodin.restaurants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ca.qc.cgodin.restaurants.Maps.RestaurantsMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onCLickMap (view: View){
        var intent = Intent(this, RestaurantsMap::class.java)
        startActivity(intent)
       // intent.ac

    }

}