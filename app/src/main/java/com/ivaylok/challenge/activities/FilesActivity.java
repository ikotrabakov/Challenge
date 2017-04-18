package com.ivaylok.challenge.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ivaylok.challenge.AddActivity;
import com.ivaylok.challenge.DatabaseHelper;
import com.ivaylok.challenge.FileAdapter;
import com.ivaylok.challenge.FileController;
import com.ivaylok.challenge.File;
import com.ivaylok.challenge.R;
import com.ivaylok.challenge.RecyclerItemClickListener;
import com.ivaylok.challenge.repositories.impl.DatabaseFilesRepository;

import java.util.List;

public class FilesActivity extends AppCompatActivity implements FilesActivityView{

    private static final String TAG = FilesActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private FileAdapter mAdapter;
    private List<File> files;
    private FileController controller;
    private FilesActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new FilesActivityPresenter(this, new DatabaseFilesRepository(getApplicationContext()));
        presenter.loadFiles();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        controller = FileController.get(getBaseContext());

        files = controller.selectAllWhere(DatabaseHelper.FOLDER, "=", "");

        mAdapter = new FileAdapter(files);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), mRecyclerView , new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        if("folder".equals(files.get(position).getFileTypeAsString())) {

                            NetworkAsyncTask asyncTask = new NetworkAsyncTask();
                            asyncTask.execute(position);//

                        } else {
                            onClickOptionDialog();
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                }));
    }

    @Override
    public void displayFiles(List<File> files) {

    }

    @Override
    public void displayNoFiles() {

    }


    private class NetworkAsyncTask extends AsyncTask<Integer, Void,Void> {

        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Integer... params) {
            int position = params[0];
            files = controller.selectAllWhere(DatabaseHelper.FOLDER, "=", files.get(position).getFilename());
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(FilesActivity.this, "Network request", "Wait for data to load");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            mAdapter = new FileAdapter(files);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void onClickOptionDialog() {

        final Dialog dialog = new Dialog(FilesActivity.this);
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
    protected void onResume() {
        super.onResume();
        mAdapter = new FileAdapter(files);
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
