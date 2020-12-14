package ca.qc.cgodin.restaurant.NearbySearch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.Favoris
import ca.qc.cgodin.restaurant.RoomDatabase.UserFavorisCrossRef
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleSearchZomato.Locations
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.restaurant_detail_item.view.*
import ca.qc.cgodin.restaurant.modeleSearchZomato.Location
import ca.qc.cgodin.restaurant.modeleSearchZomato.Restaurant
import com.bumptech.glide.Glide


class RestoDetailsAdapter(val viewModel: RestoViewModel,val idUser:Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // private var detailsResto: Result? = null
    lateinit var resto: Restaurant
    var details: DetailResto? = null


    var id: Int = 0

   // lateinit var favoris: Favoris

    //  var details: String? = null
    lateinit var lstLocations: ArrayList<Location>
    private lateinit var onItem1ClickListener: ((Favoris) -> Unit)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDetailsImage = itemView.ivDetailsImage;
        val tvNumTel = itemView.tvNumTel;
        val tvDescriptionDetails = itemView.tvDescriptionDetails;
        val tvMenu = itemView.tvMenu;
        val tvHeureOuverture = itemView.tvHeureOuverture;
        val fab = itemView.fabLocation;
        val fabFavoris = itemView.fabFavoris;

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

             val favoris = details?.R?.res_id?.let { Favoris( it.toLong()) }

            webViewMenu.apply {
                webViewClient = WebViewClient()
                details?.menu_url?.let { loadUrl(it) }
            }

            var lstLoc: Locations? = lstLocations?.let { Locations(it) }

            fabLocation.setOnClickListener {
                onItemClickListener?.let { lstLoc?.let { it1 -> it(it1) } }
            }
            fabFavoris.setOnClickListener {


                if (favoris != null) {


                  //  onItem1ClickListener(favoris)
                    //val id = intent.getIntArrayExtra("idUser")

                    viewModel.insertFavoris(favoris)
                    val crossRef = UserFavorisCrossRef(idUser,favoris.RestoId) // voir comment obtenir le id de la personne qui a ajoute un resto dans ses favoris
                    viewModel.insertUserFavorisCrossRef(crossRef)
                    Toast.makeText(holder.itemView.context,"favoris a été ajouté dans la BD", Toast.LENGTH_LONG).show()
                    Toast.makeText(holder.itemView.context,"Valeur id User dans le adapteur ${idUser} , valeurr de favorisId ${favoris.RestoId}", Toast.LENGTH_LONG).show()
                }


             //   val favoris = Favoris(0,)
               // setOnItem1ClickListener()
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
        Log.i("Info:", "DANS LE IF (})")
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

    fun setOnItem1ClickListener(listener: (Favoris)-> Unit ){
        onItem1ClickListener = listener
    }

    override fun getItemCount() = 1
}


