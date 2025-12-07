package com.example.gmail1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager // Import LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView // Import RecyclerView

class MainActivity : AppCompatActivity() {
    // It's better to initialize the list directly if possible, or make it nullable.
    // For this example, we'll initialize it in onCreate.
    private lateinit var emails: List<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up the window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // 1. Lookup the RecyclerView in activity layout
        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)

        // 2. Fetch the list of emails
        emails = EmailFetcher.getEmails()

        // 3. Create adapter passing in the list of emails

        val adapter = EmailAdapter(emails)

        // 4. Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = adapter

        // 5. Set the layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)
    }
}
