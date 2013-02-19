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
 * Time: 00:41
 */
public class YESResponseResource implements Controller {
    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList(
                "/?q=Es+tu+heureux+de+participer(OUI/NON)",
                "/?q=Es+tu+abonne+a+la+mailing+list(OUI/NON)",
                "/?q=As+tu+bien+recu+le+second+enonce(OUI/NON)",
                "/?q=Es+tu+pret+a+recevoir+une+enonce+au+format+markdown+par+http+post(OUI/NON)",
                "/?q=As+tu+bien+recu+le+premier+enonce(OUI/NON)"
                );
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {
        httpResponse.setContent(ChannelBuffers.copiedBuffer("OUI", CharsetUtil.UTF_8));
    }
}
