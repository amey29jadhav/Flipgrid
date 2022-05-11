package com.amey.flipgrid.ui.viewmodel;

import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private String _userName;
    private String _userEmail;
    private String _userPassword;
    private String _userWebsite;
    private int _emailLengthOnConfigChange, _passwordLengthOnConfigChange, _websiteLengthOnConfigChange;
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("tag","ProfileViewModel is destroyed");
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_userEmail() {
        return _userEmail;
    }

    public void set_userEmail(String _userEmail) {
        this._userEmail = _userEmail;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userPassword(String _userPassword) {
        this._userPassword = _userPassword;
    }

    public String get_userPassword() {
        return _userPassword;
    }

    public void set_userWebsite(String _userWebsite) {
        this._userWebsite = _userWebsite;
    }

    public String get_userWebsite() {
        return _userWebsite;
    }
    public boolean isValidEmail(String email) {
        boolean isValid;
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidWebAddress(String address){
        return Patterns.WEB_URL.matcher(address).matches();
    }

    public void clearAllProps(){
        _userEmail = "";
        _userName = "";
        _userPassword = "";
        _userWebsite = "";
    }

    public void set_emailLengthOnConfigChange(int _emailLengthOnConfigChange) {
        this._emailLengthOnConfigChange = _emailLengthOnConfigChange;
    }

    public int get_emailLengthOnConfigChange() {
        return _emailLengthOnConfigChange;
    }

    public void set_passwordLengthOnConfigChange(int _passwordLengthOnConfigChange) {
        this._passwordLengthOnConfigChange = _passwordLengthOnConfigChange;
    }

    public int get_passwordLengthOnConfigChange() {
        return _passwordLengthOnConfigChange;
    }

    public void set_websiteLengthOnConfigChange(int _websiteLengthOnConfigChange) {
        this._websiteLengthOnConfigChange = _websiteLengthOnConfigChange;
    }

    public int get_websiteLengthOnConfigChange() {
        return _websiteLengthOnConfigChange;
    }
}
