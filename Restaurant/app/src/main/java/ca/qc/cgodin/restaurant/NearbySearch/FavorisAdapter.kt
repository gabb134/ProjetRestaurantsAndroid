package ca.qc.cgodin.restaurant.NearbySearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.Favoris
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleSearchZomato.Restaurant
import ca.qc.cgodin.restaurant.modeleSearchZomato.RestaurantX
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*
import kotlinx.android.synthetic.main.resto_item.view.*

class FavorisAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var favoris : List<Favoris> = emptyList()



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
            tvTitle.text = favoris[position].title;
            rating.rating = favoris[position].rating;
            tvSource.text = favoris[position].adresse;
            place_id.text = favoris[position].RestoId.toString();
            tvPublishedAt.text = favoris[position].phone;
            tvPublishedAt.text = favoris[position].phone;
            Glide.with(this).load(favoris[position].url).into(ivRestoImage)


         //   var resto = RestaurantX(name = favoris[position].title);
            setOnClickListener {
          //      onItemClickListener?.let { it(search[position]) }
            }
         /*   tvTitle.text = search[position].restaurant.name;
            tvPublishedAt.text = search[position].restaurant.user_rating.rating_text;
            tvSource.text = search[position].restaurant.location.address;
            place_id.text = search[position].restaurant.id;
            rating.rating = search[position].restaurant.user_rating.aggregate_rating.toString().toFloat();
            Glide.with(this).load(search[position].restaurant.featured_image).into(ivRestoImage) */


        }
    }

    private var onItemClickListener: ((Restaurant) -> Unit)? = null

    fun setOnItemClickListener(listener: (Restaurant) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = favoris.size
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

    fun setfavoris(favoris: List<Favoris>) {
        this.favoris = favoris
        notifyDataSetChanged()
    }
}