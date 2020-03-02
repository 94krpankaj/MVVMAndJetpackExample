package com.pankaj.ebookshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pankaj.ebookshop.model.Book
import com.pankaj.ebookshop.model.Category
import com.pankaj.ebookshop.model.EbookShopRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var ebookShopRepository: EbookShopRepository = EbookShopRepository(application)
    private lateinit var allCategories:LiveData<List<Category>>
    private lateinit var booksOfSelectedCategories:LiveData<List<Book>>

    public fun  getAllCategories():LiveData<List<Category>>{
        allCategories = ebookShopRepository.getCategories()
        return  allCategories
    }

    public fun  getBooksOfSelectedCategories(categoryId:Int):LiveData<List<Book>>{
        booksOfSelectedCategories = ebookShopRepository.getBooks(categoryId)
        return  booksOfSelectedCategories
    }

    public fun addNewBook(book: Book){
        ebookShopRepository.insertBook(book)
    }

    public fun deleteNewBook(book: Book){
        ebookShopRepository.deleteBook(book)
    }

    public fun updateNewBook(book: Book){
        ebookShopRepository.updateBook(book)
    }

}