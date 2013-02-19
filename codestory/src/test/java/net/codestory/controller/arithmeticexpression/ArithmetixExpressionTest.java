package net.codestory.controller.arithmeticexpression;

import groovy.lang.GroovyShell;
import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 13/01/13
 * Time: 12:10
 */
public class ArithmetixExpressionTest {

    @Test
    public void testGroovyShell() {
        String exp = "1.0000000000000000000000000000000000000000000000001*1.0000000000000000000000000000000000000000000000001";
        GroovyShell groovyShell = new GroovyShell();
        String rep = String.valueOf(groovyShell.evaluate(exp));

        String exp2 = "((1.1+2)+3.14+4+(5+6+7)+(8+9+10)*4267387833344334647677634)/2*553344300034334349999000";
        BigDecimal evaluationResult = (BigDecimal)groovyShell.evaluate(exp2);

        Assert.assertNotNull(rep);
    }
}
