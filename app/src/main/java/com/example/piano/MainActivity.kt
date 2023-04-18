package com.example.piano


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val doButton = findViewById<Button>(R.id.doButton)
        val reButton = findViewById<Button>(R.id.reButton)

        doButton.setOnClickListener {
            // "Do" butonuna tıklandığında burası çalışır
        }

        reButton.setOnClickListener {
            // "Re" butonuna tıklandığında burası çalışır
        }
    }
}
