package com.mezez.betastudent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class NewPastQuestionActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    EditText xtitle, xdescription;
    Button imageButton, cancel, save;
    ImageView mImageView;
    String mCurrentPhotoPath, ximagePath, ximageName, imagePath, imageName, title, description;
    String [] imageDetails = new String[2];
    DBHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbHelper = new DBHelper(this);
        
        setContentView(R.layout.activity_new_past_question);
        getSupportActionBar().setTitle("New Past Question");
        xtitle = (EditText) findViewById(R.id.past_question_title);
        xdescription = (EditText) findViewById(R.id.past_question_description);
        mImageView = (ImageView) findViewById(R.id.past_question_image_view);
        imageButton = (Button) findViewById(R.id.past_question_image_button);
        cancel = (Button) findViewById(R.id.past_question_cancel);
        save = (Button) findViewById(R.id.past_question_save);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();


            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = xtitle.getText().toString();
                description = xdescription.getText().toString();
                imageName = getImageName();
                imagePath = getImagePath();


                if(xtitle.length() != 0 && xdescription.length() != 0){

                    AddData(title, description, imageName, imagePath);

                    //after adding the data, set an alarm or reminder for the submission day
                    //remember to check later for if the time and date selected has already past

                    xtitle.setText("");
                    xdescription.setText("");
                }else {
                    toastMessage("You must have a title and a description before you save");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PastQuestionsActivity.class);
                finish();
                startActivity(i);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                toastMessage("Error occurred while creating image file");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,"com.mezez.betastudent",photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

//                galleryAddPic();
//                //setPic();
//            }else {
//                toastMessage("Help");
//            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            Uri imageUri = data.getData();

            ximagePath = getRealPathFromURI(this, imageUri);

            File file = new File(ximagePath);
            ximageName = file.getName();
            imageDetails[0] = ximageName;
            imageDetails[1]= ximagePath;
            setImageDetails(imageDetails);

            //save the filename and path to past questions db together with other details
            // so you can use the filename and path to retrieve as well as edit and delete


        }
    }



    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void AddData(String title, String description, String imageName, String imagePath){
        //recall that the add data method in the helper returns true or false
        boolean insertData = mDbHelper.addPastQuestion(title, description, imageName, imagePath);
        if(insertData){
            toastMessage("Past Question saved successfully");
        }else {
            toastMessage("Error saving past question");
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    public void setImageDetails(String[] imageDetails) {
        this.imageDetails = imageDetails;
    }

    public String getImageName() {
        return imageDetails[0];
    }

    public String getImagePath() {
        return imageDetails[1];
    }
}
