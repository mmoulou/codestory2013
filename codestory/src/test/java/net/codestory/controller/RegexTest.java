package net.codestory.controller;

import junit.framework.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 13/01/13
 * Time: 00:02
 * To change this template use File | Settings | File Templates.
 */
public class RegexTest {

    @Test
    public void testRegexMustBeOk() {
        Pattern patt = Pattern.compile("^/\\?q=[0-9]\\+[0-9]");
        Matcher matcher = patt.matcher("/?q=1+1");
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testFindRegexMustBeOk() {
        Pattern patt = Pattern.compile("^/\\?q=[0-9]\\+[0-9]");
        Matcher matcher = patt.matcher("/?q=1+1");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void testFindWithParenthesesRegexMustBeOk() {
        Pattern patt = Pattern.compile("^/\\?q=([0-9]|\\([\\-\\*\\/\\+0-9\\(]*)");
        Matcher matcher = patt.matcher("/?q=1*(2*3)");
        Assert.assertTrue(matcher.find());
        Assert.assertFalse(matcher.matches());
    }

    @Test
    public void testFindWithParentheses2RegexMustBeOk() {
        Pattern patt = Pattern.compile("^/\\?q=([0-9]|\\([\\-\\*\\/\\+0-9\\(]*)");
        Matcher matcher = patt.matcher("/?q=(1+2)*2");
        Assert.assertTrue(matcher.find());
        Assert.assertFalse(matcher.matches());
    }

    @Test
    public void testRegex2MustBeOk() {
        Pattern patt = Pattern.compile("^/\\?q=[0-9][\\+|\\*|\\-|\\/][0-9]");
        Matcher matcher = patt.matcher("/?q=1*1");
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void testRegexArithmeticExpressionMustBeOk() {


//        String regex = "(?x)^(?> (?<p> \\( )* (?>-?\\d+(?:\\.\\d+)?) (?<-p> \\) )* )(?>(?:[\\-\\+\\*\\/](?> (?<p> \\( )* (?>-?\\d+(?:\\.\\d+)?) (?<-p> \\) )* ) )*)(?(p)(?!))$";
        String regex = "^[0-9][\\-\\*\\/\\+0-9]+$";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher("1*1");
        Assert.assertTrue(matcher.matches());
    }
}
