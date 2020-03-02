package com.pankaj.ebookshop.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

public class EbookShopRepository(application: Application) {
    private lateinit var categoryDAO: CategoryDAO
    private lateinit var bookDAO: BookDAO
    private lateinit var categories:LiveData<List<Category>>
    private lateinit var books:LiveData<List<Book>>

    init {
        val booksDatabase = BooksDatabase.getInstance(application)
        categoryDAO = booksDatabase.categoryDAO()
        bookDAO = booksDatabase.bookDAO()
    }

    public fun getCategories():LiveData<List<Category>>{
        return categoryDAO.getAllCategories()
    }

    public fun getBooks(categoryId:Int):LiveData<List<Book>>{
        return bookDAO.getBooks(categoryId)
    }

    public fun insertCategory(category: Category){
        InsertCategoryAsyncTAsk(categoryDAO).execute(category)
    }

    private class InsertCategoryAsyncTAsk(categoryDAO: CategoryDAO): AsyncTask<Category, Void, Void>() {
        private var categoryDAO:CategoryDAO?=null

        init {
            this.categoryDAO = categoryDAO
        }

        override fun doInBackground(vararg p0: Category?): Void? {
            categoryDAO?.addCategory(p0[0]!!)
            return null
        }
    }

    public fun insertBook(book: Book){
        InsertBookAsyncTAsk(bookDAO).execute(book)
    }

    private class InsertBookAsyncTAsk(bookDAO: BookDAO): AsyncTask<Book, Void, Void>() {
        private var bookDAO:BookDAO?=null

        init {
            this.bookDAO = bookDAO
        }

        override fun doInBackground(vararg p0: Book?): Void? {
            bookDAO?.addBook(p0[0]!!)
            return null
        }
    }

    public fun deleteCategory(category: Category){
        deleteCategoryAsyncTAsk(categoryDAO).execute(category)
    }

    private class deleteCategoryAsyncTAsk(categoryDAO: CategoryDAO): AsyncTask<Category, Void, Void>() {
        private var categoryDAO:CategoryDAO?=null

        init {
            this.categoryDAO = categoryDAO
        }

        override fun doInBackground(vararg p0: Category?): Void? {
            categoryDAO?.deleteCategory(p0[0]!!)
            return null
        }
    }

    public fun deleteBook(book: Book){
        deleteBookAsyncTAsk(bookDAO).execute(book)
    }

    private class deleteBookAsyncTAsk(bookDAO: BookDAO): AsyncTask<Book, Void, Void>() {
        private var bookDAO:BookDAO?=null

        init {
            this.bookDAO = bookDAO
        }

        override fun doInBackground(vararg p0: Book?): Void? {
            bookDAO?.deleteBook(p0[0]!!)
            return null
        }
    }

    public fun updateCategory(category: Category){
        updateCategoryAsyncTAsk(categoryDAO).execute(category)
    }

    private class updateCategoryAsyncTAsk(categoryDAO: CategoryDAO): AsyncTask<Category, Void, Void>() {
        private var categoryDAO:CategoryDAO?=null

        init {
            this.categoryDAO = categoryDAO
        }

        override fun doInBackground(vararg p0: Category?): Void? {
            categoryDAO?.updateCategory(p0[0]!!)
            return null
        }
    }

    public fun updateBook(book: Book){
        updateBookAsyncTAsk(bookDAO).execute(book)
    }

    private class updateBookAsyncTAsk(bookDAO: BookDAO): AsyncTask<Book, Void, Void>() {
        private var bookDAO:BookDAO?=null

        init {
            this.bookDAO = bookDAO
        }

        override fun doInBackground(vararg p0: Book?): Void? {
            bookDAO?.updateBook(p0[0]!!)
            return null
        }
    }

}