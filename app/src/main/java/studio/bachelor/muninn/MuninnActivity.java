package studio.bachelor.muninn;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;

import studio.bachelor.draft.Draft;
import studio.bachelor.draft.DraftDirector;
import studio.bachelor.draft.DraftView;

public class MuninnActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 7211;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muninn);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        findViewById(R.id.select_photo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                swithcToGallery();
            }
        });
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchToSetting();
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DraftDirector.instance.exportToZip();
            }
        });
    }

    private void swithcToGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_photo_string)), SELECT_PICTURE);
    }

    private void switchToSetting() {
        Intent act = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(act);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri uri = data.getData();
                DraftDirector.instance.setBirdviewImageByUri(uri);
            }
        }
    }
}
