package com.thexenon.ghemsaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thexenon.ghemsaapp.R
import com.thexenon.ghemsaapp.models.News

class NewsSmallAdapter(private val newslist: ArrayList<News>) : RecyclerView.Adapter<NewsSmallAdapter.MyviewHolder>(){

    private lateinit var mListener : onNewsClickListener
    interface onNewsClickListener {
        fun onNewsClick(adapterPosition: Int)
    }
    fun setOnNewsClickListener(listener: onNewsClickListener){
        mListener = listener
    }

    class MyviewHolder(itemView : View, listener: onNewsClickListener?) : RecyclerView.ViewHolder(itemView) {
         val posttitle = itemView.findViewById<TextView>(R.id.newsheadsmall)
         val postsubtitle = itemView.findViewById<TextView>(R.id.newsdescsmall)
         val postdate = itemView.findViewById<TextView>(R.id.newsdatesmall)
         val posttime = itemView.findViewById<TextView>(R.id.newstimesmall)
         val postimage = itemView.findViewById<ImageView>(R.id.newsimagesmall)

        init {
            itemView.setOnClickListener{
                listener?.onNewsClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_layout_file, parent, false)
        return MyviewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val newsitems = newslist[position]
        holder.posttitle.text = newsitems.PostTitle
        holder.postsubtitle.text = newsitems.PostSubTitle
        holder.postdate.text = "Date Posted" + newsitems.date
        holder.posttime.text = "Time Posted" + newsitems.time
        Glide.with(holder.itemView).load(newsitems.PostImage).into(holder.postimage)

    }

    override fun getItemCount(): Int {
        return newslist.size
    }

}