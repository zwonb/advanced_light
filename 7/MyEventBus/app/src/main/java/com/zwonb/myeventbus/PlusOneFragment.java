package com.zwonb.myeventbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PlusOneFragment extends Fragment {

    public PlusOneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        EventBus.getDefault().post(new MessageEvent("helllo"));

        return view;
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void getDate(MessageEvent messageEvent){
        Log.e("binbin", "getDate: " + messageEvent.getMessage());
    }



    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }


}
