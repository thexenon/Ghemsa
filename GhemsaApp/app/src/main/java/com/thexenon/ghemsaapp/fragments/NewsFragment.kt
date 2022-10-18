package com.thexenon.ghemsaapp.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.thexenon.ghemsaapp.R
import com.thexenon.ghemsaapp.activities.*
import com.thexenon.ghemsaapp.fragments.CourseFragment
import com.thexenon.ghemsaapp.adapters.NewsSmallAdapter
import com.thexenon.ghemsaapp.models.News


class NewsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val programsbtn = view.findViewById<LinearLayout>(R.id.homeprogramotlinebtn)
        val newsbtn = view.findViewById<LinearLayout>(R.id.homenewsbtn)
        val tutorialsbtn = view.findViewById<LinearLayout>(R.id.homevideobtn)
        val slidesbtn = view.findViewById<LinearLayout>(R.id.homeslidesbtn)
        val labworkbtn = view.findViewById<LinearLayout>(R.id.homelabworkbtn)
        val timetablebtn = view.findViewById<LinearLayout>(R.id.hometimetablebtn)
        val pascobtn = view.findViewById<LinearLayout>(R.id.homepascobtn)
        val aboutbtn = view.findViewById<LinearLayout>(R.id.homeaboutbtn)
        val eventsbtn = view.findViewById<LinearLayout>(R.id.homeeventotlinebtn)

        slidesbtn.setOnClickListener { thisActivity(SlidesActivity()) }
        labworkbtn.setOnClickListener { thisActivity(LabWorksActivity()) }
        pascobtn.setOnClickListener { thisActivity(PastQuesActivity()) }
        tutorialsbtn.setOnClickListener { thisActivity(VideoActivity()) }
        timetablebtn.setOnClickListener { changeFragment(TimeFragment()) }
        newsbtn.setOnClickListener { changeFragment(NewsFragment()) }
        programsbtn.setOnClickListener { changeFragment(CourseFragment()) }
        eventsbtn.setOnClickListener { changeFragment(ProgramFragment()) }
        aboutbtn.setOnClickListener { thisActivity(AboutActivity()) }

        newssrecyclerview = view.findViewById(R.id.newsrecyclerview)
        newssrecyclerview.layoutManager = LinearLayoutManager(context)
        newssrecyclerview.setHasFixedSize(true)

        newsarraylist = arrayListOf<News>()
        getNewsData()
    }

    private fun thisActivity(active: Activity){
        startActivity(Intent(activity, active::class.java))
    }

    fun changeFragment(chagFrag: Fragment){
        val fragment = activity?.supportFragmentManager?.beginTransaction()
        fragment?.replace(R.id.main_nav_host, chagFrag)?.commit()
    }

    private fun getNewsData() {
        dbref = FirebaseDatabase.getInstance().getReference("News Posts")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (newsfiles in snapshot.children) {
                        val news = newsfiles.getValue(News::class.java)
                        newsarraylist.add(news!!)
                    }
                    val adapter = NewsSmallAdapter(newsarraylist)
                    newssrecyclerview.adapter = adapter
                    adapter.setOnNewsClickListener(object : NewsSmallAdapter.onNewsClickListener{
                        override fun onNewsClick(adapterPosition: Int) {
                            val intent = Intent(activity, NewsLargeActivity::class.java)
                            intent.putExtra("PostTitle", newsarraylist[adapterPosition].PostTitle)
                            intent.putExtra("PostSubTitle", newsarraylist[adapterPosition].PostSubTitle)
                            intent.putExtra("PostDesc", newsarraylist[adapterPosition].PostDesc)
                            intent.putExtra("PostImage", newsarraylist[adapterPosition].PostImage)
                            intent.putExtra("date", newsarraylist[adapterPosition].date)
                            intent.putExtra("time", newsarraylist[adapterPosition].time)
                            startActivity(intent)
                            //Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
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