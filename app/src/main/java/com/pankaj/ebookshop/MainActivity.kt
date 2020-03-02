package com.pankaj.ebookshop

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pankaj.ebookshop.databinding.ActivityMainBinding
import com.pankaj.ebookshop.model.Book
import com.pankaj.ebookshop.model.Category
import com.pankaj.ebookshop.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var activityMainDataMainBinding: ActivityMainBinding? = null
    private var clickHandlers: MainActivityClickHandlers? = null
    private var categoryList: ArrayList<Category>? = null
    private var bookList: ArrayList<Book>? = null
    private var selectedCategory: Category? = null
    private var recyclerView: RecyclerView? = null
    private var booksAdapter: BooksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        activityMainDataMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        clickHandlers = MainActivityClickHandlers(this)
        activityMainDataMainBinding?.clickHandlers = clickHandlers

        mainActivityViewModel.getAllCategories()
            .observe(this, Observer<List<Category>> { category ->
                //for(i in category){
                Log.d("category", category.size.toString())
                categoryList = category as ArrayList<Category>?
                // }
                showOnSpinner()
            })


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


    }

    private fun showOnSpinner() {
        val adapter: ArrayAdapter<Category> = ArrayAdapter<Category>(
            applicationContext,
            R.layout.cell_spinner,
            categoryList!!
        )
        adapter.setDropDownViewResource(R.layout.cell_spinner)
        activityMainDataMainBinding!!.spinnerAdapter = adapter
    }


    public inner class MainActivityClickHandlers(val context: Context) {
        fun onFabClick(view: View) {
            // Toast.makeText(context, "I m clicked!!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivityForResult(intent, 1001)
        }

        fun onSelectItem(
            parent: AdapterView<*>,
            view: View?,
            pos: Int,
            id: Long
        ) {
            selectedCategory = parent.getItemAtPosition(pos) as Category
            val message =
                " id is " + selectedCategory!!.id + "\n name is " + selectedCategory!!.categoryName + "\n email is " + selectedCategory!!.categoryDescription
            // Showing selected spinner item
            Toast.makeText(parent.context, message, Toast.LENGTH_LONG).show()

            loadBooksArrayList(selectedCategory!!.id!!)
        }


    }

    private fun loadRecyclerView() {
        recyclerView = activityMainDataMainBinding?.secondaryLayout?.bookRv
        recyclerView?.layoutManager = LinearLayoutManager(this)
        booksAdapter = BooksAdapter()
        recyclerView?.adapter = booksAdapter
        booksAdapter?.setBooks(bookList)
        booksAdapter?.setListner { book ->
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            intent.putExtra("book_id", book.bookId)
            intent.putExtra("book_name", book.bookName)
            intent.putExtra("unit_price", book.unitPrice)
            startActivityForResult(intent, 1002)
        }
    }

    private fun loadBooksArrayList(categoryId: Int) {
        mainActivityViewModel.getBooksOfSelectedCategories(categoryId)
            .observe(this, Observer<List<Book>> { book ->
                //                for(i in book){
//                    Log.d("book",i.bookName!!)
//                }
                Log.d("book", book.size.toString())
                bookList = book as ArrayList<Book>
                loadRecyclerView()
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            val book = Book()
          //  Toast.makeText(this,data?.getStringExtra("book_name")+data?.getStringExtra("unit_price"),Toast.LENGTH_SHORT).show()
            book.bookName = data?.getStringExtra("book_name")
            book.unitPrice = data?.getStringExtra("unit_price")
            book.categoryId = selectedCategory?.id
            mainActivityViewModel.addNewBook(book)
        }else if (requestCode == 1002 && resultCode == Activity.RESULT_OK) {
            val book = Book()
          //  Toast.makeText(this,data?.getStringExtra("book_name")+data?.getStringExtra("unit_price")+data?.getIntExtra("book_id",0),Toast.LENGTH_SHORT).show()
            book.bookName = data?.getStringExtra("book_name")
            book.unitPrice = data?.getStringExtra("unit_price")
            book.bookId = data?.getIntExtra("book_id",0)
            book.categoryId = selectedCategory?.id
            mainActivityViewModel.updateNewBook(book)
        }
    }
}
