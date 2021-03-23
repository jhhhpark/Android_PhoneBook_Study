package com.hellow.phonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Detail_PhoneBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail__phone_book)
        // TextView Name, Phone 변수
        val strName = findViewById<TextView>(R.id.detailName)
        val strPhone = findViewById<TextView>(R.id.detailNumber)

        // 인텐트로 받아온 이름, 전화번호 값을 name, phone에 저장
        strName.text = intent.getStringExtra("Name")
        strPhone.text = intent.getStringExtra("Phone")

    }
}