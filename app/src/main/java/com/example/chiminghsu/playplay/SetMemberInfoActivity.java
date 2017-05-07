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
import android.view.View;

public class SetMemberInfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    MyCircleImageView myCircleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_member_info);
        initToolBar();
        initMyCircleImageView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","onStop");
        /*
        * 這裡預計用來把資料存入db
        * */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","onDestroy");
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


}
