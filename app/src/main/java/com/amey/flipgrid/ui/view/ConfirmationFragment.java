package com.amey.flipgrid.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amey.flipgrid.R;
import com.amey.flipgrid.databinding.FragmentConfirmationBinding;
import com.amey.flipgrid.ui.viewmodel.ProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentConfirmationBinding fragmentConfirmationBinding;
    Context context;
    AppCompatTextView username, emailConfirmationTextView, userNameConfirmationTextView, websiteConfirmationTextView;
    ProfileViewModel profileViewModel;


    public ConfirmationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmationFragment newInstance(String param1, String param2) {
        ConfirmationFragment fragment = new ConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
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
        //fragmentConfirmationBinding.leftArrowImageView.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        username = fragmentConfirmationBinding.username;

        emailConfirmationTextView = fragmentConfirmationBinding.emailConfirmationTextView;
        emailConfirmationTextView.setText(profileViewModel.get_userEmail());

        userNameConfirmationTextView = fragmentConfirmationBinding.userNameConfirmationTextView;
        if(profileViewModel.get_userName() != null && !profileViewModel.get_userName().isEmpty()){
            Resources res = getResources();
            String text = String.format(res.getString(R.string.username_placeholder), profileViewModel.get_userName());
            username.setText(text);
            userNameConfirmationTextView.setText(profileViewModel.get_userName());
        }

        websiteConfirmationTextView = fragmentConfirmationBinding.websiteConfirmationTextView;
        if(profileViewModel.get_userWebsite() != null && !profileViewModel.get_userWebsite().isEmpty()){
            websiteConfirmationTextView.setText(profileViewModel.get_userWebsite());
        }else{
            websiteConfirmationTextView.setText(context.getString(R.string.default_url));
        }
    }
}