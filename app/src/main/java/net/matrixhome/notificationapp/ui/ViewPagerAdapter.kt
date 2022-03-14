package net.matrixhome.notificationapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.matrixhome.notificationapp.R
import net.matrixhome.notificationapp.model.PageRoom

class ViewPagerAdapter(private val itemClickListener: ButtonClickListener
): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var items = mutableListOf<PageRoom>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewpage_fragment, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            pageNumber.text = (items[position].id + 1).toString()
            minusBtn.setOnClickListener { itemClickListener.onButtonClick(it) }
            plusBtn.setOnClickListener { itemClickListener.onButtonClick(it) }
            pushBtn.setOnClickListener { itemClickListener.onButtonClick(it) }
        }
    }

    fun update(newItems: MutableList<PageRoom>){
        val diffUtil: DiffUtil.DiffResult
                = DiffUtil.calculateDiff(PageComparator(this.items, newItems))
        this.items.clear()
        this.items.addAll(newItems)
        diffUtil.dispatchUpdatesTo(this)
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val plusBtn: Button = item.findViewById(R.id.btn_plus)
        val minusBtn: Button = item.findViewById(R.id.btn_minus)
        val pushBtn: Button = item.findViewById(R.id.push_button)
        val pageNumber: TextView = item.findViewById(R.id.fragment_number)
    }

    interface ButtonClickListener{
        fun onButtonClick(view: View)
    }
}