package net.codestory.controller.scalaskel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.codestory.Controller;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 12/01/13
 * Time: 02:42
 * To change this template use File | Settings | File Templates.
 */
public class ScalaskelResource implements Controller {

    private int sum;
    private List<Map<CoinEnum, Integer>> responseList = Lists.newArrayList();

    @Override
    public List<String> getUriTemplate() {
        return Lists.newArrayList("/scalaskel/change/{coinValue}");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void answerQuestion(HttpRequest httpRequest, HttpResponse httpResponse, Map<String, String> matchResult) {
        try {
            responseList.clear();
            sum = Integer.parseInt(matchResult.get("coinValue"));
            List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
            Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();
            constructResult(0, allCoinEnums,  coinPieces);
            httpResponse.setContent(ChannelBuffers.copiedBuffer(getJsonResult(), CharsetUtil.UTF_8));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getJsonResult() throws IOException {

        final JsonFactory jsonFactory = new JsonFactory();
        final StringWriter stringWriter = new StringWriter();
        final JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(stringWriter);

        jsonGenerator.writeStartArray();
        for(Map<CoinEnum, Integer> map : responseList){
            jsonGenerator.writeStartObject();
            for(CoinEnum coinEnum : map.keySet()) {
                jsonGenerator.writeNumberField(coinEnum.name().toLowerCase(), map.get(coinEnum));
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.close();

        return stringWriter.toString();
    }

    public void constructResult(int accu, List<CoinEnum> coinEnums, Map<CoinEnum, Integer> coinPieces) {
        CoinEnum coinEnum = coinEnums.isEmpty() ? null : coinEnums.get(0);
        while(true && coinEnum != null) {
           if(accu + coinEnum.getValue() == this.sum) {
               if(coinPieces.get(coinEnum) == null ) {
                   coinPieces.put(coinEnum, 1);
               } else {
                   coinPieces.put(coinEnum, coinPieces.get(coinEnum) + 1);
               }

               responseList.add(coinPieces);
               break;
           } else if(accu + coinEnum.getValue() < this.sum) {
               Map<CoinEnum, Integer> copyCoinPieces = Maps.newHashMap(coinPieces);
               constructResult(accu, coinEnums.subList(1, coinEnums.size() ), copyCoinPieces);
               if(coinPieces.get(coinEnum) == null ) {
                   coinPieces.put(coinEnum, 1);
               } else {
                   coinPieces.put(coinEnum, coinPieces.get(coinEnum) + 1);
               }
               accu += coinEnum.getValue();
           }  else {
                break;
           }
        }
        if(coinEnums.size() >= 1) {
            Map<CoinEnum, Integer> copyCoinPieces = Maps.newHashMap(coinPieces);
            constructResult(accu, coinEnums.subList(1, coinEnums.size()), copyCoinPieces);
        }
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public List<Map<CoinEnum, Integer>> getResponseList() {
        return responseList;
    }

    @Override
    public boolean IsUriRegexDefinition() {
        return false;
    }
}
