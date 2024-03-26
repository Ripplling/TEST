package util;

public class Decryption {
    //简单解密操作，即该字符串减5然后除以2，不过并没有用到
    public static String decryption(String repassword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repassword.length(); i++) {
            char c = (char) ((repassword.charAt(i) - 5) / 2);
            sb.append(c);
        }
        return sb.toString();
    }
}
