package com.ivaylok.challenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = AddActivity.class.getSimpleName();
    private TextView mName, mFolder;
    private Switch mOrange, mBlue;
    private Button mAddFile;

    private FileModel mModel;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mModel = new FileModel();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.addCoordinator);

        mName = (TextView) findViewById(R.id.tvAddName);
        mFolder = (TextView) findViewById(R.id.tvAddFolder);
        mOrange = (Switch) findViewById(R.id.swOrange);
        mBlue = (Switch) findViewById(R.id.swBlue);
        mAddFile = (Button) findViewById(R.id.btnAddFile);

        mAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mModel.getFileType() == null)
                    mModel.setFileType("image");
                mModel.setFilename(mName.getText().toString());
                mModel.setIsFolder(mFolder.getText().toString());
                FileController.get(AddActivity.this).addFilesToDatabase(mModel);
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        setSwitches();
        setDialog();
    }

    private void setDialog() {

        List<FileModel> modelList = DatabaseHelper.getInstance(AddActivity.this).getAllData(DatabaseHelper.SELECT_ALL_FOLDERS_QUERY);
        final List<String> folderList = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++) {
            folderList.add(modelList.get(i).getFilename());
        }
        final CharSequence[] folderNames = folderList.toArray(new String[folderList.size()]);

        mFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(AddActivity.this)
                        .setTitle(getString(R.string.app_name))
                        .setSingleChoiceItems(folderNames, 0, null)
                        .setPositiveButton(R.string.txt_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                mFolder.setText(folderList.get(selectedPosition));

                            }
                        })
                        .setNegativeButton(R.string.txt_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                            }
                        })
                        .show();
            }
        });
    }

    private void setSwitches() {
        mOrange.setChecked(false);
        mOrange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    Log.d(TAG,"Orange is on!");
                    mOrange.setChecked(true);
                    mModel.setOrange(true);
                } else {
                    Log.d(TAG,"Orange is off!");
                    mOrange.setChecked(false);
                    mModel.setOrange(false);
                }
            }
        });

        mBlue.setChecked(false);
        mBlue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    Log.d(TAG,"Blue is on!");
                    mBlue.setChecked(true);
                    mModel.setBlue(true);
                } else {
                    Log.d(TAG,"Blue is off!");
                    mBlue.setChecked(false);
                    mModel.setBlue(false);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbAddImage:
                if (checked)
                    mModel.setFileType("image");
                    break;
            case R.id.rbAddMovie:
                if (checked)
                    mModel.setFileType("movie");
                    break;
            case R.id.rbAddMusic:
                if(checked)
                    mModel.setFileType("music");
                    break;
            case R.id.rbFolder:
                if(checked)
                    mModel.setFileType("folder");
                    break;
            default:
                break;
        }
    }

}
