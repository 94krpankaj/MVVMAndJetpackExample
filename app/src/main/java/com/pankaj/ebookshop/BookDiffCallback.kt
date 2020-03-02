package com.pankaj.ebookshop

import androidx.recyclerview.widget.DiffUtil
import com.pankaj.ebookshop.model.Book

class BookDiffCallback(oldBookList: ArrayList<Book>,newBookList: ArrayList<Book>) : DiffUtil.Callback() {

    var oldBookList: ArrayList<Book>? = null
    var newBookList: ArrayList<Book>? = null

    init {
        this.oldBookList = oldBookList
        this.newBookList = newBookList
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBookList?.get(oldItemPosition)?.bookId== newBookList?.get(newItemPosition)?.bookId
    }

    override fun getOldListSize(): Int {

        if (oldBookList == null)
            return 0
        else
            return oldBookList?.size!!
    }

    override fun getNewListSize(): Int {

        if (newBookList == null)
            return 0
        else
            return newBookList?.size!!
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBookList?.get(oldItemPosition)?.bookName.equals(newBookList?.get(newItemPosition)?.bookName)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}