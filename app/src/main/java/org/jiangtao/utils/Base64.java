package org.jiangtao.utils;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import java.io.IOException;

/**
 * Created by MrJiang on 2016/3/19.
 * 基于Base64位加密解密
 */
public class Base64 {

    private static Base64 instance;
    private static BASE64Encoder encoder = new BASE64Encoder();
    private static BASE64Decoder decoder = new BASE64Decoder();

    private Base64() {
    }

    public static Base64 getInstance() {
        if (instance == null) {
            synchronized (Base64.class) {
                instance = new Base64();
            }
        }
        return instance;
    }

    /**
     * BASE64 编码
     *
     * @param buff
     * @return
     */
    public String encodeBufferBase64(byte[] buff) {
        return buff == null ? null : encoder.encodeBuffer(buff).trim();
    }


    /**
     * BASE64解码
     *
     * @param s
     * @return
     */
    public byte[] decodeBufferBase64(String s) {
        try {
            return s == null ? null : decoder.decodeBuffer(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * base64编码
     *
     * @param bytes 字符数组
     * @return
     * @throws IOException
     */
    public static String encodeBytes(byte[] bytes) throws IOException {
        return new BASE64Encoder().encode(bytes).replace("\n", "").replace("\r", "");
    }

    /**
     * base64解码
     *
     * @param bytes 字符数组
     * @return
     * @throws IOException
     */
    public static String decodeBytes(byte[] bytes) throws IOException {
        return new String(new BASE64Decoder().decodeBuffer(new String(bytes)));
    }
}
