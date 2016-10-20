package com.ivaylok.challenge;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private FileAdapter mAdapter;
    private List<FileModel> fileModels;
    private FileController controller;

    private DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        controller = FileController.get(getBaseContext());
        fileModels = controller.getFiles();

        mAdapter = new FileAdapter(fileModels);
        mRecyclerView.setAdapter(mAdapter);
        mDatabase = DatabaseHelper.getInstance(getApplicationContext());

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), mRecyclerView , new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.dialog);
                        dialog.setTitle("Actions");
                        dialog.show();

                        TextView mFavorite = (TextView) dialog.findViewById(R.id.tvFavorite);
                        TextView mPermalink = (TextView) dialog.findViewById(R.id.tvPermalink);
                        TextView mDelete = (TextView) dialog.findViewById(R.id.tvDelete);

                        mFavorite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("TAG", "You just clicked on Favorite");
                                dialog.dismiss();
                            }
                        });

                        mPermalink.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("TAG", "You just clicked on Permalink");
                                dialog.dismiss();
                            }
                        });

                        mDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("TAG", "You just clicked on Delete");
                                dialog.dismiss();
                            }
                        });

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fileModels = controller.getFiles();
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add) {
            startActivity(new Intent(this, AddActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
