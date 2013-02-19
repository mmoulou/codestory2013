package net.codestory.controller.javascript;

import com.google.common.collect.Lists;
import net.codestory.Controller;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 15/01/13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public class JajaScriptResource implements Controller{

    private final static Logger logger = LoggerFactory.getLogger(JajaScriptResource.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JsonFactory jsonFactory = new JsonFactory();

    List<RentingRequestItem> requests = Lists.newArrayList();

    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList("/jajascript/optimize");
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {

        String rentingRequestsASJson = httpRequest.getContent().toString(CharsetUtil.UTF_8);
        logger.info("parsing requests: " + rentingRequestsASJson);

        JsonParser jsonParser = null;
        try {
            jsonParser = jsonFactory.createJsonParser(rentingRequestsASJson);
            List<RentingRequestItem> rentingRequest = objectMapper.readValue(jsonParser, new TypeReference<List<RentingRequestItem>>() {});
            Collections.sort(rentingRequest);

            RentingResponse rentingResponse = new RentingResponse(BigDecimal.ZERO);
            if(!rentingRequest.isEmpty()) {
                RentingRequestItemComposite rentingRequestItemComposite =  new RentingRequestItemComposite();
                int i = 0;
                for(; i< rentingRequest.size() ; i++) {
                    rentingRequestItemComposite.add(new RentingRequestItemComposite(rentingRequest.get(i)));
                }
                rentingResponse =  rentingRequestItemComposite.getOptimalRoute();
            }

            ObjectMapper mapper = new ObjectMapper();
            final StringWriter stringWriter = new StringWriter();
            mapper.writeValue(stringWriter, rentingResponse);
            String optimalResultAsJson = stringWriter.toString();

            logger.info("sending Response : " + optimalResultAsJson );
            ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer(optimalResultAsJson.length());
            channelBuffer.writeBytes(ChannelBuffers.copiedBuffer(optimalResultAsJson, CharsetUtil.UTF_8));
            httpResponse.setContent(channelBuffer);
            httpResponse.setStatus(HttpResponseStatus.CREATED);
            httpResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpResponse.setChunked(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Deprecated
    public String getOptimalResultAsJson(TreeMap<BigDecimal, List<RentingRequestItem>> allPossibleResults) throws IOException {

        final JsonFactory jsonFactory = new JsonFactory();
        final StringWriter stringWriter = new StringWriter();
        final JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(stringWriter);

        BigDecimal optimalResultPrice = allPossibleResults.descendingKeySet().first();
        List<RentingRequestItem> items = allPossibleResults.get(optimalResultPrice);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("gain", optimalResultPrice.intValue());
        jsonGenerator.writeArrayFieldStart("path");
        for(RentingRequestItem rentingRequestItem : Lists.reverse(items)){
            jsonGenerator.writeString(rentingRequestItem.getFlightName());
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        return stringWriter.toString();
    }

    @Deprecated
    public TreeMap<BigDecimal, List<RentingRequestItem>> getOptimalResult(
            LinkedList<RentingRequestItem> accuFlightList,
            BigDecimal accu,
            List<RentingRequestItem> rentingRequest,
            TreeMap<BigDecimal, List<RentingRequestItem>> allPossibleResults) {

        Iterator<RentingRequestItem> rentingRequestItemIterator = rentingRequest.iterator();

        LinkedList<RentingRequestItem> accuFlightListCopy = Lists.newLinkedList();
        Collections.copy(accuFlightListCopy, accuFlightList);
        BigDecimal accuCopy = new BigDecimal(accu.toString());

        if(rentingRequestItemIterator.hasNext()) {
            RentingRequestItem item = rentingRequestItemIterator.next();
            List<RentingRequestItem> rentingRequestList = rentingRequest.subList(1, rentingRequest.size());
            if(accuFlightList.isEmpty()) {
                accuFlightList.addFirst(item);
                accu = accu.add(item.getPrice());
            } else if(accuFlightList.peekFirst().notOverlapped(item) <= 0) {
                accuFlightList.addFirst(item);
                accu = accu.add(item.getPrice());
            }
            allPossibleResults.putAll(getOptimalResult(accuFlightListCopy, accuCopy, rentingRequestList, allPossibleResults));
            allPossibleResults.putAll(getOptimalResult(accuFlightList, accu, rentingRequestList, allPossibleResults));
        }
        allPossibleResults.put(accu, accuFlightList);
        return allPossibleResults;
    }
}
