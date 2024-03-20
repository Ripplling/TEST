package util;

public class Decryption {
    public static String decryption(String repassword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repassword.length(); i++) {
            char c = (char) ((repassword.charAt(i) - 5) / 2);
            sb.append(c);
        }
        return sb.toString();
    }
}
