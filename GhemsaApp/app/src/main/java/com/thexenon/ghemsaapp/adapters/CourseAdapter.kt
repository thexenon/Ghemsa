package com.thexenon.ghemsaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thexenon.ghemsaapp.R
import com.thexenon.ghemsaapp.models.News

class CourseAdapter(private val newslist: ArrayList<News>) : RecyclerView.Adapter<CourseAdapter.MyviewHolder>(){

    private lateinit var mListener : onNewsClickListener
    interface onNewsClickListener {
        fun onNewsClick(adapterPosition: Int)
    }
    fun setOnNewsClickListener(listener: onNewsClickListener){
        mListener = listener
    }

    class MyviewHolder(itemView : View, listener: onNewsClickListener?) : RecyclerView.ViewHolder(itemView) {
        val posttitle = itemView.findViewById<TextView>(R.id.idcoursetitle)
        val postsubtitle = itemView.findViewById<TextView>(R.id.idcoursedesc)

        init {
            itemView.setOnClickListener{
                listener?.onNewsClick(adapterPosition)
                if (postsubtitle.visibility == View.VISIBLE){
                    postsubtitle.visibility = View.GONE
                } else {
                    postsubtitle.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.courses_layout, parent, false)
        return MyviewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val newsitems = newslist[position]
        holder.posttitle.text = newsitems.PostTitle
        holder.postsubtitle.text = newsitems.PostSubTitle

    }

    override fun getItemCount(): Int {
        return newslist.size
    }


}