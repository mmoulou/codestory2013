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
 * Date: 15/01/13
 * Time: 00:49
 */
public class MultipleChoiceResource implements Controller {
    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList("/?q=As+tu+passe+une+bonne+nuit+malgre+les+bugs+de+l+etape+precedente(PAS_TOP/BOF/QUELS_BUGS)");
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {
        httpResponse.setContent(ChannelBuffers.copiedBuffer("PAS_TOP", CharsetUtil.UTF_8));
    }
}
