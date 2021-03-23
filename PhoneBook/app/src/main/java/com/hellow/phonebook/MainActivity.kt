package com.hellow.phonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 전화번호부(이름, 전화번호) 리스트 생성
        val phoneList = ArrayList<NumberList>()

        // 이름 및 전화번호 배열
        val nameList = arrayOf("Hello", "James", "Park", "Sony", "June", "Kate",
            "Hello", "James", "Park", "Sony", "June", "Kate",
            "Hello", "James", "Park", "Sony", "June", "Kate",
            "Hello", "James", "Park", "Sony", "June", "Kate")

        val numberList = arrayOf("123", "456", "789", "123", "456",
            "123", "456", "789", "123", "456",
            "123", "456", "789", "123", "456",
            "123", "456", "789", "123", "456",
            "123", "456", "789", "123")

        // 헤드네임 리스트 생성
        val headNameList = mutableListOf<String>()

        for(i in nameList.indices) {
            headNameList.add(nameList[i].substring(0, 1))
        }


        // 리스트에 이름과 전화번호 입력
        for(i in nameList.indices) {
            phoneList.add(NumberList(nameList[i], numberList[i]))
        }

        // itemView를 addView할 container와 inflater 생성
        val container : LinearLayout = findViewById<LinearLayout>(R.id.containerView)
        val inflater : LayoutInflater = this@MainActivity.layoutInflater

        // 상세 페이지 레이아웃 변수 생성
        val detailPhoneBook = findViewById<LinearLayout>(R.id.detailPhoneBoook)

        for(i in nameList.indices) {
            // itemView를 phone_book_main의 LinearLayout에 붙임
            val phoneBook_ItemView = inflater.inflate(R.layout.numberlist_view, container, false)

            // 각각의 headName, Name을 TextView에 추가한다.
            val headName = phoneBook_ItemView.findViewById<TextView>(R.id.txHeadName)
            val fullName = phoneBook_ItemView.findViewById<TextView>(R.id.txFullName)

            headName.text = phoneList[i].name
            fullName.text = phoneList[i].phone

            // 컨테이너에 해당 item View를 붙인다
            container.addView(phoneBook_ItemView)

            phoneBook_ItemView.setOnClickListener {
                // 상세페이지에 이름과 전화번호 데이터 전달
                // 인텐트 생성
                val intent = Intent(this, Detail_PhoneBook::class.java)

                // 이름, 전화번호 데이터 인텐트에 저장
                intent.apply {
                    intent.putExtra("Name", phoneList[i].name)
                    intent.putExtra("Phone", phoneList[i].phone)
                }
                // 인텐트 전달
                startActivity(intent)
            }
        }



    }
}

// 전화번호 리스트 클래스
class NumberList(val name: String, val phone: String) {
}