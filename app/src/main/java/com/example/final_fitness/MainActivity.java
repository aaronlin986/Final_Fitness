package com.example.final_fitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button edit;
    TextView name;
    TextView height;
    TextView weight;
    ImageView img;
    ImageView wo;
    ImageView time;
    static String datas[] = new String[3];
    Uri imageUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_REQUEST_CODE = 2;

    private void dispatchTakePictureIntent(){
        File photoFile = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (photoFile.exists()) {
                photoFile.delete();
            }
            photoFile.createNewFile();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(MainActivity.this,"com.example.final_fitness.fileprovider", photoFile);
        }
        else{
            imageUri = Uri.fromFile(photoFile);
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    private void pickFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void selectImage(){
        final String items[] = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select Image From");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item){
                if(items[item].equals("Take Photo")){
                    dispatchTakePictureIntent();
                }
                else if(items[item].equals("Choose from Gallery")){
                    pickFromGallery();
                }
                else if(items[item].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.profile);
        name = (TextView) findViewById(R.id.nameProfile);
        height = (TextView) findViewById(R.id.heightProfile);
        weight = (TextView) findViewById(R.id.weightProfile);
        edit = (Button) findViewById(R.id.update);
        wo = (ImageView) findViewById(R.id.workouts);
        time = (ImageView) findViewById(R.id.timer);

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    selectImage();
                }
                return true;
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), editInfo.class);
                startActivityForResult(intent, 3);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), countDownTimer.class);
                startActivity(intent);
            }
        });

        wo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), myList.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 3:
                if(resultCode == Activity.RESULT_OK){
                    name.setText(datas[0]);
                    height.setText(datas[1]);
                    weight.setText(datas[2]);
                    break;
                }
            case REQUEST_IMAGE_CAPTURE:
                if(resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        img.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case GALLERY_REQUEST_CODE:
                Uri selectedImage = data.getData();

                if(resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                        img.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
