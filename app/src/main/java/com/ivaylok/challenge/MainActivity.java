package com.ivaylok.challenge;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        FileController controller = FileController.get(getBaseContext());
        List<FileModel> fileModels = controller.getFiles();

        mAdapter = new FileAdapter(fileModels);
        mRecyclerView.setAdapter(mAdapter);

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
}
