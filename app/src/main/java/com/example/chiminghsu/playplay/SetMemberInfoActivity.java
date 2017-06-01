package com.example.chiminghsu.playplay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;

import static android.view.KeyEvent.KEYCODE_ENTER;

public class SetMemberInfoActivity extends AppCompatActivity {
    private static final int REQUEST_PICK_PICTURE = 3;
    private static final int REQUEST_TAKE_PICTURE_LARGE = 1;
    private static final int GET_CROP_PICTURE=2;
    private File picFile;
    private Toolbar toolbar;
    private MyCircleImageView myCircleImageView;

    private ImageView iw_edit_pic;
    private ImageView iw_edit_user_id;
    private ImageView iw_edit_user_height;
    private ImageView iw_edit_user_weight;
    private ImageView iw_edit_user_age;
    private ImageView iw_clear_user_id;
    private ImageView iw_clear_user_height;
    private ImageView iw_clear_user_weight;
    private ImageView iw_clear_user_age;
    private EditText et_user_id;
    private EditText et_user_height;
    private EditText et_user_weight;
    private EditText et_user_age;
    private TextView textView4;
    private TextView textView6;
    private TextView textView9;
    private TextView textView14;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_member_info);
        initStatustBar();
        getViews();
        initToolBar();
        initMyCircleImageView();
        initEditBigHead();
        setViewListener(iw_edit_user_id, et_user_id,iw_clear_user_id,textView4);
        setViewListener(iw_edit_user_height,et_user_height,iw_clear_user_height,textView6);
        setViewListener(iw_edit_user_weight,et_user_weight,iw_clear_user_weight,textView9);
        setViewListener(iw_edit_user_age,et_user_age,iw_clear_user_age,textView14);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","onStop");
        /* TODO:save data to data base*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap;

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);  //這是裁切的照片大小
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case REQUEST_TAKE_PICTURE_LARGE:{
//                   bitmap = BitmapFactory.decodeFile(picFile.getPath());
//                    myCircleImageView.setImageBitmap(bitmap);

                    intent.setDataAndType(Uri.fromFile(picFile), "image/*");
                    startActivityForResult(intent, GET_CROP_PICTURE);
                    break;
                }

                case GET_CROP_PICTURE:{
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        myCircleImageView.setImageBitmap(photo);
                    }
                    break;
                }

                case REQUEST_PICK_PICTURE:{
                    Uri uri = data.getData();
//                    myCircleImageView.setImageURI(uri);
                    intent.setDataAndType(uri, "image/*");
                    startActivityForResult(intent, GET_CROP_PICTURE);
                    break;
                }

                default:{}
            }
        }


    }

    private void getViews() {
        iw_edit_pic = (ImageView) findViewById(R.id.iw_edit_pic);

        iw_edit_user_id = (ImageView) findViewById(R.id.iw_edit_userid);
        iw_edit_user_height = (ImageView) findViewById(R.id.iw_edit_user_height);
        iw_edit_user_weight = (ImageView) findViewById(R.id.iw_edit_user_weight);
        iw_edit_user_age = (ImageView) findViewById(R.id.iw_edit_user_age);

        et_user_id = (EditText) findViewById(R.id.et_uesr_id);
        et_user_height = (EditText) findViewById(R.id.et_user_height);
        et_user_weight = (EditText) findViewById(R.id.et_user_weight);
        et_user_age = (EditText) findViewById(R.id.et_user_age);

        iw_clear_user_id = (ImageView) findViewById(R.id.iw_clear_user_id);
        iw_clear_user_height = (ImageView) findViewById(R.id.iw_clear_user_height);
        iw_clear_user_weight = (ImageView) findViewById(R.id.iw_clear_user_weight);
        iw_clear_user_age = (ImageView) findViewById(R.id.iw_clear_user_age);

        textView4 = (TextView) findViewById(R.id.textView4);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView14 = (TextView) findViewById(R.id.textView14);

    }

    private void initEditBigHead() {
        iw_edit_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                popupMenu.inflate(R.menu.menu_edit_bighead);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.munu_item_camera:{
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                picFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"bigHead.jpg");
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picFile));
                                startActivityForResult(intent,REQUEST_TAKE_PICTURE_LARGE);
                                return true;
                            }
                            case R.id.menu_item_album:{
                                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent,REQUEST_PICK_PICTURE);
                                return true;
                            }
                            default:
                                return false;
                        }

                    }
                });
                popupMenu.show();
            }
        });
    }

    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.tb_set_memberinfo);
        toolbar.setNavigationIcon(R.drawable.ic_action_cancle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                alertDialogFragment.show(getSupportFragmentManager(),"AlertDialogFragment");
            }
        });
    }

    private void initMyCircleImageView(){
        myCircleImageView = (MyCircleImageView) findViewById(R.id.iw_circle);
    }

    public void setViewListener(final ImageView iw_edit, final EditText et, final ImageView iw_clear, final TextView showInfo){
        iw_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iw_edit.setVisibility(View.INVISIBLE);
                showInfo.setVisibility(View.INVISIBLE);
                iw_clear.setVisibility(View.VISIBLE);
                et.setVisibility(View.VISIBLE);
                et.setFocusable(true);
            }
        });

        iw_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
                et.setVisibility(View.INVISIBLE);
                iw_clear.setVisibility(View.INVISIBLE);
                iw_edit.setVisibility(View.VISIBLE);
                showInfo.setVisibility(View.VISIBLE);
            }
        });

        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KEYCODE_ENTER:{
                        showInfo.setText(et.getText());
                        et.setVisibility(View.INVISIBLE);
                        iw_clear.setVisibility(View.INVISIBLE);
                        iw_edit.setVisibility(View.VISIBLE);
                        showInfo.setVisibility(View.VISIBLE);
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });

    }


    public static class AlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(this.getContext())
                        .setTitle(R.string.alert_title)
                        .setMessage(R.string.alert_message)
                        .setPositiveButton(R.string.alert_leave,this)
                        .setNegativeButton(R.string.alert_cancle,this)
                        .create();
        }
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which){
                case DialogInterface.BUTTON_POSITIVE:{
                    getActivity().finish();
                    break;
                }
                case DialogInterface.BUTTON_NEGATIVE:{
                    dialog.cancel();
                    break;
                }
                default:
                    break;
            }
        }
    }

    private void initStatustBar() {
        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#FFCD6D"));
//        // set a custom navigation bar resource
//        tintManager.setNavigationBarTintResource(R.drawable.my_tint);
//        // set a custom status bar drawable
//        tintManager.setStatusBarTintDrawable(MyDrawable);

    }





}
