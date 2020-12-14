package ca.qc.cgodin.restaurant.NearbySearch

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleMenu.DailyMenu
import ca.qc.cgodin.restaurant.modeleMenu.Menu
import ca.qc.cgodin.restaurant.modeleSearchZomato.Locations
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.restaurant_detail_item.view.*
import ca.qc.cgodin.restaurant.modeleSearchZomato.Location
import ca.qc.cgodin.restaurant.modeleSearchZomato.Restaurant
import kotlin.math.log


class RestoDetailsAdapter(val viewModel: RestoViewModel) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   // private var detailsResto: Result? = null
   lateinit var resto: Restaurant
    private var menu: List<DailyMenu> = emptyList()
    var details: DetailResto? = null
    lateinit var lstLocations: ArrayList<Location>


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }



   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {

            tvNomProduit.text =
                menu.get(1)?.daily_menu?.dishes?.get(position)?.dish?.name
            tvPrix.text =
                menu.get(1)?.daily_menu?.dishes?.get(position)?.dish?.price
            Log.i("Info:",">>>>>>>>>>>>>>>")
    }
}
    fun setDetailsResto(resto: Restaurant) {
        this.resto = resto;
        notifyDataSetChanged()
    }

    fun setDetailsMenuResto(details: DetailResto) {
        //  Log.i("Info:","DANS LE IF (})")
        this.details = details;
        notifyDataSetChanged()
    }

   fun setDetailsMenu(menu: List<DailyMenu>) {
        this.menu = menu;
        Log.i("SIZE", menu.size.toString())
        notifyDataSetChanged()
    }
    override fun getItemCount():Int = menu.size

}


