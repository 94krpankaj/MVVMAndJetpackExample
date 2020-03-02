package com.pankaj.ebookshop.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDAO {


    @Insert
    public fun addCategory(category: Category):Long

    @Update
    public fun updateCategory(category: Category)

    @Delete
    public fun deleteCategory(category: Category)

    @Query("select * from categories_table")
    public fun getAllCategories() :LiveData<List<Category>>

}