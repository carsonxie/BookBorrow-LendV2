package com.example.y.bookborrow_lendv2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.media.Image;

import java.util.ArrayList;

public class SearchResultForBook extends AppCompatActivity {

    private ListView mResultList;
    private ArrayList<book> books = new ArrayList<>();
    private SearchBookAdapter adapter;
    private DatabaseReference mBookDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_for_book);

        mBookDatabase = FirebaseDatabase.getInstance().getReference("book");
        //mSearchWord = (EditText) findViewById(R.id.editText);
        Button newSearch = (Button) findViewById(R.id.NewSearch1);
        Button backHome = (Button) findViewById(R.id.HomeButton1);

        mResultList = findViewById(R.id.ListBook);

        Intent intent = getIntent();
        final String Keyword = intent.getStringExtra("key");



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference booksRef = rootRef.child("book");
        //final ArrayList<book> bookLists = new ArrayList<>();


        // eventListener for searching book title's keyword

        ValueEventListener eventListener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean found;
                // String search = "Elements";
                String search = Keyword;

                //String search = "Trevor Hastie Robert Tibshirani Jerome Friedman";
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    book bookdFound = ds.getValue(book.class);
                    String title = bookdFound.getName();
                    String author = bookdFound.getAuthor();
                    String stat = bookdFound.getStatus();
                    //check if title contains keyword
                    found = title.contains(search);

                    if (found && !stat.equals("accepted") && !stat.equals("borrowed") ) {
                        books.add(bookdFound);
                    }


                    //check if author contains keyword

                    found = author.contains(search);

                    if (found && !stat.equals("accepted") && !stat.equals("borrowed")) {
                        books.add(bookdFound);
                    }



                    String size = Integer.toString(books.size());
                    adapter.notifyDataSetChanged();
                    Log.i("bbbbbbbbb", size);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        booksRef.addListenerForSingleValueEvent(eventListener);








        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultForBook.this, Search.class);
                startActivity(intent);
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultForBook.this,home_page.class);
                startActivity(intent);
            }
        });
        books = new ArrayList<>();
        adapter = new SearchBookAdapter(this, books);
        mResultList.setAdapter(adapter);

        mResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                book bookItem = books.get(position);
                String bookId = bookItem.getID();
                Intent intent = new Intent(SearchResultForBook.this, PublicBookDetails.class);
                intent.putExtra("Id",bookId);
                startActivity(intent);
            }
        });
    }






/*
    private void firebaseBookSearch (String searchText) {
        Toast.makeText(SearchResultForBook.this, "Start Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mBookDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<book> options = new FirebaseRecyclerOptions.Builder<book>().setQuery(firebaseSearchQuery, book.class).setLifecycleOwner(this).build();
        FirebaseRecyclerAdapter<book, BookViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<book, BookViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull book model) {
                holder.book_name.setText(model.getName());
                holder.book_status.setText(model.getStatus());
                //holder.image.setImageDrawable();
                holder.book_description.setText(model.getDescription());

                //Picasso.get().load(model.getPhoto()).into(holder.Photo)
            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                //return null;
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_search_result_for_book, viewGroup, false);
                BookViewHolder viewHolder = new BookViewHolder(view);
                return viewHolder;

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);



    }

    @Override
    protected void onStart(){
        super.onStart();
       // SearchBookAdapter.startListening();


    }

    /*@Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<book> options= new FirebaseRecyclerOptions.Builder<book>().setQuery(firebaseSearchQuery, book.class).setLifecycleOwner(this).build();
        FirebaseRecyclerAdapter<book, BookViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<book, BookViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull book model) {
                holder.book_name.setText(model.getName());
                holder.book_status.setText(model.getStatus());
                //holder.image.setImageDrawable();
                holder.book_description.setText(model.getDescription());


                //Picasso.get().load(model.getPhoto()).into(holder.Photo)
            }

            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                //return null;
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_search_result_for_book, viewGroup, false);
                BookViewHolder viewHolder = new BookViewHolder(view);
                return viewHolder;

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

        firebaseRecyclerAdapter.startListening();


    }*/


    // view book Class
/*
    public static class BookViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView book_name, book_status, book_description;
        public BookViewHolder(View itemView){
            super(itemView);
            mView= itemView;

            book_name= (TextView) itemView.findViewById(R.id.BookName) ;
            book_status = (TextView) itemView.findViewById(R.id.Stat);
            book_description = (TextView) itemView.findViewById(R.id.descrip);
        }

        public void setDetails(Context ctx, String bookName, String bookStatus, String bookDescription, Image bookImage){

            TextView book_name= (TextView) mView.findViewById(R.id.BookName) ;
            TextView book_status = (TextView) mView.findViewById(R.id.Stat);
            TextView book_description = (TextView) mView.findViewById(R.id.descrip);
            //ImageView book_image = (ImageView) mView.findViewById(R.id.BookImage);


            book_name.setText(bookName);
            book_status.setText(bookStatus);
            book_description.setText(bookDescription);

            //Glide.with(ctx).load(bookImage).into(book_image);

        }
    }



    public void newSearch(View view){
        //BookDatabase= FirebaseDatabase.getInstance().getReference("books");



    }

    public void backHome(View view){


    }

    public void displayBookName(View view){


    }


    public void displayBookDetail(View view){


    }

    public void displayBookImage(View view){

    }
    */
}
