package com.example.mynewsdaily.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewsdaily.R;


/**
 * 左边侧拉界面
 */
public class FragmentLeft extends Fragment {
    private View view;
    private RelativeLayout[] rls=new RelativeLayout[5];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment_left,null);

        rls[0]= (RelativeLayout) view.findViewById(R.id.rl_news);
        rls[1]= (RelativeLayout) view.findViewById(R.id.rl_reading);
        rls[2]= (RelativeLayout) view.findViewById(R.id.rl_local);
        rls[3]= (RelativeLayout) view.findViewById(R.id.rl_comment);
        rls[4]= (RelativeLayout) view.findViewById(R.id.rl_photo);

        for (int i = 0; i < rls.length; i++) {
            rls[i].setOnClickListener(myListener);
        }
        return view;
    }
    private View.OnClickListener myListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            for (int i = 0; i < rls.length; i++) {
                rls[i].setBackgroundColor(0);
            }

            switch (v.getId()){

                case R.id.rl_news:
                    rls[0].setBackgroundColor(0x33c85555);

                    break;
                case R.id.rl_reading:
//                    rls[1].setBackgroundColor(0x33c85555);
//                    ((MainActivity)getActivity()).showFragmentFavorite();

                    Intent intent=new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_local:
                  //  rls[2].setBackgroundColor(0x33c85555);
                    Toast.makeText(getActivity(), "Local", Toast.LENGTH_SHORT).show();
                    break;

                case R.id. rl_comment:
                    rls[3].setBackgroundColor(0x33c85555);
                    Toast.makeText(getActivity(), "Commnet", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_photo:
                    rls[4].setBackgroundColor(0x33c85555);
                    Toast.makeText(getActivity(), "Photo", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
