package net.codestory.controller.javascript;

import junit.framework.Assert;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 15/01/13
 * Time: 21:45
 */
public class RentingRequestTest {

    @Test
    public void serializeRentingRequestShouldBeOk() throws IOException {

        // G
        String rentingRequestAsJson = "[" +
                "{ \"VOL\": \"MONAD42\", \"DEPART\": 0, \"DUREE\": 5, \"PRIX\": 10 }," +
                "{ \"VOL\": \"META18\", \"DEPART\": 3, \"DUREE\": 7, \"PRIX\": 14 }," +
                "{ \"VOL\": \"LEGACY01\", \"DEPART\": 5, \"DUREE\": 9, \"PRIX\": 8 }," +
                "{ \"VOL\": \"YAGNI17\", \"DEPART\": 5, \"DUREE\": 9, \"PRIX\": 7 }" +
                "]" ;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createJsonParser(rentingRequestAsJson);

        // W
        List<RentingRequestItem> rentingRequest= objectMapper.readValue(jsonParser, new TypeReference<List<RentingRequestItem>>() {});

        // T
        Assert.assertNotNull(rentingRequest);
        Assert.assertEquals(4, rentingRequest.size());
        Assert.assertEquals("MONAD42", rentingRequest.get(0).getFlightName());
        Assert.assertEquals(0, rentingRequest.get(0).getStart().intValue());
        Assert.assertEquals(5, rentingRequest.get(0).getDuration().intValue());
        Assert.assertEquals(10, rentingRequest.get(0).getPrice().intValue());


    }
}
