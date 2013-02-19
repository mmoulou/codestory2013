package net.codestory.controller;

import com.google.common.collect.Lists;
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
 * Date: 23/01/13
 * Time: 22:37
 */
public class MailAddressResource implements Controller {
    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList("/?q=Quelle+est+ton+adresse+email");
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {
        httpResponse.setContent(ChannelBuffers.copiedBuffer("mouhcine.moulou@soat.fr", CharsetUtil.UTF_8));
    }
}
