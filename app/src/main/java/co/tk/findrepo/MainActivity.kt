package co.tk.findrepo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEditTxt = findViewById<EditText>(R.id.searchEditText)

        val button = findViewById<Button>(R.id.searchButton)

        button.setOnClickListener {
            val intent = Intent(applicationContext, SearchResultActivity::class.java)
            intent.putExtra("searchTerm", searchEditTxt.text.toString())
            startActivity(intent)

        }
    }
}