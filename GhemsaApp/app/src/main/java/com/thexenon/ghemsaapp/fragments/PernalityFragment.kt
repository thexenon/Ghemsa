package com.thexenon.ghemsaapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.thexenon.ghemsaapp.R
import com.thexenon.ghemsaapp.activities.NewsLargeActivity
import com.thexenon.ghemsaapp.adapters.MainAdapter
import com.thexenon.ghemsaapp.adapters.NewsSmallAdapter
import com.thexenon.ghemsaapp.models.News

class PernalityFragment : Fragment() {

    private  lateinit var dbref: DatabaseReference
    private lateinit var newssrecyclerview : RecyclerView
    private lateinit var newsarraylist : ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pernality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newssrecyclerview = view.findViewById(R.id.personrecyclerview)
        newssrecyclerview.layoutManager = LinearLayoutManager(context)
        newssrecyclerview.setHasFixedSize(true)

        newsarraylist = arrayListOf<News>()
        getNewsData()
    }

    private fun getNewsData() {
        dbref = FirebaseDatabase.getInstance().getReference("Personality Posts")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (newsfiles in snapshot.children) {
                        val news = newsfiles.getValue(News::class.java)
                        newsarraylist.add(news!!)
                    }
                    val adapter = MainAdapter(newsarraylist)
                    newssrecyclerview.adapter = adapter
                    adapter.setOnNewsClickListener(object : MainAdapter.onNewsClickListener{
                        override fun onNewsClick(adapterPosition: Int) {
//                            val intent = Intent(activity, NewsLargeActivity::class.java)
//                            intent.putExtra("PostTitle", newsarraylist[adapterPosition].PostTitle)
//                            intent.putExtra("PostSubTitle", newsarraylist[adapterPosition].PostSubTitle)
//                            intent.putExtra("PostDesc", newsarraylist[adapterPosition].PostDesc)
//                            intent.putExtra("PostImage", newsarraylist[adapterPosition].PostImage)
//                            intent.putExtra("date", newsarraylist[adapterPosition].date)
//                            intent.putExtra("time", newsarraylist[adapterPosition].time)
//                            startActivity(intent)
//                            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }



}