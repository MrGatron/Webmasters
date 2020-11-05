package com.example.webmasters.models.graphic_design;

import android.graphics.Color;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.shared.ObservableLiveData;
import com.example.webmasters.types.ILogo;
import com.example.webmasters.ui.graphic_design.logos.LogoView;

import java.util.Objects;

public class LogoViewModel extends BaseObservable implements ILogo {
    private Logo mLogo = new Logo();

    public void setText(String text) {
        if (mLogo.text.value.equals(text)) return;
        mLogo.setText(text);
        notifyPropertyChanged(BR.text);
    }


    @Bindable
    public String getText() {
        return mLogo.getText();
    }


    public void setTextSize(int textSize) {
        if (mLogo.text.size == textSize) return;
        mLogo.setTextSize(textSize);
        notifyPropertyChanged(BR.textSize);
    }



    @Bindable
    public int getTextSize() {
        return mLogo.getTextSize();
    }


    public void setTextColor(int textColor) {
        if (mLogo.text.color == textColor) return;
        mLogo.setTextColor(textColor);
        notifyPropertyChanged(BR.textColor);
    }



    @Bindable
    public int getTextColor() {
        return mLogo.getTextColor();
    }


    public void setColor(int color) {

    }


    public int getColor() {
        return 0;
    }


    @Override
    public void setSize(int size) {

    }

    public int getSize() {
        return 0;
    }


}