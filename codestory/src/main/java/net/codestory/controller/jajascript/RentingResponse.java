package net.codestory.controller.jajascript;

import com.google.common.collect.Lists;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 23/01/13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class RentingResponse implements Comparable<RentingResponse> {

    @JsonProperty("gain")
    private BigDecimal gain;

    @JsonProperty("path")
    private List<String> flightNames = Lists.newArrayList();

    public void add(RentingResponse response) {
        gain = gain.add(response.getGain());
        flightNames.addAll(0, response.getFlightNames());
    }

    public RentingResponse(BigDecimal gain, List<String> flightNames) {
        this.gain = gain;
        this.flightNames = flightNames;
    }

    public RentingResponse(BigDecimal gain) {
        this.gain = gain;
    }

    public BigDecimal getGain() {
        return gain;
    }

    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    public List<String> getFlightNames() {
        return flightNames;
    }

    public void setFlightNames(List<String> flightNames) {
        this.flightNames = flightNames;
    }

    @Override
    public int compareTo(RentingResponse o) {
        return this.gain.compareTo(o.getGain());
    }
}
