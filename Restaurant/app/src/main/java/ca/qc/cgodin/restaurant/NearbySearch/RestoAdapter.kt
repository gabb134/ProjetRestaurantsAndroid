package ca.qc.cgodin.restaurant.NearbySearch

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.Favoris
import ca.qc.cgodin.restaurant.modeleSearch.Result
import ca.qc.cgodin.restaurant.modeleSearchZomato.Restaurant
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_details_resto.view.*
import kotlinx.android.synthetic.main.item_article_preview.view.*
import kotlinx.android.synthetic.main.restaurant_detail_item.view.*
import java.lang.Exception


class RestoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var nearbySearch: List<Result> = emptyList()

    private var favoris : List<Favoris> = emptyList()

    private var search: List<Restaurant> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNameResto: TextView = itemView.findViewById(R.id.tvTitle)
        val place_id: TextView = itemView.findViewById(R.id.place_id)
        val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvPublishedAt)
        val tvAddress: TextView = itemView.findViewById(R.id.tvTitle)
        val tvImg: ImageView = itemView.findViewById(R.id.ivRestoImage)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.apply {

            tvTitle.text = search[position].restaurant.name;
            tvPublishedAt.text = search[position].restaurant.user_rating.rating_text;
            tvSource.text = search[position].restaurant.location.address;
            place_id.text = search[position].restaurant.id;
            rating.rating = search[position].restaurant.user_rating.aggregate_rating.toString().toFloat();
            Glide.with(this).load(search[position].restaurant.featured_image).into(ivRestoImage)

            setOnClickListener {
                onItemClickListener?.let { it(search[position]) }
            }
        }
    }

    private var onItemClickListener: ((Restaurant) -> Unit)? = null

    fun setOnItemClickListener(listener: (Restaurant) -> Unit) {
        onItemClickListener = listener
    }
    override fun getItemCount() = search.size

    fun setNearbySearch(nearbySearch: List<Result>){
        this.nearbySearch = nearbySearch;
        notifyDataSetChanged()
    }

    fun setDetailsMenuResto(search: List<Restaurant>){
        this.search = search;
        notifyDataSetChanged()
    }

    fun setfavoris(favoris: List<Favoris>) {
        this.favoris = favoris
        notifyDataSetChanged()
    }


}
