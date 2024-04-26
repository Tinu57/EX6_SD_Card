package com.example.sd_card

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name:EditText=findViewById(R.id.tv1)
        val reg:EditText=findViewById(R.id.tv2)
        val cgpa:EditText=findViewById(R.id.tv3)
        val save:Button=findViewById(R.id.btn1)
        val load:Button=findViewById(R.id.btn2)

        val na=name.text.toString()
        val re=reg.text.toString()
        val cg=cgpa.text.toString()

        save.setOnClickListener(){
            val file=File(getExternalFilesDir(null),"stud.txt")
            val fos=FileOutputStream(file)
            fos.write("$name, $re, $cg".toByteArray())
            name.text.clear()
            reg.text.clear()
            cgpa.text.clear()
            fos.close()
        }

        load.setOnClickListener {
            val file=File(getExternalFilesDir(null),"stud.txt")
            val fis=FileInputStream(file)
            val isr=InputStreamReader(fis)
            val br=BufferedReader(isr)
            val line=br.readLine()
            var parts=line.split(",")
            name.setText(parts[0])
            reg.setText(parts[1])
            cgpa.setText(parts[2])
            fis.close()
        }
    }
}