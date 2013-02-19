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
 * Date: 12/01/13
 * Time: 02:32
 */
public class NoResponseResource implements Controller {
    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList(
                "/?q=Est+ce+que+tu+reponds+toujours+oui(OUI/NON)",
                "/?q=As+tu+copie+le+code+de+ndeloof(OUI/NON/JE_SUIS_NICOLAS)"
        );
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {
        httpResponse.setContent(ChannelBuffers.copiedBuffer("NON", CharsetUtil.UTF_8));
    }
}