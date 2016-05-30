package com.example.steven.tamtam.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.steven.tamtam.Models.User;
import com.example.steven.tamtam.NoSwipeViewPager;
import com.example.steven.tamtam.R;
import com.example.steven.tamtam.RegisterActivity;
import com.example.steven.tamtam.apimanager.UserApiManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by steven on 5/28/16.
 */
public class RegisterImageFragment extends Fragment {
    String[] perms = {"android.permission.READ_EXTERNAL_STORAGE"};

    User user;
    Button btnPrev;
    Button btnNext;
    Button btnTakePhoto;
    View inflatedView;
    NoSwipeViewPager pager;
    ImageView imageView;
    private Uri fileUri;

    int permsRequestCode = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((RegisterActivity) getActivity()).getUser();
        inflatedView = inflater.inflate(R.layout.fragment_register_image, container, false);
        pager = ((RegisterActivity) getActivity()).getmViewPager();
        btnPrev = (Button) inflatedView.findViewById(R.id.btnPrevious);
        btnNext = (Button) inflatedView.findViewById(R.id.btnNext);
        btnTakePhoto = (Button) inflatedView.findViewById(R.id.btnStartCamera);
        imageView = (ImageView) inflatedView.findViewById(R.id.image);

        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
        btnTakePhoto.setOnClickListener(btnTakePhotoListener);

        return inflatedView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                user.setImageUrl(fileUri);
                loadImageFromUri(imageView, fileUri);
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("image", "canceled");
            } else {
                Log.d("image", "failed");
            }
        }
    }


    private void startCamera() {
        requestPermissions(perms, permsRequestCode);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    /** Create a file Uri for saving an image or video */
    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "DCIM/Camera");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    private void loadImageFromUri(ImageView view, Uri uri) {
        if (uri != null) {
            File filePath = new File(uri.getPath());
            view.setImageDrawable(Drawable.createFromPath(filePath.toString()));
        }
    }

    View.OnClickListener btnPrevListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }
    };

    View.OnClickListener btnNextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(UserApiManager.register(user)) {
                getActivity().finish();
            }
        }
    };

    View.OnClickListener btnTakePhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startCamera();
        }
    };
}