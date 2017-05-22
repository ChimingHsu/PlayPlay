package com.example.chiminghsu.playplay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.KeyEvent.KEYCODE_ENTER;

public class SetMemberInfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    MyCircleImageView myCircleImageView;
    ImageView iw_edit_pic;
    ImageView iw_edit_user_id;
    ImageView iw_edit_user_height;
    ImageView iw_edit_user_weight;
    ImageView iw_edit_user_age;
    ImageView iw_clear_user_id;
    ImageView iw_clear_user_height;
    ImageView iw_clear_user_weight;
    ImageView iw_clear_user_age;
    EditText et_user_id;
    EditText et_user_height;
    EditText et_user_weight;
    EditText et_user_age;
    TextView textView4;
    TextView textView6;
    TextView textView9;
    TextView textView14;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_member_info);
        getViews();
        initToolBar();
        initMyCircleImageView();
        initChangePic();
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

    private void initChangePic() {
        iw_edit_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO FOR  UPLOAD PIC*/
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

}
