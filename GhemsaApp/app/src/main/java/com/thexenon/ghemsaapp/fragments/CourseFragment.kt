package com.thexenon.ghemsaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.thexenon.ghemsaapp.R
import com.thexenon.ghemsaapp.adapters.CourseAdapter
import com.thexenon.ghemsaapp.models.News


class CourseFragment : Fragment() {
    private  lateinit var dbref: DatabaseReference
//    private lateinit var yearrecyclerview : RecyclerView
    private lateinit var year1recyclerview : RecyclerView
    private lateinit var year2recyclerview : RecyclerView
    private lateinit var year3recyclerview : RecyclerView
    private lateinit var year4recyclerview : RecyclerView
    private lateinit var year1btn : LinearLayout
    private lateinit var year2btn : LinearLayout
    private lateinit var year3btn : LinearLayout
    private lateinit var year4btn : LinearLayout
//    private lateinit var newsarraylist : ArrayList<News>
    private lateinit var newsarraylist1 : ArrayList<News>
    private lateinit var newsarraylist2 : ArrayList<News>
    private lateinit var newsarraylist3: ArrayList<News>
    private lateinit var newsarraylist4 : ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        yearrecyclerview = view.findViewById(R.id.yearrecycler)
//        yearrecyclerview.layoutManager = LinearLayoutManager(context)
//        yearrecyclerview.setHasFixedSize(true)
        year1recyclerview = view.findViewById(R.id.year1recycler)
        year1recyclerview.layoutManager = LinearLayoutManager(context)
        year1recyclerview.setHasFixedSize(true)
        year2recyclerview = view.findViewById(R.id.year2recycler)
        year2recyclerview.layoutManager = LinearLayoutManager(context)
        year2recyclerview.setHasFixedSize(true)
        year3recyclerview = view.findViewById(R.id.year3recycler)
        year3recyclerview.layoutManager = LinearLayoutManager(context)
        year3recyclerview.setHasFixedSize(true)
        year4recyclerview = view.findViewById(R.id.year4recycler)
        year4recyclerview.layoutManager = LinearLayoutManager(context)
        year4recyclerview.setHasFixedSize(true)

        year1btn = view.findViewById(R.id.year1recyclerview)
        year2btn = view.findViewById(R.id.year2recyclerview)
        year3btn = view.findViewById(R.id.year3recyclerview)
        year4btn = view.findViewById(R.id.year4recyclerview)

        year1btn.setOnClickListener {
            if(year1recyclerview.visibility == View.VISIBLE){
                year1recyclerview.visibility = View.GONE
            } else {
            year1recyclerview.visibility = View.VISIBLE
            }
        }
        year2btn.setOnClickListener {
            if(year2recyclerview.visibility == View.VISIBLE){
                year2recyclerview.visibility = View.GONE
            } else {
                year2recyclerview.visibility = View.VISIBLE
            }
        }
        year3btn.setOnClickListener {
            if(year3recyclerview.visibility == View.VISIBLE){
                year3recyclerview.visibility = View.GONE
            } else {
                year3recyclerview.visibility = View.VISIBLE
            }
        }
        year4btn.setOnClickListener {
            if(year4recyclerview.visibility == View.VISIBLE){
                year4recyclerview.visibility = View.GONE
            } else {
                year4recyclerview.visibility = View.VISIBLE
            }
        }

//        newsarraylist = arrayListOf<News>()
        newsarraylist1 = arrayListOf<News>()
        newsarraylist2 = arrayListOf<News>()
        newsarraylist3 = arrayListOf<News>()
        newsarraylist4 = arrayListOf<News>()
//        getYearData()
        getYear1Data()
        getYear2Data()
        getYear3Data()
        getYear4Data()
    }

//    private fun getYearData() {
//        dbref = FirebaseDatabase.getInstance().getReference("Courses")
//        dbref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    for (newsfiles in snapshot.children) {
//                        val news = newsfiles.getValue(News::class.java)
//                        newsarraylist.add(news!!)
//                    }
//                    val adapter = CourseAdapter(newsarraylist)
//                    yearrecyclerview.adapter = adapter
//                    adapter.setOnNewsClickListener(object : CourseAdapter.onNewsClickListener{
//                        override fun onNewsClick(adapterPosition: Int) {
////
//                        }
//
//                    })
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//
//
//    }

    private fun getYear1Data() {
        dbref = FirebaseDatabase.getInstance().getReference("Courses/Year 1")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (newsfiles in snapshot.children) {
                        val news = newsfiles.getValue(News::class.java)
                        newsarraylist1.add(news!!)
                    }
                    val adapter = CourseAdapter(newsarraylist1)
                    year1recyclerview.adapter = adapter
                    adapter.setOnNewsClickListener(object : CourseAdapter.onNewsClickListener{
                        override fun onNewsClick(adapterPosition: Int) {
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
    private fun getYear2Data() {
        dbref = FirebaseDatabase.getInstance().getReference("Courses/Year 2")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (newsfiles in snapshot.children) {
                        val news = newsfiles.getValue(News::class.java)
                        newsarraylist2.add(news!!)
                    }
                    val adapter = CourseAdapter(newsarraylist2)
                    year2recyclerview.adapter = adapter
                    adapter.setOnNewsClickListener(object : CourseAdapter.onNewsClickListener{
                        override fun onNewsClick(adapterPosition: Int) {
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
    private fun getYear3Data() {
        dbref = FirebaseDatabase.getInstance().getReference("Courses/Year 3")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (newsfiles in snapshot.children) {
                        val news = newsfiles.getValue(News::class.java)
                        newsarraylist3.add(news!!)
                    }
                    val adapter = CourseAdapter(newsarraylist3)
                    year3recyclerview.adapter = adapter
                    adapter.setOnNewsClickListener(object : CourseAdapter.onNewsClickListener{
                        override fun onNewsClick(adapterPosition: Int) {
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
    private fun getYear4Data() {
        dbref = FirebaseDatabase.getInstance().getReference("Courses/Year 4")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (newsfiles in snapshot.children) {
                        val news = newsfiles.getValue(News::class.java)
                        newsarraylist4.add(news!!)
                    }
                    val adapter = CourseAdapter(newsarraylist4)
                    year4recyclerview.adapter = adapter
                    adapter.setOnNewsClickListener(object : CourseAdapter.onNewsClickListener{
                        override fun onNewsClick(adapterPosition: Int) {
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }



}