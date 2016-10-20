package com.ivaylok.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

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
