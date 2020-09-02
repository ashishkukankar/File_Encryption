package com.cryptography;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CryptoForFile extends ReactContextBaseJavaModule {

    public CryptoForFile(ReactApplicationContext reactContext) {
        super(reactContext);

    }
    @NonNull
    @Override
    public String getName() {
        return "crypography";
    }

    @ReactMethod
    public String readEncryptedFile(){
        String plaintext =  Utils.readCryptoFile(getReactApplicationContext());
        return plaintext;
    }

    @ReactMethod
    public void writeEncryptedFile(String text){
        Utils.writeCryptoFile(getReactApplicationContext(),text);
    }
}
