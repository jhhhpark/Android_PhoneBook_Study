package com.hellow.phonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // list생성 및 phoneBook생성
        val phoneBook = createFakePhoneBook(20)
        createPhoneBookList(phoneBook)
    }

    // 생성 할 Person의 갯수를 입력하면 해당 갯수만큼 Person 인스턴스를 List에 추가 전화번호부를 return
    fun createFakePhoneBook(fakeNumber: Int = 10, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 0 until fakeNumber) {
            phoneBook.addPerson(
                    Person("${i}번째 사람", "010-1234-567,${i}")
            )
        }
        return phoneBook
    }

    fun createPhoneBookList(phoneBook: PhoneBook) {
        // 인플레이터와 아이템 뷰를 붙일 컨테이너 생성
        val inflaterLayout = LayoutInflater.from(this@MainActivity)
        val container = findViewById<LinearLayout>(R.id.containerView)

        // headName과 phoneNumber 변수 생성 및 데이터 저장
        for (i in 0 until phoneBook.personList.size) {
            val itemView = layoutInflater.inflate(R.layout.numberlist_view, null)
            val headName = itemView.findViewById<TextView>(R.id.txHeadName)
            val phoneNumber = itemView.findViewById<TextView>(R.id.txHeadName)

            headName.text = phoneBook.personList[i].name
            phoneNumber.text = phoneBook.personList[i].number

            // 생성한 itemView를 container에 addView
            container.addView(itemView)

            // 리스트 클릭 시 상세페이지 이동
            detailPageOpen(phoneBook.personList[i], itemView)

        }
    }

    // 상세 페이지 생성 및 리스트 클릭 시 액티비티 전환
    fun detailPageOpen(person: Person, view: View) {
        view.setOnClickListener {
            // 인텐트 생성 및 서브 액티비티 실행
            val intent = Intent(this@MainActivity, Detail_PhoneBook::class.java)
            intent.putExtra("Name", person.name)
            intent.putExtra("Number", person.number)

            startActivity(intent)
        }
    }
}

// Person인스턴스 List를 가진 PhoneBook 클래스 선언
class PhoneBook() {
    val personList = ArrayList<Person>()
    fun addPerson(person: Person) {
        personList.add(person)
    }
}


// 이름과 전화번호를 가진 Person클래스 선언
class Person(val name: String, val number: String) {

}