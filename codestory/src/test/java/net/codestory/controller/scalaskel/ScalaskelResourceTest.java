package net.codestory.controller.scalaskel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 12/01/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class ScalaskelResourceTest {

    @Test
    public void checkJsonResponseFor1() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(1);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);
        String responseResultAsJson = scalaskelResource.getJsonResult();
        int x=0;

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(1, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponseMustHaveOneCoin() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(1);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(1, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponse2MustHaveOneCoin() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(2);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(1, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponseFor3MustHaveOneCoin() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(3);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(1, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponseFor7MustHaveTwoElements() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(7);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(2, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponseMustFor8HaveTwoElements() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(8);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(2, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponseMustFor11HaveThreeElements() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(11);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(3, scalaskelResource.getResponseList().size());
    }

    @Test
    public void listResponseMustFor20HaveFiveElements() throws Exception{
        // G
        ScalaskelResource scalaskelResource = new ScalaskelResource();
        scalaskelResource.setSum(20);
        List<CoinEnum> allCoinEnums = Lists.newArrayList((CoinEnum[]) Class.forName("net.codestory.controller.scalaskel.CoinEnum").getEnumConstants());
        Map<CoinEnum, Integer> coinPieces = Maps.newHashMap();;

        // W
        scalaskelResource.constructResult(0, allCoinEnums, coinPieces);

        // T
        Assert.assertTrue(!scalaskelResource.getResponseList().isEmpty());
        Assert.assertEquals(5, scalaskelResource.getResponseList().size());
    }
}
