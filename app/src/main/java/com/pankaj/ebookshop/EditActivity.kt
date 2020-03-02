package com.pankaj.ebookshop

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.pankaj.ebookshop.databinding.ActivityEditBinding
import com.pankaj.ebookshop.model.Book

class EditActivity : AppCompatActivity() {

    private var book:Book?=null
    private var activityEditBinding:ActivityEditBinding?=null
    private var clickHandler:EditOnClickHandler?=null
    private var flag:String = ""
    private var book_id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        book = Book()
        activityEditBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit)
        activityEditBinding?.book = book
        clickHandler = EditOnClickHandler(this)
        activityEditBinding?.clickHandlers = clickHandler

        val intent =intent
        if(intent.hasExtra("book_id")){
            book?.bookName = intent.getStringExtra("book_name")
            book?.unitPrice = intent.getStringExtra("unit_price")
            book?.bookId = intent.getIntExtra("book_id",0)
            flag = "old"
        }
    }

    public inner class EditOnClickHandler(context: Context){
        var context:Context?=null

        init {
            this.context = context
        }

        public fun onClickSubmit(view:View){
            Toast.makeText(this@EditActivity,book?.bookName+book?.unitPrice,
                Toast.LENGTH_SHORT).show()

            val intent=Intent()
            intent.putExtra("book_name",book?.bookName)
            intent.putExtra("unit_price",book?.unitPrice)
            if(flag=="old"){
                intent.putExtra("book_id",book?.bookId)
            }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

}
