package com.pankaj.ebookshop.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
class Category: BaseObservable {

    @Ignore
    constructor(){
    }

    constructor(id:Int, categoryName:String?, categoryDescription:String?){
        this.id = id
        this.categoryName = categoryName
        this.categoryDescription = categoryDescription
    }

    @Bindable
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @Bindable
    @ColumnInfo(name = "category_name")
    var categoryName:String? = null

    @Bindable
    @ColumnInfo(name = "category_description")
    var categoryDescription:String? = null

    override fun notifyPropertyChanged(fieldId: Int) {
        super.notifyPropertyChanged(fieldId)
    }

    override fun toString(): String {
        return this.categoryName!!
    }
}