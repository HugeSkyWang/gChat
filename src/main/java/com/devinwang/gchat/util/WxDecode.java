package com.devinwang.gchat.util;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class WxDecode {
    /**
     * 解密工具直接放进去即可
     */
    public static String decryptS5(String sSrc, String encodingFormat, String sKey, String ivParameter) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] raw = decoder.decodeBuffer(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(decoder.decodeBuffer(ivParameter));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] myendicod = decoder.decodeBuffer(sSrc);
            byte[] original = cipher.doFinal(myendicod);
            return new String(original, encodingFormat);
        } catch (Exception ex) {
            return null;
        }
    }
}
