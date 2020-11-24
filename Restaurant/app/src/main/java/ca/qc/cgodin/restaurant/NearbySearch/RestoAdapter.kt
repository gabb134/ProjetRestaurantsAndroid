package ca.qc.cgodin.restaurant.NearbySearch

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.modeleSearch.Photo
import ca.qc.cgodin.restaurant.modeleSearch.Result
import ca.qc.cgodin.restaurant.modeleSearchZomato.Restaurant
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import ca.qc.cgodin.restaurant.ui.RestoViewModelProviderFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*
import java.lang.Exception


class RestoAdapter(val viewModel: RestoViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var nearbySearch: List<Result> = emptyList()

    private var search: List<Restaurant> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNameResto: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
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
           // tvDescription.text = search[position].restaurant.featured_image;
            Glide.with(this).load(search[position].restaurant.featured_image).into(ivRestoImage)

            setOnClickListener {
                onItemClickListener?.let { it(search[position]) }
            }
          /*  tvTitle.text = nearbySearch[position].name;
            tvPublishedAt.text = nearbySearch[position].rating.toString();
            tvSource.text = nearbySearch[position].vicinity;
            place_id.text = nearbySearch[position].place_id;


            try {
                val url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" +nearbySearch[position].photos[0].photo_reference + "&key=AIzaSyDm2ot4-CDQg6M6ZJSbz0K21cXUklAQYQ0";
            //    https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=YOUR_API_KEY
                Glide.with(this).load(url).into(ivRestoImage)
            } catch (e: Exception){

            }
            setOnClickListener {
                onItemClickListener?.let { it(nearbySearch[position]) }
            }
*/

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

}
