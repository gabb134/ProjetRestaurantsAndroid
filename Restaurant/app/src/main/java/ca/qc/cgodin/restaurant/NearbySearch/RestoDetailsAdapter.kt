package ca.qc.cgodin.restaurant.NearbySearch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleSearchZomato.Locations
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.restaurant_detail_item.view.*
import ca.qc.cgodin.restaurant.modeleSearchZomato.Location
import ca.qc.cgodin.restaurant.modeleSearchZomato.Restaurant
import com.bumptech.glide.Glide


class RestoDetailsAdapter(val viewModel: RestoViewModel) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   // private var detailsResto: Result? = null
     lateinit var resto: Restaurant
    var details: DetailResto? = null
 //  var details: String? = null
    lateinit var lstLocations: ArrayList<Location>


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDetailsImage = itemView.ivDetailsImage;
        val tvNumTel = itemView.tvNumTel;
        val tvDescriptionDetails = itemView.tvDescriptionDetails;
        val tvMenu = itemView.tvMenu;
        val tvHeureOuverture = itemView.tvHeureOuverture;
        val fab = itemView.fabLocation;

        val webView = itemView.webViewMenu;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.apply {

            tvNumTel.text = details?.name
            tvDescriptionDetails.text = details?.R?.res_id.toString()
            //tvHeureOuverture.text = details?.phone_numbers

            tvHeureOuverture.text = details?.featured_image
            Glide.with(this).load(details?.featured_image).into(ivDetailsImage)

            webViewMenu.apply {
                webViewClient = WebViewClient()
                details?.menu_url?.let { loadUrl(it) }}

            var lstLoc: Locations? = lstLocations?.let { Locations(it) }

            fabLocation.setOnClickListener {
                onItemClickListener?.let { lstLoc?.let { it1 -> it(it1) } }
            }

        }





    }
    fun setDetailsResto(resto: Restaurant) {
        this.resto = resto;
        notifyDataSetChanged()
    }

   /* fun setDetailsResto(detailsResto: Result) {
        this.detailsResto = detailsResto;
        notifyDataSetChanged()
    }*/

    fun setDetailsMenuResto(details: DetailResto) {
        Log.i("Info:","DANS LE IF (})")
        this.details = details;
        notifyDataSetChanged()
    }


    fun setLst(lstLocations: ArrayList<Location>) {
        this.lstLocations = lstLocations;
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((Locations) -> Unit)? = null

    fun setOnItemClickListener(listener: (Locations) -> Unit) {
        onItemClickListener = listener
    }
    override fun getItemCount() = 1
}


