package ca.qc.cgodin.restaurant.NearbySearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_detail_item.view.*
import ca.qc.cgodin.restaurant.modeleDetail.Result


class RestoDetailsAdapter(val viewModel: RestoViewModel) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private  var detailsResto: Result? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivDetailsImage = itemView.ivDetailsImage;
        val tvNumTel = itemView.tvNumTel;
        val tvDescriptionDetails = itemView.tvDescriptionDetails;
        val tvMenu = itemView.tvMenu;
        val tvHeureOuverture = itemView.tvHeureOuverture;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_detail_item,parent,false)
        return RestoDetailsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.apply {
            val url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + (detailsResto?.photos?.get(1 )?.photo_reference
                 ) + "&key=AIzaSyDm2ot4-CDQg6M6ZJSbz0K21cXUklAQYQ0";

             Glide.with(this).load(url).into(ivDetailsImage)
            tvNumTel.text = detailsResto?.formatted_phone_number
            tvDescriptionDetails.text = detailsResto?.website
            tvHeureOuverture.text = detailsResto?.name

        }


    }



    fun setDetailsResto(detailsResto: Result){
        this.detailsResto = detailsResto;
        notifyDataSetChanged()
    }

    override fun getItemCount() = 1
}