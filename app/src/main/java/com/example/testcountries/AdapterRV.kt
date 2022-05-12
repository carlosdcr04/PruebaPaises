package com.example.testcountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testcountries.model.response.PaisesDTO

class AdapterRV(): RecyclerView.Adapter<AdapterRV.ViewHolder>() {
    var mContext: Context? = null
    private var res: List<PaisesDTO>? = null
    private var clickeo: (PaisesDTO) -> Unit = {}

    constructor(context: Context, results: List<PaisesDTO>, click: (PaisesDTO) -> Unit) : this() {
        mContext = context
        res = results
        clickeo = click
    }

    fun setCountriesList(results: List<PaisesDTO>) {
        res = results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRV.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterRV.ViewHolder, position: Int) {
        holder.tvId!!.text = "idPais: " + res?.get(position)?.idPais
        holder.tvName!!.text = res?.get(position)?.pais
        holder.ivSelected?.visibility = View.GONE
        if(res?.get(position)!!.clickeado) {
            holder.ivSelected?.visibility = View.VISIBLE
        }
        holder.llCountry?.setOnClickListener {
            holder.ivSelected?.visibility = View.VISIBLE
            res?.get(position)!!.clickeado = true
            clickeo(res?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        if(res != null) {
            return res!!.size
        }
        return 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var llCountry: LinearLayout? = null
        var tvId: TextView? = null
        var tvName: TextView? = null
        var ivSelected: ImageView? = null

        init {
            llCountry = itemView.findViewById(R.id.llItemCountry)
            tvId = itemView.findViewById(R.id.tvIdCountry)
            tvName = itemView.findViewById(R.id.tvNameCountry)
            ivSelected = itemView.findViewById(R.id.ivCountrySelected)
        }
    }
}