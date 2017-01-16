package ru.itis.marvel_encyclopedia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;

/**
 * Created by UseR7 on 15.01.2017.
 */

public class InfoFragment extends Fragment {
    private TextView tv_name;
    private TextView tv_desc;
    private ImageView iv_image;

    public static InfoFragment newInstance(Result character) {

        Bundle args = new Bundle();
        args.putSerializable("char", character);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.activity_info, container, false);
        final Result character = (Result) getArguments().getSerializable("char");

        tv_name = (TextView) v.findViewById(R.id.name);
        tv_desc = (TextView) v.findViewById(R.id.description);
        iv_image = (ImageView) v.findViewById(R.id.image);

        tv_name.setText(character.getName());
        tv_desc.setText(character.getDescription());
        Glide.with(getContext()).load(character.getThumbnail().getPath()+"/standard_fantastic."+character.getThumbnail().getExtension()).fitCenter().into(iv_image);


        return v;
    }
}
