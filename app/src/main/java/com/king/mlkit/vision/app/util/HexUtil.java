package com.king.mlkit.vision.app.util;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class HexUtil {

    private HexUtil(){
        throw new AssertionError();
    }

    /**
     * bytes 转16进制字符串
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }




    /**
     * 16进制字符串转bytes
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {

        int len = 0;
        int num=0;

        //判断字符串的长度是否是两位
        if(hex.length()>=2){
            //判断字符喜欢是否是偶数
            len=(hex.length() / 2);
            num = (hex.length() % 2);

            if (num == 1) {
                hex = "0" + hex;
                len=len+1;
            }


        }else{

            hex = "0" + hex;
            len=1;


        }
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }


    private static int toByte(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

}
