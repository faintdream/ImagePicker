package com.akashdubey.demopickphoto;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button launchGallery;
    ImageView image;
    Intent imgIntent=new Intent(Intent.ACTION_GET_CONTENT);
    public static final int PICK_STATUS=1;
    InputStream input;
    Uri selectedImage;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchGallery=(Button) findViewById(R.id.goGallery);
        image=(ImageView) findViewById(R.id.image);
        imgIntent.setType("image/*");

        launchGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(imgIntent.createChooser(imgIntent,"Select Pic.."),PICK_STATUS);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            selectedImage=data.getData();

            try {
                input= getContentResolver().openInputStream(selectedImage);
                bitmap= BitmapFactory.decodeStream(input);
                image.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}
