package com.dinesh.shaadi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dinesh.shaadi.R
import com.dinesh.shaadi.data.entities.MatchDisplayModel
import com.dinesh.shaadi.data.entities.MatchStatus
import com.dinesh.shaadi.databinding.ListItemMatchesBinding

class MatchesAdapter(
    private var items: MutableList<MatchDisplayModel> = mutableListOf(),
    var callback: (data: MatchDisplayModel) -> Unit
) :
    RecyclerView.Adapter<MatchesAdapter.MatchesHolder>() {

    inner class MatchesHolder(var binding: ListItemMatchesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(matchDisplayModel: MatchDisplayModel) {
            matchDisplayModel.also {
                if (it.image.isNotEmpty()) {
                    Glide.with(binding.root)
                        .load(it.image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(RoundedCorners(15))
                        .into(binding.matchImage)
                }

                binding.tvMatchName.text = it.name
                binding.tvMatchAge.text = it.age
                binding.tvMatchLocation.text = it.location
                binding.tvMatchGender.text = it.gender
                binding.statusGroupBtn.visibility =
                    if (it.accepted_state == MatchStatus.PENDING) View.VISIBLE else View.GONE
                if (binding.statusGroupBtn.visibility == View.GONE) {
                    binding.tvStatusMessage.visibility = View.VISIBLE
                    binding.tvStatusMessage.text = when (it.accepted_state) {
                        MatchStatus.PENDING -> ""
                        MatchStatus.ACCEPTED -> binding.root.context.getString(R.string.msg_accepted)
                        MatchStatus.REJECTED -> binding.root.context.getString(R.string.msg_declined)
                    }
                } else
                    binding.tvStatusMessage.visibility = View.GONE

                binding.btnMatchAccept.setOnClickListener {
                    callback(matchDisplayModel.apply {
                        accepted_state = MatchStatus.ACCEPTED
                    })
                }

                binding.btnMatchReject.setOnClickListener {
                    callback(matchDisplayModel.apply {
                        accepted_state = MatchStatus.REJECTED
                    })
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesHolder {
        val binding: ListItemMatchesBinding =
            ListItemMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchesHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchesHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newList: MutableList<MatchDisplayModel>) {
        val diff = DiffUtil.calculateDiff(MatchesDiffCallback(items, newList))
        items = newList
        diff.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    class MatchesDiffCallback(
        var oldItems: MutableList<MatchDisplayModel>,
        var newItems: MutableList<MatchDisplayModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].id == newItems[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }
}