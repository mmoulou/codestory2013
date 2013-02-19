package net.codestory;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

import java.util.List;
import java.util.Map;

/**
 * @author : Mouhcine MOULOU
 */
public interface Controller {

    /**
     *
     * @return
     */
    List<String> getUriTemplate();

    /**
     *
     * @return
     */
    boolean IsUriRegexDefinition() ;

    /**
     *
     * @param httpRequest
     * @param httpResponse
     * @param matchResult
     */
    void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult);
}
