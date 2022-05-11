package com.amey.flipgrid.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amey.flipgrid.R;
import com.amey.flipgrid.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {
    FragmentSplashBinding fragmentSplashBinding;
    private static int SPLASH_TIME_OUT = 3000;


    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSplashBinding = FragmentSplashBinding.inflate(inflater);
        init();
        return fragmentSplashBinding.getRoot();
    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)getActivity()).navController.navigate(R.id.action_splashFragment_to_signupFragment,null,new NavOptions.Builder().setPopUpTo(R.id.splashFragment,true).build());
            }
        },SPLASH_TIME_OUT);
    }
}