package com.amey.flipgrid.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.amey.flipgrid.R;
import com.amey.flipgrid.databinding.FragmentSignupBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentSignupBinding fragmentSignupBinding;
    View view;
    Context context;
    AppCompatTextView singupTextView;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragmentSignupBinding = FragmentSignupBinding.inflate(inflater);
         fragmentSignupBinding.emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @RequiresApi(api = Build.VERSION_CODES.M)
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                 /*if(hasFocus) {
                     fragmentSignupBinding.emailTextInputLayout.setEndIconTintList(ColorStateList.valueOf(context.getColor(R.color.box_selected_bg_color)));
                     fragmentSignupBinding.emailTextInputLayout.setBoxStrokeColorStateList(ColorStateList.valueOf(context.getColor(R.color.box_selected_bg_color)));
                 }else{
                     fragmentSignupBinding.emailTextInputLayout.setEndIconTintList(ColorStateList.valueOf(context.getColor(R.color.box_normal_bg_color)));
                     fragmentSignupBinding.emailTextInputLayout.setBoxStrokeColorStateList(ColorStateList.valueOf(context.getColor(R.color.box_normal_bg_color)));
                 }*/

             }
         });
         view = fragmentSignupBinding.getRoot();
         init();
        return view;
    }

    private void init() {
        singupTextView = fragmentSignupBinding.singupTextView;
        singupTextView.setTypeface(null, Typeface.BOLD);
        Animation animSlideDown = AnimationUtils.loadAnimation(context,R.anim.slide_down);
        singupTextView.startAnimation(animSlideDown);
    }

}
