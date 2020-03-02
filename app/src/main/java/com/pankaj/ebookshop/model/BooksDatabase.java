package com.pankaj.ebookshop.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class,Book.class},version = 1,exportSchema = false)
public abstract class BooksDatabase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract BookDAO bookDAO();

    private static BooksDatabase instance;

    public static synchronized BooksDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,BooksDatabase.class,"books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsynctask(instance).execute();
        }
    };

    private static class InitialDataAsynctask extends AsyncTask<Void,Void,Void>{

        private CategoryDAO categoryDAO;
        private BookDAO bookDAO;

        public InitialDataAsynctask(BooksDatabase booksDatabase){
            categoryDAO = booksDatabase.categoryDAO();
            bookDAO = booksDatabase.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1 = new Category();
            category1.setCategoryName("Text Books");
            category1.setCategoryDescription("Text Books Description");

            Category category2 = new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryDescription("Novels Description");

            Category category3 = new Category();
            category3.setCategoryName("Other Books");
            category3.setCategoryDescription("Other Books Description");

            categoryDAO.addCategory(category1);
            categoryDAO.addCategory(category2);
            categoryDAO.addCategory(category3);

            Book book1 = new Book();
            book1.setBookName("High school Java");
            book1.setUnitPrice("$150");
            book1.setCategoryId(1);

            Book book2 = new Book();
            book2.setBookName("Maths for begginers");
            book2.setUnitPrice("300");
            book2.setCategoryId(2);

            Book book3 = new Book();
            book3.setBookName("Science");
            book3.setUnitPrice("200");
            book3.setCategoryId(3);

            Book book4 = new Book();
            book4.setBookName("General knowledge");
            book4.setUnitPrice("$160");
            book4.setCategoryId(3);

            bookDAO.addBook(book1);
            bookDAO.addBook(book2);
            bookDAO.addBook(book3);
            bookDAO.addBook(book4);


            return null;
        }
    }

}
