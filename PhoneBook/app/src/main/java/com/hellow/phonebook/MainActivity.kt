package com.hellow.phonebook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 리사이클러뷰 인스턴스 생성
        val phoneBook_Recycler_View : RecyclerView = findViewById<RecyclerView>(R.id.containerView)

        // 어댑터 및 레이아웃 매니저설정
        with(phoneBook_Recycler_View) {
            this.adapter = PhoneBookRecyclerAdapter(
                    phoneBookList = createFakePhoneBook(50),
                    inflater = LayoutInflater.from(this@MainActivity),
                    activity = this@MainActivity)
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
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

// RecyclerView Adapter
// 리사이클러뷰 어댑터 클래스
class PhoneBookRecyclerAdapter (
        val phoneBookList : PhoneBook,
        val inflater : LayoutInflater,
        val activity : Activity
) : RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName : TextView?
        val personNumber: TextView?

        init {
            personName = itemView?.findViewById<TextView>(R.id.txHeadName)
            personNumber = itemView?.findViewById<TextView>(R.id.txPhoneNumber)

            itemView.setOnClickListener {

                // 인텐트 생성
                val intent = Intent(activity, Detail_PhoneBook::class.java)

                // 인텐트에 데이터 저장
                intent.putExtra("Name", phoneBookList.personList[adapterPosition].name)
                intent.putExtra("Number", phoneBookList.personList[adapterPosition].number)

                // 상세 페이지 액티비티 시작
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.numberlist_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneBookList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName?.text = phoneBookList.personList[position].name
        holder.personNumber?.text = phoneBookList.personList[position].number
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