package uy.edu.ctc.sgapp.logica;

/**
 * Created by alvar on 01/08/2017.
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

public class Seguridad {

    private static Seguridad instancia;

    private Seguridad() {
    }

    public static Seguridad GetInstancia(){
        if (instancia==null)
        {
            instancia   = new Seguridad();

        }

        return instancia;
    }

    public String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }

    public String decrypt(String encryptedData, String initialVectorString, String secretKey) {
        String decryptedData = null;
        try {
            SecretKeySpec skeySpec          = new SecretKeySpec(secretKey.getBytes(), "AES");
            IvParameterSpec initialVector   = new IvParameterSpec(initialVectorString.getBytes());
            Cipher cipher                   = Cipher.getInstance("AES/CFB8/NoPadding");

            cipher.init(Cipher.DECRYPT_MODE, skeySpec, initialVector);


            byte[] encryptedByteArray = Base64.decode(encryptedData.getBytes(), Base64.NO_WRAP);
            byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);

            decryptedData = new String(decryptedByteArray, "UTF8");

        } catch (Exception e) {

            e.printStackTrace();
            //System.err.println("Problem decrypting the data" + e.getMessage());
        }
        return decryptedData;
    }

    public String crypt(String text) throws Exception {

        String initialVectorString      = "a#!?d./*@@^^''_a";
        String secretKey                = "-KeY!!AD#AM!!KeY";

        SecretKeySpec skeySpec          = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec initialVector   = new IvParameterSpec(initialVectorString.getBytes());

        if(text == null || text.length() == 0)
            throw new Exception("Empty string");

        byte[] encrypted = null;

        try {
            Cipher cipher                   = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initialVector);

            encrypted = cipher.doFinal(text.getBytes());

            encrypted = Base64.encode(encrypted, Base64.NO_WRAP);
        } catch (Exception e)
        {
            throw new Exception("[encrypt] " + e.getMessage());
        }

        return new String(encrypted, "UTF8");

//        StringBuffer sb = new StringBuffer();
  //      for(int i=0;i<encrypted.length;i++){
    //        sb.append(Integer.toHexString(0xff & encrypted[i]));
      //  }
        //return sb.toString();

//        return encrypted;

/*
        String decryptedData = null;
        try {
            SecretKeySpec skeySpec          = new SecretKeySpec(secretKey.getBytes(), "AES");
            IvParameterSpec initialVector   = new IvParameterSpec(initialVectorString.getBytes());
            Cipher cipher                   = Cipher.getInstance("AES/CFB8/NoPadding");

            cipher.init(Cipher.DECRYPT_MODE, skeySpec, initialVector);

            //byte[] encryptedByteArray = Base64.edecode(encryptedData.getBytes());
            //byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);

      //      decryptedData = new String(decryptedByteArray, "UTF8");

        } catch (Exception e) {

            e.printStackTrace();
            //System.err.println("Problem decrypting the data" + e.getMessage());
        }
        return decryptedData;
        */
    }

    private static String padString(String source)
    {
        char paddingChar = ' ';
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;

        for (int i = 0; i < padLength; i++)
        {
            source += paddingChar;
        }

        return source;
    }

    public String cryptWithMD5(String pass){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguridad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


    }

}
