package com.ivaylok.challenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by smn on 10/19/16.
 */

public class FileAdapter extends RecyclerView.Adapter<FileHolder> {
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