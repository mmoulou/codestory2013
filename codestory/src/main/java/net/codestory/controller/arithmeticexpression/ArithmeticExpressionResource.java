package net.codestory.controller.arithmeticexpression;

import com.google.common.collect.Lists;
import groovy.lang.GroovyShell;
import net.codestory.Controller;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 12/01/13
 * Time: 22:38
 */
public class ArithmeticExpressionResource implements Controller{


    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList("^/\\?q=[\\-\\*\\/\\+0-9\\(\\)\\,]*");
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {
        try {
            String question = httpRequest.getUri().split("=")[1].replaceAll(",", ".");
            GroovyShell groovyShell = new GroovyShell();
            String result = String.valueOf( ((Double)new GroovyShell().evaluate(question)) ).replace('.', ',');
            if(result.endsWith(",00")) {
                result = result.replace(",00", "");
            } else if(result.endsWith(",0")) {
                result = result.replace(",0", "");
            }

            httpResponse.setContent(ChannelBuffers.copiedBuffer(result, CharsetUtil.UTF_8));

        } catch (Exception e) {
        }
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return true;
    }
}
