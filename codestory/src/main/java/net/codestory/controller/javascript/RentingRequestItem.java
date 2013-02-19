package net.codestory.controller.javascript;

//import org.codehaus.jackson.annotate.JsonProperty;

import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 15/01/13
 * Time: 21:22
 */
public class RentingRequestItem implements Comparable<RentingRequestItem>{

    public static final RentingRequestItem EMPTY_REQUEST = new RentingRequestItem();

    @JsonProperty("VOL")
    private String flightName;

    @JsonProperty("DEPART")
    private BigDecimal start;

    @JsonProperty("DUREE")
    private BigDecimal duration;

    @JsonProperty("PRIX")
    private BigDecimal price;

    public RentingRequestItem(){
        start = BigDecimal.ZERO;
        duration = BigDecimal.ZERO;
        price = BigDecimal.ZERO;
    }


    public RentingRequestItem(String flightName, BigDecimal start, BigDecimal duration, BigDecimal price) {
        this.flightName = flightName;
        this.start = start;
        this.duration = duration;
        this.price = price;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public BigDecimal getStart() {
        return start;
    }

    public void setStart(BigDecimal start) {
        this.start = start;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int compareTo(RentingRequestItem o) {
        if(this.start.compareTo(o.start) == 0 && this.duration.compareTo(o.duration) == 0 ) {
            return - price.compareTo(o.price);
        } else if(this.start.compareTo(o.start) == 0 && this.duration.compareTo(o.duration) > 0 && this.price.compareTo(o.price) < 0) {
            return 1;
        } else if(start.add(duration).compareTo(o.start.add(o.duration)) == 0 ) {
            return (start.compareTo(o.start) / Math.abs(start.compareTo(o.start))) * price.compareTo(o.price);
        }
        return this.start.compareTo(o.start);
    }

    public int notOverlapped(RentingRequestItem newtItem) {
        return (start.add(duration)).compareTo(newtItem.start);
    }

    /**
     * @param newtItem > This
     * @return
     */
    public boolean isNotOverlapped(RentingRequestItem newtItem) {
        return start.add(duration).compareTo(newtItem.start) <= 0;
    }

    public boolean canReplace(RentingRequestItem item) {
        if(this.getStart().equals(item.getStart())
                && this.getDuration().equals(item.getDuration())
                && this.getPrice().compareTo(item.getPrice()) < 0) {
            return true;
        }else if(BigDecimal.ZERO.equals(item.getStart())
                && this.getStart().add(getDuration()).equals(item.getDuration())
                && this.getPrice().compareTo(item.getPrice()) < 0) {
            return true;
        } else if(this.getStart().compareTo(item.getStart()) < 0
                && this.getDuration().equals(item.getDuration())
                && this.getPrice().compareTo(item.getPrice()) < 0) {
            return true;
        }
        return false;
    }

}