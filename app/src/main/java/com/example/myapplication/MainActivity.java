package com.example.myapplication;

import java.io.File;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;

        public class MainActivity extends Activity {

        File directory;
        final int TYPE_PHOTO = 1;
        final int TYPE_VIDEO = 2;

        final int REQUEST_CODE_PHOTO = 1;
        final int REQUEST_CODE_VIDEO = 2;

        final String TAG = "myLogs";

        ImageView ivPhoto;

@Override
  protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          createDirectory();
          ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
          }

          public void onClickPhoto(View view) {
          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

          startActivityForResult(intent, REQUEST_CODE_PHOTO);
          }

          public void onClickVideo(View view) {
          Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

          startActivityForResult(intent, REQUEST_CODE_VIDEO);
          }

@Override
  protected void onActivityResult(int requestCode, int resultCode,
          Intent intent) {
          if(requestCode == REQUEST_CODE_PHOTO) {
          if(resultCode == RESULT_OK) {
          if(intent == null) {
          Log.d(TAG, "Intent is null");
          } else{
          Log.d(TAG, "Photo uri: "+ intent.getData());
          Bundle bndl = intent.getExtras();
          if(bndl != null) {
          Object obj = intent.getExtras().get("data");
          if(obj instanceof Bitmap) {
          Bitmap bitmap = (Bitmap) obj;
          Log.d(TAG, "bitmap "+ bitmap.getWidth() + " x "
          + bitmap.getHeight());
          ivPhoto.setImageBitmap(bitmap);
          }
          }
          }
          }
          }
}private void createDirectory() {
                directory = new File(
                        Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        "MyFolder");
                if (!directory.exists())
                    directory.mkdirs();
            }
        }
