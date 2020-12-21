package ca.qc.cgodin.restaurant.NearbySearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import kotlinx.android.synthetic.main.resto_item.view.*

class FavorisAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val tvNameResto: TextView = itemView.findViewById(R.id.tvTitle)
        val place_id: TextView = itemView.findViewById(R.id.place_id)
        val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvPublishedAt)
        val tvAddress: TextView = itemView.findViewById(R.id.tvTitle)
        val tvImg: ImageView = itemView.findViewById(R.id.ivRestoImage)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
        return RestoAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {


        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
/*
    var detailsFav: ArrayList<MutableLiveData<DetailResto>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resto_item,parent,false)
        return RestoAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {

            tvSource3.text = detailsFav.size.toString()
        }
    }

    override fun getItemCount(): Int {
        return  detailsFav.size
    }


    fun setDetails(detailsFav: ArrayList<MutableLiveData<DetailResto>>){
        this.detailsFav = detailsFav;
      //  Log.i("FAVORIS","${ detailsFav[0].name}")

        notifyDataSetChanged()
    }*/
}