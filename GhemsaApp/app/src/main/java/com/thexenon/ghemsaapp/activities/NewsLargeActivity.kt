package com.thexenon.ghemsaapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.thexenon.ghemsaapp.R

class NewsLargeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_large)

        val titleView = findViewById<TextView>(R.id.idnewsheadlarge)
        val subtitleView = findViewById<TextView>(R.id.idnewsdesclagrge)
        val descView = findViewById<TextView>(R.id.idnewsbodylagrge)
        val dateView = findViewById<TextView>(R.id.idnewsdate)
        val timeView = findViewById<TextView>(R.id.idnewstimes)
        val imageView = findViewById<ImageView>(R.id.idnewsimagelarge)

        val bundle : Bundle? = intent.extras
        val title = bundle!!.getString("PostTitle")
        val subtitle = bundle!!.getString("PostSubTitle")
        val body = bundle!!.getString("PostDesc")
        val image = bundle!!.getString("PostImage")
        val date = bundle!!.getString("date")
        val time = bundle!!.getString("time")

        titleView.text = title
        subtitleView.text = subtitle
        descView.text = body
        dateView.text = date
        timeView.text = time
        Glide.with(this@NewsLargeActivity).load(image).into(imageView)
    }
}