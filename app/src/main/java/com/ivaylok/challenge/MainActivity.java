package com.ivaylok.challenge;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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
    }

    private class FileHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mTitle;
        private TextView mDesc;
        private FileModel mFile;

        private ImageView mTopColor;
        private ImageView mBottomColor;

        public FileHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.ivIcon);
            mTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mDesc = (TextView) itemView.findViewById(R.id.tvDesc);
            mTopColor = (ImageView) itemView.findViewById(R.id.topColor);
            mBottomColor = (ImageView) itemView.findViewById(R.id.bottomColor);

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

        public void bindFile(FileModel fileModel)
        {
             mFile = fileModel;
            if(mFile == null)
            {
                Log.d("TAG","Got a null file object");
            } else {

                mTitle.setText(mFile.getFilename());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                mDesc.setText(String.valueOf("modivied " + simpleDateFormat.format(mFile.getModDate())));

                if (mFile.isBlue() && mFile.isOrange()) {
                    mTopColor.setColorFilter(Color.BLUE);
                    mBottomColor.setColorFilter(Color.YELLOW);
                } else if (mFile.isBlue()) {
                    mTopColor.setColorFilter(Color.BLUE);
                    mBottomColor.setColorFilter(Color.BLUE);
                }
                else {
                    mTopColor.setColorFilter(Color.YELLOW);
                    mBottomColor.setColorFilter(Color.YELLOW);
                }

                switch (mFile.getFileType()) {
                    case image: mImage.setImageResource(R.drawable.ic_photo_black_48dp);
                        break;
                    case pdf: mImage.setImageResource(R.drawable.ic_picture_as_pdf_black_48dp);
                        break;
                    case movie: mImage.setImageResource(R.drawable.ic_local_movies_black_48dp);
                        break;
                    case music: mImage.setImageResource(R.drawable.ic_music_note_black_48dp);
                        break;
                    default:
                        mImage.setImageResource(R.mipmap.ic_launcher);
                        break;
                }
            }
        }
    }

    private class FileAdapter extends RecyclerView.Adapter<FileHolder>
    {
        private List<FileModel> modelList;

        public FileAdapter (List<FileModel> fileModels)
        {
            modelList = fileModels;
        }

        public FileHolder onCreateViewHolder( ViewGroup parent,
                                              int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater
                    .inflate(R.layout.list_item_file, parent,
                            false);
            return new FileHolder(view);
        }

        public void onBindViewHolder(FileHolder holder ,int position)
        {
            FileModel fileModel = modelList.get(position);
            holder.bindFile(fileModel);
        }

        @Override
        public int getItemCount()
        {
            return modelList.size();
        }

    }


}
