package server.java.com.linglett.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    private String REGEX;
    private String input;
    //构造器
    public RegexMatches(String REGEX, String input)
    {
        this.REGEX = REGEX;
        this.input = input;
    }

    /**
     * 正则表达式匹配，用到matches()方法
     * 该方法要求完全匹配
     */
    public boolean match()
    {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
