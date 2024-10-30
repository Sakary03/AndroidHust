package com.huudung.gmail_recycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = ArrayList<ItemModel>()

        itemList.add(ItemModel(R.drawable.j, "Java Academy", "Master Java in 30 days\n" +
                "Comprehensive course covering basics...", "10:15 AM"));

        itemList.add(ItemModel(R.drawable.a, "Apple News", "Unveiling the Latest iPhone\n" +
                "Get all details on the iPhone 15...", "09:45 AM"));

        itemList.add(ItemModel(R.drawable.g, "Google Cloud", "Take Your App to the Cloud\n" +
                "Learn how to seamlessly deploy apps...", "01:33 PM"));

        itemList.add(ItemModel(R.drawable.f, "Foodie Fun", "20 Must-Try Street Foods Around the World\n" +
                "Explore the best flavors globally...", "07:18 PM"));

        itemList.add(ItemModel(R.drawable.t, "Tech Radar", "Top 10 Gadgets of 2024\n" +
                "A sneak peek into the best tech of...", "02:20 PM"));

        itemList.add(ItemModel(R.drawable.c, "Coding Bootcamp", "Learn Python for Free\n" +
                "A beginner’s guide to Python programming...", "11:12 AM"));

        itemList.add(ItemModel(R.drawable.r, "Reddit News", "Most Discussed Topics This Week\n" +
                "See what everyone is talking about...", "04:45 PM"));

        itemList.add(ItemModel(R.drawable.m, "Mindfulness Daily", "5-Minute Meditation Practices\n" +
                "Achieve peace in your daily routine...", "08:30 AM"));

        itemList.add(ItemModel(R.drawable.v, "Vimeo", "New Video Editing Features\n" +
                "Create stunning videos easily with...", "09:55 PM"));

        itemList.add(ItemModel(R.drawable.e, "Etsy", "Handmade Gifts for Every Occasion\n" +
                "Find unique and personalized items...", "06:03 PM"));

        itemList.add(ItemModel(R.drawable.d, "Dribbble", "Trending Design Ideas\n" +
                "Get inspired by the latest design...", "12:40 PM"));

        itemList.add(ItemModel(R.drawable.z, "Zoom Updates", "Enhanced Video Quality in Meetings\n" +
                "Experience clearer video with new...", "10:50 AM"));

        itemList.add(ItemModel(R.drawable.h, "Healthline", "Top 5 Nutritional Tips for 2024\n" +
                "A guide to boosting your health...", "01:10 PM"));

        itemList.add(ItemModel(R.drawable.s, "Spotify", "New Releases This Month\n" +
                "Listen to the hottest tracks...", "02:45 PM"));

        itemList.add(ItemModel(R.drawable.u, "Udemy", "Biggest Sale of the Year\n" +
                "Courses starting at $9.99...", "05:12 PM"));

        itemList.add(ItemModel(R.drawable.w, "Wikipedia", "Did You Know?\n" +
                "Discover interesting facts daily...", "11:01 AM"));

        itemList.add(ItemModel(R.drawable.b, "BBC News", "World News Highlights\n" +
                "Catch up on today’s top stories...", "06:22 PM"));

        itemList.add(ItemModel(R.drawable.k, "Kickstarter", "Support Emerging Creators\n" +
                "Explore new and exciting projects...", "10:30 AM"));

        itemList.add(ItemModel(R.drawable.i, "Instagram", "New Filters & Effects\n" +
                "Check out the latest creative tools...", "04:05 PM"));

        itemList.add(ItemModel(R.drawable.n, "Netflix", "New Movies and Shows This Month\n" +
                "Explore what's trending on Netflix...", "03:48 PM"));

        val listView = findViewById<RecyclerView>(R.id.rview)
        listView.layoutManager = LinearLayoutManager(this)
        listView.setAdapter(MyAdapter(itemList))
    }
}