package unicauca.movil.beaconsfinalubicuas.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import unicauca.movil.beaconsfinalubicuas.R
import unicauca.movil.beaconsfinalubicuas.databinding.TemplatePlayerBinding
import unicauca.movil.beaconsfinalubicuas.di.ActivityScope
import unicauca.movil.beaconsfinalubicuas.model.Player
import unicauca.movil.beaconsfinalubicuas.util.inflate
import javax.inject.Inject

/**
 * Created by jlbeltran94 on 11/12/2017.
 */

class PlayerAdapter @Inject constructor(): RecyclerView.Adapter<PlayerHolder>() {

    var players:MutableList<Player> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder =
            PlayerHolder(parent.inflate(R.layout.template_player))

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.binding.player = players[position]
    }

    override fun getItemCount(): Int = players.size
}

class PlayerHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var binding: TemplatePlayerBinding = DataBindingUtil.bind(itemView)


}
