package net.codestory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import net.codestory.controller.MultipleChoiceResource;
import net.codestory.controller.MailAddressResource;
import net.codestory.controller.arithmeticexpression.ArithmeticExpressionResource;
import net.codestory.controller.ennonce.EnonoceResource;
import net.codestory.controller.javascript.JajaScriptResource;
import net.codestory.controller.scalaskel.ScalaskelResource;
import net.codestory.controller.NoResponseResource;
import net.codestory.controller.YESResponseResource;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Slf4JLoggerFactory;

/**
 * @author : Mouhcine MOULOU
 */
public class CodeStoryServer {

    private final int port;

    private ServerBootstrap serverBootstrap;

    static {
        InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
    }

    public CodeStoryServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {

        // Configure the netty server.
        serverBootstrap= new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool(),
                        4 * Runtime.getRuntime().availableProcessors()));

        // Set up the event pipeline factory.
        serverBootstrap.setPipelineFactory(new RestServerPipelineFactory(registerResources()));

        // Bind and start to accept incoming connections.
        serverBootstrap.bind(new InetSocketAddress(port));
    }

    public void shutdown() {
        serverBootstrap.releaseExternalResources();
    }

    public static void main(String[] args) throws InterruptedException {
        int port = Integer.parseInt(System.getProperties().getProperty("http.port"));
        new CodeStoryServer(port).start();
    }


    protected Dispatcher registerResources(){
        final Dispatcher dispatcher = new Dispatcher();
        dispatcher.subscribe(new MailAddressResource());
        dispatcher.subscribe(new YESResponseResource());
        dispatcher.subscribe(new NoResponseResource());
        dispatcher.subscribe(new ScalaskelResource());
        dispatcher.subscribe(new JajaScriptResource());
        dispatcher.subscribe(new EnonoceResource());
        dispatcher.subscribe(new MultipleChoiceResource());
        dispatcher.subscribe(new ArithmeticExpressionResource());
        return dispatcher;
    }

}