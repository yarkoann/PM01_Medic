package com.example.medic;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class onBoardOneFragment extends Fragment {

    private ImageView mImageView;
    private int mImageId;


    public onBoardOneFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static onBoardOneFragment newInstance(int imageId) {
        onBoardOneFragment fragment = new onBoardOneFragment();
        Bundle args = new Bundle();
        args.putInt("image_id", imageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageId = getArguments().getInt("image_id");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_board_one, container, false);
        mImageView = view.findViewById(R.id.imageFrag1);
        mImageView.setImageResource(mImageId);
        return view;
    }
}