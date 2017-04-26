package com.example.chiminghsu.playplay;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiming Hsu on 2017/4/25.
 */

public class FragmentForViewPager extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String KEY_WORD = "somewords";
    private RecyclerView recyclerView;
    private Spinner select_court;
    private List<PartnerInfoVo> listData;
    private InnerAdapter myAdapter;
    private String[] item;

    public FragmentForViewPager(){
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        item = (String[]) bundle.getCharSequenceArray(KEY_WORD);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager_item_find_partner,container,false);
        listData =new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_show_partner);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        myAdapter = new InnerAdapter(this.getContext(),listData);
        recyclerView.setAdapter(myAdapter);

        select_court = (Spinner) rootView.findViewById(R.id.spinner_near_by_court);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_court.setAdapter(arrayAdapter);
        //select_court.setSelection(0,true);
        select_court.setOnItemSelectedListener(this);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String court = (String) adapterView.getItemAtPosition(i);
        listData = getListData(court);
        myAdapter.setContext(this.getContext());
        myAdapter.setList(listData);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private List<PartnerInfoVo> getListData(String item){
        List<PartnerInfoVo> list = new ArrayList<>();
        String posInfo = "距離"+item+"大約oo公里";
        String id = "暱稱";
        int picId = R.drawable.portrait_36;
        list = new ArrayList<>();
        for(int j = 0 ; j<20 ;j++){
            list.add(new PartnerInfoVo(id+j,posInfo,picId));
        }
        return list;
    }



    private class InnerAdapter extends RecyclerView.Adapter {
        private ImageView iv_big_head;
        private TextView tv_mem_id;
        private TextView tv_pos_info;

        public void setContext(Context context) {
            this.context = context;
        }

        public void setList(List<PartnerInfoVo> list) {
            this.list = list;
        }

        private Context context;
        private List<PartnerInfoVo> list;

        public InnerAdapter(Context context, List<PartnerInfoVo> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.rv_item_near_by_court_partner,parent,false);
            return new InnerViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            InnerViewHolder myViewHolder = (InnerViewHolder) holder;
            if(myViewHolder!=null){
                iv_big_head = myViewHolder.iv_big_head;
                tv_mem_id = myViewHolder.tv_mem_id;
                tv_pos_info = myViewHolder.tv_pos_info;
                iv_big_head.setImageResource(list.get(position).getBigHeadID());
                tv_mem_id.setText(list.get(position).getMemberId());
                tv_pos_info.setText(list.get(position).getPositionInfo());
            }
        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class InnerViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_mem_id;
        private TextView tv_pos_info;
        private ImageView iv_big_head;

        public InnerViewHolder(View itemView) {
            super(itemView);
            if(itemView!=null){
                tv_mem_id = (TextView) itemView.findViewById(R.id.tv_member_id);
                tv_pos_info = (TextView) itemView.findViewById(R.id.tv_info);
                iv_big_head = (ImageView) itemView.findViewById(R.id.iw_member_head);
            }
        }
    }



}

