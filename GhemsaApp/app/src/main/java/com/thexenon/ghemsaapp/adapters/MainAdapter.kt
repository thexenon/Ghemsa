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

class MainAdapter(private val newslist: ArrayList<News>) : RecyclerView.Adapter<MainAdapter.MyviewHolder>(){

    private lateinit var mListener : onNewsClickListener
    interface onNewsClickListener {
        fun onNewsClick(adapterPosition: Int)
    }
    fun setOnNewsClickListener(listener: onNewsClickListener){
        mListener = listener
    }

    class MyviewHolder(itemView : View, listener: onNewsClickListener?) : RecyclerView.ViewHolder(itemView) {
        val posttitle = itemView.findViewById<TextView>(R.id.newsheadlarge)
        val postsubtitle = itemView.findViewById<TextView>(R.id.newsdesclagrge)
        val postbody = itemView.findViewById<TextView>(R.id.newsbodylagrge)
        val postimage = itemView.findViewById<ImageView>(R.id.newsimagelarge)

        init {
            itemView.setOnClickListener{
                listener?.onNewsClick(adapterPosition)
                if (postbody.visibility == View.VISIBLE){
                    postbody.visibility = View.GONE
                } else {
                    postbody.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_layout_file, parent, false)
        return MyviewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val newsitems = newslist[position]
        holder.posttitle.text = newsitems.PostTitle
        holder.postsubtitle.text = newsitems.PostSubTitle
        holder.postbody.text = newsitems.PostDesc
        Glide.with(holder.itemView).load(newsitems.PostImage).into(holder.postimage)

    }

    override fun getItemCount(): Int {
        return newslist.size
    }


}