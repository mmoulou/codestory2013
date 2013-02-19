package net.codestory.controller.javascript;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 15/01/13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class RentingRequestItemTest {

    @Test
    public void testSortRequestItemSouldBeOk() {

        // G
        List<RentingRequestItem> items = Lists.newArrayList(
                new RentingRequestItem("VOL1", BigDecimal.ZERO, new BigDecimal(3), new BigDecimal(3)),
                new RentingRequestItem("VOL2", BigDecimal.ZERO, new BigDecimal(2), new BigDecimal(4)),
                new RentingRequestItem("VOL3", BigDecimal.ONE, new BigDecimal(3), new BigDecimal(5)),
                new RentingRequestItem("VOL4", new BigDecimal(3), new BigDecimal(6), new BigDecimal(10)),
                new RentingRequestItem("VOL5", new BigDecimal(4), new BigDecimal(5), new BigDecimal(30)),
                new RentingRequestItem("VOL6", new BigDecimal(11), new BigDecimal(3), new BigDecimal(3))
        );

        // W
        Collections.sort(items);

        // T
        Assert.assertNotNull(items);
    }
}
