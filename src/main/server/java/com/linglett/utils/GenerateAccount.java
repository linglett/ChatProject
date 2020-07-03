package server.java.com.linglett.utils;

public class GenerateAccount {
    //生成随机账号(Accounnt)
    public static String CreateAccount() {
        long time = System.currentTimeMillis();
        String ss= (String.valueOf(time)).substring((String.valueOf(time)).length()-10);
        return ss;
    }
    public static String CreateAccount(int z){
        long time = System.currentTimeMillis();
        String ss= (String.valueOf(time)).substring((String.valueOf(time)).length()-6);
        return ss;
    }

}
