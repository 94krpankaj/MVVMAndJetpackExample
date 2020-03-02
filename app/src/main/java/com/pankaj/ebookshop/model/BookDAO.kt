package com.pankaj.ebookshop.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDAO {

    @Insert
    public fun addBook(book: Book):Long

    @Update
    public fun updateBook(book: Book)

    @Delete
    public fun deleteBook(book: Book)

    @Query("select * from book_table")
    public fun getAllBooks() : LiveData<List<Book>>

    @Query("select * from book_table WHERE category_id = :categoryId")
    public fun getBooks(categoryId:Int) : LiveData<List<Book>>

}