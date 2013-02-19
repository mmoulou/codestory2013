package net.codestory;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.*;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author : Mouhcine MOULOU
 */
public class RestServerHandler extends SimpleChannelUpstreamHandler  {

    private final static Logger logger = LoggerFactory.getLogger(RestServerHandler.class);

    private final Dispatcher dispatcher;

    public RestServerHandler(final Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent messageEvent) throws Exception {
    	
        final Object msg = messageEvent.getMessage();

        // Http request
        if (msg instanceof HttpRequest) {
            final HttpRequest httpRequest = (HttpRequest) msg;
            final String requestPath = httpRequest.getUri();

            logger.info("new question: " + requestPath);
            final RouteResult routeResult = dispatcher.getResourceForUri(requestPath);
            HttpResponse httpResponse = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
            if(routeResult == null) {
                logger.info("New Question need to be implemented: " + requestPath);
                logger.info(httpRequest.toString());
                sendError(ctx,HttpResponseStatus.BAD_REQUEST);
            } else {
                // Build the response object.
                routeResult.getController().answerQuestion(httpRequest, httpResponse, routeResult.getMatchResult());
                ChannelFuture future = messageEvent.getChannel().write(httpResponse);
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, status);
        response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.setContent(ChannelBuffers.copiedBuffer(
                "Failure: " + status.toString() + "\r\n",
                CharsetUtil.UTF_8));
        // Close the connection as soon as the error message is sent.
        ctx.getChannel().write(response).addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
    		throws Exception {
    	
    	HttpResponseStatus status = HttpResponseStatus.INTERNAL_SERVER_ERROR;
    	HttpResponse response = new DefaultHttpResponse(HTTP_1_1, status);
        response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.setContent(ChannelBuffers.copiedBuffer("Failure: " + e.toString() + "\r\n", CharsetUtil.UTF_8));

        // Close the connection as soon as the error message is sent.
        ctx.getChannel().write(response).addListener(ChannelFutureListener.CLOSE);
        e.getCause().printStackTrace();
    }

}
