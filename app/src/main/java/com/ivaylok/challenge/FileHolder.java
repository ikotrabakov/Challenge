package com.ivaylok.challenge;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by smn on 10/19/16.
 */

public class FileHolder extends RecyclerView.ViewHolder {

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
