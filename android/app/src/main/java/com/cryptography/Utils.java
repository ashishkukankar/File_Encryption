package com.cryptography;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;

import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static String  readCryptoFile(Context mcontext){
        byte[] plaintext = new byte[0];
    try {
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);

        Context context = mcontext;
        String fileToRead = "my_sensitive_data.txt";
        EncryptedFile encryptedFile = new EncryptedFile.Builder(
                new File(context.getFilesDir(), fileToRead),
                context,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build();

        InputStream inputStream = encryptedFile.openFileInput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextByte = inputStream.read();
        while (nextByte != -1) {
            byteArrayOutputStream.write(nextByte);
            nextByte = inputStream.read();
        }
         plaintext = byteArrayOutputStream.toByteArray();
    }catch (Exception e){
        e.printStackTrace();
    }
        return plaintext.toString();
    }

    public static void  writeCryptoFile(Context context,String text){
        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);

            // Creates a file with this name, or replaces an existing file
            // that has the same name. Note that the file name cannot contain
            // path separators.
            String fileToWrite = "my_sensitive_data.txt";
            EncryptedFile encryptedFile = new EncryptedFile.Builder(
                    new File(context.getFilesDir(), fileToWrite),
                    context,
                    masterKeyAlias,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();

            byte[] fileContent = text
                    .getBytes(StandardCharsets.UTF_8);
            OutputStream outputStream = encryptedFile.openFileOutput();
            outputStream.write(fileContent);
            outputStream.flush();
            outputStream.close();
        }catch (Exception io){
            io.printStackTrace();
        }
    }
}
