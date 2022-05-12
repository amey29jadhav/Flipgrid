package com.amey.flipgrid.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.amey.flipgrid.R;
import com.amey.flipgrid.databinding.FragmentConfirmationBinding;
import com.amey.flipgrid.ui.viewmodel.ProfileViewModel;

public class ConfirmationFragment extends Fragment {

    FragmentConfirmationBinding fragmentConfirmationBinding;
    Context context;
    ProfileViewModel profileViewModel;
    private static int TIME_OUT = 2000;



    public ConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                backPressed();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    private void backPressed() {
        profileViewModel.clearAllProps();
        ((MainActivity)getActivity()).navController.navigateUp();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentConfirmationBinding = FragmentConfirmationBinding.inflate(inflater);
        init();
        return fragmentConfirmationBinding.getRoot();
    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentConfirmationBinding.loadingContainer.getRoot().setVisibility(View.GONE);
                fragmentConfirmationBinding.mainView.setVisibility(View.VISIBLE);
            }
        }, TIME_OUT);
        fragmentConfirmationBinding.leftArrowImageView.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary));
        fragmentConfirmationBinding.leftArrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed();
            }
        });

        fragmentConfirmationBinding.emailConfirmationTextView.setText(profileViewModel.get_userEmail());

        if (profileViewModel.get_userName() != null && !profileViewModel.get_userName().isEmpty()) {
            Resources res = getResources();
            String text = String.format(res.getString(R.string.username_placeholder), profileViewModel.get_userName());
            fragmentConfirmationBinding.username.setText(text);
            fragmentConfirmationBinding.userNameConfirmationTextView.setText(profileViewModel.get_userName());
        }else{
            Resources res = getResources();
            String text = String.format(res.getString(R.string.username_placeholder), "User");
            fragmentConfirmationBinding.username.setText(text);
            fragmentConfirmationBinding.userNameConfirmationTextView.setText("User");
        }

        if (profileViewModel.get_userWebsite() != null && !profileViewModel.get_userWebsite().isEmpty()) {
            fragmentConfirmationBinding.websiteConfirmationTextView.setText(profileViewModel.get_userWebsite());
        } else {
            fragmentConfirmationBinding.websiteConfirmationTextView.setText(context.getString(R.string.default_url));
        }

        Animation fallingAnim = AnimationUtils.loadAnimation(context, R.anim.falling);
        fragmentConfirmationBinding.username.startAnimation(fallingAnim);
        fragmentConfirmationBinding.emailConfirmationTextView.startAnimation(fallingAnim);
        fragmentConfirmationBinding.userNameConfirmationTextView.startAnimation(fallingAnim);
        fragmentConfirmationBinding.websiteConfirmationTextView.startAnimation(fallingAnim);
        fragmentConfirmationBinding.successTextView.startAnimation(fallingAnim);
        fragmentConfirmationBinding.backgroundImageView.startAnimation(fallingAnim);
        fragmentConfirmationBinding.successDesc.startAnimation(fallingAnim);
        fragmentConfirmationBinding.submitButton.startAnimation(fallingAnim);

    }
}