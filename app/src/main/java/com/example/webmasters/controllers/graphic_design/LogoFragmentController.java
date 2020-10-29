package com.example.webmasters.controllers.graphic_design;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.webmasters.R;
import com.example.webmasters.models.graphic_design.view_models.LogoViewModel;
import com.example.webmasters.types.TextChangedListener;
import com.example.webmasters.ui.graphic_design.logos.LogoView;

public class LogoFragmentController {
    private Fragment mFragment;

    /**
     * Constructor
     * @param fragment (Fragment)
     * @param view (Fragment)
     */
    public LogoFragmentController(Fragment fragment, View view) {
        mFragment = fragment;
        LogoViewModel model = new ViewModelProvider(mFragment).get(LogoViewModel.class);

        bindLogoText(view.findViewById(R.id.editLogoText), model);
        bindLogoTextSize(view.findViewById(R.id.editLogoTextSize), model);
        bindLogoView(view.findViewById(R.id.logoView), model);
    }

    /**
     * bindLogoView binds logo view to view model.
     * @param logoView (LogoView)
     * @param logoViewModel (LogoViewModel)
     */
    private void bindLogoView(LogoView logoView, LogoViewModel logoViewModel) {
        // Model -> View updates.
        logoViewModel.getTextObservable().observe(mFragment, logoView::setText);
        logoViewModel.getTextSizeObservable().observe(mFragment, logoView::setTextSize);
    }

    /**
     * bindLogoText binds logo text edit view to view model and enables bidirectional updates.
     * @param editLogoText (EditText)
     * @param logoViewModel (LogoViewModel)
     */
    private void bindLogoText(EditText editLogoText, LogoViewModel logoViewModel) {
        // Model -> View update.
        logoViewModel.getTextObservable().observe(mFragment, logoText -> {
            int position = editLogoText.getSelectionEnd();
            editLogoText.setText(logoText);
            editLogoText.setSelection(position);
        });
        // View -> Model update.
        editLogoText.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(String logoText) {
                logoViewModel.setText(logoText);
            }
        });
    }

    /**
     * bindLogoTextSize binds logo text size edit view to view model and enables bidirectional updates.
     * @param editLogoTextSize (EditText)
     * @param logoViewModel (LogoViewModel)
     */
    private void bindLogoTextSize(EditText editLogoTextSize, LogoViewModel logoViewModel) {
        // Model -> View update
        logoViewModel.getTextSizeObservable().observe(mFragment, logoTextSize -> {
            int position = editLogoTextSize.getSelectionEnd();
            editLogoTextSize.setText(logoTextSize.toString());
        });
        // View -> Model update.
        editLogoTextSize.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(String text) {
                try {
                    int textSize = Integer.parseInt(text);
                    logoViewModel.setTextSize(textSize);
                } catch (NumberFormatException ignored) { }
            }
        });
    }
}
