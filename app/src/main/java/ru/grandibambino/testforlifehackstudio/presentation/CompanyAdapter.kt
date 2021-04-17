package ru.grandibambino.testforlifehackstudio.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.grandibambino.testforlifehackstudio.R
import ru.grandibambino.testforlifehackstudio.domain.data.Company
import ru.grandibambino.testforlifehackstudio.utils.image.ImageLoader

class CompanyAdapter(
    private var data: MutableList<Company>,
    private val imageLoader: ImageLoader,
    private val itemClickListener: ItemClickListener
    ) : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.company_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(data[position], imageLoader)
        holder.itemClick(itemClickListener, data[position])
    }

    override fun getItemCount() = data.size

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(company: Company, imageLoader: ImageLoader){
            val imageCompany: ImageView = itemView.findViewById(R.id.image_company_item)
            val nameCompany: TextView = itemView.findViewById(R.id.name_company_item)

            imageLoader.loadImage(company.image, imageCompany)
            nameCompany.text = company.name
        }

        fun itemClick(itemClickListener: ItemClickListener, company: Company){
            itemView.setOnClickListener {
                itemClickListener.itemClick(company)
            }
        }
    }

    interface ItemClickListener{
        fun itemClick(company: Company)
    }
}
