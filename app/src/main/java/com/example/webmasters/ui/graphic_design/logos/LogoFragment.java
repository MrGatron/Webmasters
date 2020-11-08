package com.example.webmasters.ui.graphic_design.logos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import com.example.webmasters.databinding.FragmentLogosBinding;
import com.example.webmasters.models.graphic_design.LogoViewModel;

public class LogoFragment extends Fragment {


    private FragmentLogosBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = FragmentLogosBinding.inflate(getLayoutInflater());

        mBinding.logoView.setSwipeListener(new LogoView.SwipeListener() {
            @Override
            public void onSwipeDown() {
                mBinding.scrollControls.setVisibility(View.GONE);
            }

            @Override
            public void onSwipeUp() {
                mBinding.scrollControls.setVisibility(View.VISIBLE);
            }
        });

        LogoView logoView = mBinding.logoView;

        LogoViewModel logoViewModel = new LogoViewModel();
        logoViewModel.setTextValue("Webmasters");
        mBinding.setVariable(BR.logo, logoViewModel);

        mBinding.getRoot().post(() -> {
            int xBoundary = logoView.getWidth();
            int yBoundary = logoView.getHeight();

            mBinding.seekTextX.setMax(xBoundary);
            mBinding.seekTextY.setMax(yBoundary);

            logoViewModel.setTextX(xBoundary / 2);
            logoViewModel.setTextY(yBoundary / 2);

        });

        return mBinding.getRoot();
    }


}