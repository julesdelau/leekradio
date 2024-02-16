/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaceUser;

/**
 *
 * @author Jules_D
 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class encrypteur {
    public encrypteur(){}
        public String securiserFacilement(String password) {
        MessageDigest digest;
        byte[] encodedhash = new byte[512];
        try {
            digest = MessageDigest.getInstance("SHA-512");
            encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {

        }
        return bytesToHex(encodedhash);

    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
            
        }
        return hexString.toString();
    }
}
