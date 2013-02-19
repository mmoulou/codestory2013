package net.codestory.controller.jajascript;

import com.google.common.collect.Lists;
import com.google.gdata.util.common.base.Pair;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 16/01/13
 * Time: 00:22
 * To change this template use File | Settings | File Templates.
 */
public class JajaScriptResourceTest {

    @Test
    public void getOptimalResultSouldBeOk() {

        // G
        List<RentingRequestItem> items = Lists.newArrayList(
                new  RentingRequestItem("MONAD42", new BigDecimal(0), new BigDecimal(5), new BigDecimal(10)),
                new  RentingRequestItem("META18", new BigDecimal(3), new BigDecimal(5), new BigDecimal(10)),
                new  RentingRequestItem("LEGACY01", new BigDecimal(5), new BigDecimal(9), new BigDecimal(8)),
                new  RentingRequestItem("YAGNI17", new BigDecimal(5), new BigDecimal(9), new BigDecimal(7))
        ) ;
        JajaScriptResource javaScriptResource = new JajaScriptResource();
        Pair<BigDecimal, List<RentingRequestItem>> optimalResult = new Pair<BigDecimal, List<RentingRequestItem>>(BigDecimal.ZERO, new ArrayList<RentingRequestItem>());
        LinkedList<RentingRequestItem> accuFlightList = Lists.newLinkedList();

        // W
    }
}
