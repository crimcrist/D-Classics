package com.example.dclassics;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<AllBookItem> books;

    public BookAdapter(List<AllBookItem> books) {
        this.books = books;
    }

    public void setBooks(List<AllBookItem> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        AllBookItem book = books.get(position);

        holder.imgBook.setImageResource(book.getImageRes());
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvBookAuthor.setText("By " + book.getAuthor());
        holder.tvBookDesc.setText(book.getDescription());

        View.OnClickListener openDetailListener = v -> {
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra("book_title", book.getTitle());
            intent.putExtra("book_author", book.getAuthor());
            intent.putExtra("book_synopsis", book.getDescription());
            intent.putExtra("book_image", book.getImageRes());
            v.getContext().startActivity(intent);
        };

        holder.itemView.setOnClickListener(openDetailListener);
        holder.btnBookNext.setOnClickListener(openDetailListener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView tvBookTitle;
        TextView tvBookAuthor;
        TextView tvBookDesc;
        TextView btnBookNext;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.imgBook);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvBookDesc = itemView.findViewById(R.id.tvBookDesc);
            btnBookNext = itemView.findViewById(R.id.btnBookNext);
        }
    }
}