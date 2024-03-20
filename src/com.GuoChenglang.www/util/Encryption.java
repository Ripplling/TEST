package util;

public class Encryption {
    //加密规则，每个字符×2+5
    public static String encryption(String password) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = (char) (password.charAt(i) * 2 + 5);
            sb.append(c);
        }
        return sb.toString();
    }

}
