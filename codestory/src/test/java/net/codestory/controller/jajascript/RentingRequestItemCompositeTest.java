package net.codestory.controller.jajascript;

import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 23/01/13
 * Time: 21:58
 */
public class RentingRequestItemCompositeTest {

    @Test
    public void addShouldreturnOptimalresult1(){
        // G
        List<RentingRequestItem> items = Lists.newArrayList(
                new RentingRequestItem("MONAD42", new BigDecimal(0), new BigDecimal(5), new BigDecimal(10)),
                new RentingRequestItem("META18", new BigDecimal(3), new BigDecimal(5), new BigDecimal(10)),
                new RentingRequestItem("LEGACY01", new BigDecimal(5), new BigDecimal(9), new BigDecimal(8)),
                new RentingRequestItem("YAGNI17", new BigDecimal(5), new BigDecimal(9), new BigDecimal(7))
        );
        RentingRequestItemComposite rentingRequestItemComposite = new RentingRequestItemComposite();

        // W
        RentingResponse rentingResponse = null;
        int i = 0;
        for( i = 0 ; i< items.size() - 1; i++) {
            rentingRequestItemComposite.add(new RentingRequestItemComposite(items.get(i)));
        }
        rentingRequestItemComposite.add(new RentingRequestItemComposite(items.get(i)));
        rentingResponse = rentingRequestItemComposite.getOptimalRoute();

        // T
        Assert.assertNotNull(rentingResponse);
    }

    @Test
    public void addShouldreturnOptimalresult2(){
        // G
        List<RentingRequestItem> items = Lists.newArrayList(
                new RentingRequestItem("AF1", new BigDecimal(0), new BigDecimal(1), new BigDecimal(5)),
                new RentingRequestItem("AF2", new BigDecimal(0), new BigDecimal(1), new BigDecimal(6))
        );
        RentingRequestItemComposite rentingRequestItemComposite = new RentingRequestItemComposite();

        // W
        RentingResponse rentingResponse = null;
        int i = 0;
        for( i = 0 ; i< items.size() - 1; i++) {
            rentingRequestItemComposite.add(new RentingRequestItemComposite(items.get(i)));
        }
        rentingRequestItemComposite.add(new RentingRequestItemComposite(items.get(i)));
        rentingResponse = rentingRequestItemComposite.getResponse();
        // T
        Assert.assertNotNull(rentingResponse);
    }
}

