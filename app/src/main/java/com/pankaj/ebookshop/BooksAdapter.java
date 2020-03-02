package com.pankaj.ebookshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.ebookshop.databinding.CellRecyclerviewBinding;
import com.pankaj.ebookshop.model.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyHolder> {

    private onItemClickListner listner;
    private ArrayList<Book> books = new ArrayList();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CellRecyclerviewBinding cellRecyclerviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cell_recyclerview,parent,false);
        return new MyHolder(cellRecyclerviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
      Book book = books.get(position);
      holder.cellRecyclerviewBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private CellRecyclerviewBinding cellRecyclerviewBinding;
       // private ArrayList<Book> books = new ArrayList<>();

        public MyHolder(@NonNull CellRecyclerviewBinding cellRecyclerviewBinding) {
            super(cellRecyclerviewBinding.getRoot());
            this.cellRecyclerviewBinding = cellRecyclerviewBinding;
            cellRecyclerviewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    if (listner != null && clickedPosition != RecyclerView.NO_POSITION) {
                        listner.onItemClick(books.get(clickedPosition));
                    }
                }
            });
        }
    }

    public interface onItemClickListner {
        void onItemClick(Book book);
    }

    public void setListner(onItemClickListner listner) {
        this.listner = listner;
    }

    public void setBooks(ArrayList<Book> newBooks) {
//        this.books = books;
//        notifyDataSetChanged();
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BookDiffCallback(books,newBooks),false);
        books = newBooks;
        result.dispatchUpdatesTo(BooksAdapter.this);

    }
}
