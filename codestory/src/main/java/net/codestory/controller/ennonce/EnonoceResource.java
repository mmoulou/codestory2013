package net.codestory.controller.ennonce;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.codestory.Controller;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 15/01/13
 * Time: 00:53
 */
public class EnonoceResource implements Controller {
    private final static Logger logger = LoggerFactory.getLogger(EnonoceResource.class);

    private Map<Integer, String> enonces = Maps.newHashMap();

    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList("/enonce/{enonce}");
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {

        int enonce = Integer.parseInt(matchResult.get("enonce"));
        if(HttpMethod.GET.equals(httpRequest.getMethod())){
            httpResponse.setContent(ChannelBuffers.copiedBuffer(enonces.get(enonce), CharsetUtil.UTF_8));
            return;
        }

        enonces.put(enonce, httpRequest.getContent().toString(CharsetUtil.UTF_8));

        logger.info("nouvelle enoncé - ennonce" + enonce + ": " );
        logger.info(httpRequest.getContent().toString(CharsetUtil.UTF_8));

    }
}
