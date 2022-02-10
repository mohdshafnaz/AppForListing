package com.example.appforlisting.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appforlisting.R
import com.example.appforlisting.databinding.AdapterListItemBinding
import com.example.appforlisting.models.response.ResultList
import com.squareup.picasso.Picasso

class MainListAdapter(private var mContext: Context, private var mListener: ItemListener) :
    ListAdapter<ResultList, MainListAdapter.MyViewHolder>(DiffCallback()) {
    private var binding: AdapterListItemBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = AdapterListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val element = getItem(position)

        holder.mBinding?.textviewTitle?.text = element.title
        holder.mBinding?.textviewDescription?.text = element.byline
        holder.mBinding?.textviewDate?.text = element.publishedDate
        if (element.media != null && element.media?.isNotEmpty()!!) {
            if (element.media!![0].mediaMetadata != null) {
                element.media!![0].mediaMetadata!![0].url
                Picasso.get().load(R.color.grey).into(holder.mBinding?.logo)

            }
        }
        holder.mBinding?.cardViewItemDetails?.setOnClickListener {
            mListener.onItemClick(element)
        }
    }


    inner class MyViewHolder(var mBinding: AdapterListItemBinding?) :
        RecyclerView.ViewHolder(
            mBinding?.root!!
        ) {

    }


    fun updateItemList(temp: ArrayList<ResultList>) {
        submitList(temp)
        notifyDataSetChanged()
    }

    fun clearList() {
        submitList(null)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onItemClick(item: ResultList)
    }

    class DiffCallback : DiffUtil.ItemCallback<ResultList>() {
        override fun areItemsTheSame(
            oldItem: ResultList,
            newItem: ResultList
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResultList,
            newItem: ResultList
        ) =
            oldItem == newItem
    }
}