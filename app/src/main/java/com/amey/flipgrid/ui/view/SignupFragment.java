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
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.amey.flipgrid.R;
import com.amey.flipgrid.databinding.FragmentSignupBinding;
import com.amey.flipgrid.ui.viewmodel.ProfileViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    AppCompatTextView singupTextView, emailTextView, passwordTextView, requiredTextView;
    ProfileViewModel profileViewModel;
    AppCompatButton submitButton;
    TextInputEditText emailEditText, passwordEditText, firstNameEditText, websiteEditText;
    TextInputLayout emailTextInputLayout, passwordTextInputLayout;

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
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragmentSignupBinding = FragmentSignupBinding.inflate(inflater);
         view = fragmentSignupBinding.getRoot();
         init();
        return view;
    }

    private void init() {
        singupTextView = fragmentSignupBinding.singupTextView;
        singupTextView.setTypeface(null, Typeface.BOLD);
        Animation animSlideDown = AnimationUtils.loadAnimation(context,R.anim.slide_down);
        singupTextView.startAnimation(animSlideDown);

        String colored = "*";
        emailTextView = fragmentSignupBinding.emailTextView;
        String emailTitle = context.getString(R.string.email);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(emailTitle + colored);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.box_heading_title_color)),emailTitle.length(), spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        emailTextView.setText(spannableStringBuilder);

        passwordTextView = fragmentSignupBinding.passwordTextView;
        String passwordTitle = context.getString(R.string.password);
        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(passwordTitle + colored);
        spannableStringBuilder1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.box_heading_title_color)),passwordTitle.length(), spannableStringBuilder1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        passwordTextView.setText(spannableStringBuilder1);

        requiredTextView = fragmentSignupBinding.requiredTextView;
        String requiredTitle = context.getString(R.string.required);
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(requiredTitle + colored);
        spannableStringBuilder2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.colorPrimary)),requiredTitle.length(), spannableStringBuilder2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        requiredTextView.setText(spannableStringBuilder2);

        emailTextInputLayout = fragmentSignupBinding.emailTextInputLayout;
        emailEditText = fragmentSignupBinding.emailEditText;
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    emailTextInputLayout.setError(context.getString(R.string.email_error));
                }else{
                    if(before == 0 && count ==1 && emailTextInputLayout.isErrorEnabled()){
                        emailTextInputLayout.setError("");
                        emailTextInputLayout.setErrorEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordTextInputLayout = fragmentSignupBinding.passwordTextInputLayout;
        passwordEditText = fragmentSignupBinding.passwordEditText;
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    passwordTextInputLayout.setError(context.getString(R.string.password_error));
                }else{
                    if(before == 0 && count ==1 && passwordTextInputLayout.isErrorEnabled()){
                        passwordTextInputLayout.setError("");
                        passwordTextInputLayout.setErrorEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        websiteEditText = fragmentSignupBinding.websiteEditText;
        websiteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count ==0 && fragmentSignupBinding.webTextInputLayout.isErrorEnabled()){
                    fragmentSignupBinding.webTextInputLayout.setError("");
                    fragmentSignupBinding.webTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        websiteEditText = fragmentSignupBinding.websiteEditText;

        submitButton = fragmentSignupBinding.submitButton;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                profileViewModel.set_userName(fragmentSignupBinding.firstNameEditText.getText().toString());
                String emailErrorMsg = (emailEditText.getText().toString().isEmpty()) ?  context.getString(R.string.email_error): context.getString(R.string.invalid_email_error);
                if(!emailEditText.getText().toString().isEmpty() && profileViewModel.isValidEmail(emailEditText.getText().toString())){
                    profileViewModel.set_userEmail(emailEditText.getText().toString());
                }else{
                    emailTextInputLayout.setError(emailErrorMsg);
                    Animation shake = AnimationUtils.loadAnimation(context,R.anim.shake);
                    emailEditText.startAnimation(shake);
                    return;
                }

                if(!passwordEditText.getText().toString().isEmpty()){
                    profileViewModel.set_userPassword(passwordEditText.getText().toString());
                }else{
                    passwordTextInputLayout.setError(context.getString(R.string.password_error));
                    Animation shake = AnimationUtils.loadAnimation(context,R.anim.shake);
                    passwordEditText.startAnimation(shake);
                    return;
                }

                String websiteAddress = websiteEditText.getText().toString();
                if(!websiteAddress.isEmpty()){
                   boolean isValid = profileViewModel.isValidWebAddress(websiteAddress);
                   if(isValid){
                       profileViewModel.set_userWebsite(websiteAddress);
                   }else{
                       fragmentSignupBinding.webTextInputLayout.setError(context.getString(R.string.invalid_web));
                       Animation shake = AnimationUtils.loadAnimation(context,R.anim.shake);
                       websiteEditText.startAnimation(shake);
                       return;
                   }
                }else{
                    profileViewModel.set_userWebsite(websiteAddress);
                }

                Navigation.findNavController(v).navigate(R.id.action_signupFragment_to_confirmationFragment);

            }
        });
    }

}
