package com.amey.flipgrid.ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.amey.flipgrid.R;
import com.amey.flipgrid.databinding.FragmentSignupBinding;
import com.amey.flipgrid.ui.viewmodel.ProfileViewModel;

public class SignupFragment extends Fragment {
    FragmentSignupBinding fragmentSignupBinding;
    Context context;
    ProfileViewModel profileViewModel;

    public SignupFragment() {
        // Required empty public constructor
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
        init();
        return fragmentSignupBinding.getRoot();
    }

    private void init() {
        Animation animSlideDown = AnimationUtils.loadAnimation(context, R.anim.falling);
        fragmentSignupBinding.singupTextView.startAnimation(animSlideDown);

        String colored = "*";
        String emailTitle = context.getString(R.string.email);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(emailTitle + colored);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.box_heading_title_color)), emailTitle.length(), spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentSignupBinding.emailTextView.setText(spannableStringBuilder);

        String passwordTitle = context.getString(R.string.password);
        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(passwordTitle + colored);
        spannableStringBuilder1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.box_heading_title_color)), passwordTitle.length(), spannableStringBuilder1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentSignupBinding.passwordTextView.setText(spannableStringBuilder1);

        String requiredTitle = context.getString(R.string.required);
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(requiredTitle + colored);
        spannableStringBuilder2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)), requiredTitle.length(), spannableStringBuilder2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentSignupBinding.requiredTextView.setText(spannableStringBuilder2);

        fragmentSignupBinding.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                profileViewModel.set_emailLengthOnConfigChange(fragmentSignupBinding.emailEditText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(fragmentSignupBinding.emailEditText.getText().length() > 0 && profileViewModel.get_emailLengthOnConfigChange() !=0 &&
                fragmentSignupBinding.emailTextInputLayout.isErrorEnabled()){
                    profileViewModel.set_emailLengthOnConfigChange(fragmentSignupBinding.emailEditText.getText().length());
                    fragmentSignupBinding.emailTextInputLayout.setError("");
                    fragmentSignupBinding.emailTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        fragmentSignupBinding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                profileViewModel.set_passwordLengthOnConfigChange(fragmentSignupBinding.passwordEditText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (fragmentSignupBinding.passwordEditText.getText().length() > 0 && profileViewModel.get_passwordLengthOnConfigChange() !=0 &&
                        fragmentSignupBinding.passwordTextInputLayout.isErrorEnabled()) {
                    profileViewModel.set_passwordLengthOnConfigChange(fragmentSignupBinding.passwordEditText.getText().length());
                    fragmentSignupBinding.passwordTextInputLayout.setError("");
                    fragmentSignupBinding.passwordTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        fragmentSignupBinding.websiteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                profileViewModel.set_websiteLengthOnConfigChange(fragmentSignupBinding.websiteEditText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (fragmentSignupBinding.websiteEditText.getText().length() > 0 && profileViewModel.get_websiteLengthOnConfigChange() !=0 &&
                        fragmentSignupBinding.webTextInputLayout.isErrorEnabled()) {
                    profileViewModel.set_websiteLengthOnConfigChange(fragmentSignupBinding.websiteEditText.getText().length());
                    fragmentSignupBinding.webTextInputLayout.setError("");
                    fragmentSignupBinding.webTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        fragmentSignupBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                profileViewModel.set_userName(fragmentSignupBinding.firstNameEditText.getText().toString());
                String emailErrorMsg = (fragmentSignupBinding.emailEditText.getText().toString().isEmpty()) ? context.getString(R.string.email_error) : context.getString(R.string.invalid_email_error);
                if (!fragmentSignupBinding.emailEditText.getText().toString().isEmpty() && profileViewModel.isValidEmail(fragmentSignupBinding.emailEditText.getText().toString())) {
                    profileViewModel.set_userEmail(fragmentSignupBinding.emailEditText.getText().toString());
                } else {
                    fragmentSignupBinding.emailTextInputLayout.setError(emailErrorMsg);
                    fragmentSignupBinding.emailTextInputLayout.setErrorEnabled(true);
                    Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                    fragmentSignupBinding.emailEditText.startAnimation(shake);
                    return;
                }

                if (!fragmentSignupBinding.passwordEditText.getText().toString().isEmpty()) {
                    profileViewModel.set_userPassword(fragmentSignupBinding.passwordEditText.getText().toString());
                } else {
                    fragmentSignupBinding.passwordTextInputLayout.setError(context.getString(R.string.password_error));
                    fragmentSignupBinding.passwordTextInputLayout.setErrorEnabled(true);
                    Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                    fragmentSignupBinding.passwordEditText.startAnimation(shake);
                    return;
                }

                String websiteAddress = fragmentSignupBinding.websiteEditText.getText().toString();
                if (!websiteAddress.isEmpty()) {
                    boolean isValid = profileViewModel.isValidWebAddress(websiteAddress);
                    if (isValid) {
                        profileViewModel.set_userWebsite(websiteAddress);
                    } else {
                        fragmentSignupBinding.webTextInputLayout.setError(context.getString(R.string.invalid_web));
                        fragmentSignupBinding.webTextInputLayout.setErrorEnabled(true);
                        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                        fragmentSignupBinding.websiteEditText.startAnimation(shake);
                        return;
                    }
                } else {
                    profileViewModel.set_userWebsite(websiteAddress);
                }


                ((MainActivity)getActivity()).navController.navigate(R.id.action_signupFragment_to_confirmationFragment);

                // clear all views
                fragmentSignupBinding.firstNameEditText.setText("");
                fragmentSignupBinding.emailEditText.setText("");
                fragmentSignupBinding.passwordEditText.setText("");
                fragmentSignupBinding.websiteEditText.setText("");
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        profileViewModel.set_emailLengthOnConfigChange(0);
        profileViewModel.set_passwordLengthOnConfigChange(0);
        profileViewModel.set_websiteLengthOnConfigChange(0);
    }
}
