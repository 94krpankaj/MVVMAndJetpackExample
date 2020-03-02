package com.pankaj.ebookshop.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.*
import com.pankaj.ebookshop.BR

@Entity(
    tableName = "book_table"
    , foreignKeys = arrayOf(
        ForeignKey(
            entity = Category::class
            , parentColumns = arrayOf("id")
            , childColumns = arrayOf("category_id")
            , onDelete = ForeignKey.CASCADE
        )
    )
)
class Book : BaseObservable {

    @Ignore
    constructor() {
    }

    constructor(bookId: Int, bookName: String, unitPrice: String, categoryId: Int) {
        this.bookId = bookId
        this.bookName = bookName
        this.unitPrice = unitPrice
        this.categoryId = categoryId
    }


    @Bindable
    @ColumnInfo(name = "book_id")
    @PrimaryKey(autoGenerate = true)
    var bookId: Int? = null

    @Bindable
    @ColumnInfo(name = "book_name")
    var bookName: String? = null
        set(bookName) {
            field = bookName
            notifyPropertyChanged(BR.bookName)
        }

    @Bindable
    @ColumnInfo(name = "unit_price")
    var unitPrice: String? = null
        set(unitPrice) {
            field = unitPrice
            notifyPropertyChanged(BR.unitPrice)
        }

    @Bindable
    @ColumnInfo(name = "category_id", index = true)
    var categoryId: Int? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (bookId != other.bookId) return false
        if (bookName != other.bookName) return false
        if (unitPrice != other.unitPrice) return false
        if (categoryId != other.categoryId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bookId ?: 0
        result = 31 * result + (bookName?.hashCode() ?: 0)
        result = 31 * result + (unitPrice?.hashCode() ?: 0)
        result = 31 * result + (categoryId ?: 0)
        return result
    }


//    override fun notifyPropertyChanged(fieldId: Int) {
//        super.notifyPropertyChanged(fieldId)
//    }

}