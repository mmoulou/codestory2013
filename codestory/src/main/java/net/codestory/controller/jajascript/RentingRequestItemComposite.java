package net.codestory.controller.jajascript;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mouhcine MOULOU
 * Date: 23/01/13
 * Time: 21:15
 */
public class RentingRequestItemComposite /*implements Comparable<RentingRequestItemComposite>*/ {

    private List<RentingRequestItemComposite> children =  Lists.newArrayList();

    private RentingRequestItem currentRequest;

    private RentingRequestItemComposite optimalRoute;

    private RentingResponse response = new RentingResponse(BigDecimal.ZERO);

    private boolean root;

    public RentingResponse getOptimalRoute(){
        RentingResponse rentingResponse = new RentingResponse(BigDecimal.ZERO);
        for(RentingRequestItemComposite rentingRequestItemComposite : children) {
            RentingResponse rentingResponseForCurrentChild = rentingRequestItemComposite.getOptimalRoute();
            if(rentingResponseForCurrentChild.compareTo(rentingResponse) > 0) {
                rentingResponse = rentingResponseForCurrentChild;
            }
        }
        if(! root){
            rentingResponse.add(new RentingResponse(currentRequest.getPrice(), Lists.newArrayList(currentRequest.getFlightName())));
        }
        return rentingResponse;
    }

    public void add(RentingRequestItemComposite compositeItem) {
        if(! root) {
            // add RequestItem to the Graph
            if(currentRequest.isNotOverlapped(compositeItem.getCurrentRequest())) {
                boolean isOverlappedWithOneOrMoreRequestItem = false;
                for(RentingRequestItemComposite rentingRequestItemComposite : children) {
                    if(rentingRequestItemComposite.getCurrentRequest().isNotOverlapped(compositeItem.getCurrentRequest())) {
                        rentingRequestItemComposite.add(compositeItem);
                        if(this.getResponse().compareTo(rentingRequestItemComposite.getResponse()) < 0) {
                            this.response = rentingRequestItemComposite.getResponse();
                        }
                    }  else {
                        isOverlappedWithOneOrMoreRequestItem = true;
                        if(rentingRequestItemComposite.couldReplaceCurrentRequest(compositeItem.getCurrentRequest())) {
                            rentingRequestItemComposite.setCurrentRequest(compositeItem.getCurrentRequest());
                            break;
                        }
                    }
                }
                if(isOverlappedWithOneOrMoreRequestItem || this.children.isEmpty()) {
                    this.children.add(compositeItem);
                    Collections.sort(this.children, new RentingRequestItemCompositeComparatorPrice());
                    this.response = children.get(0).getResponse();
                    this.response.add(new RentingResponse(this.getCurrentRequest().getPrice(), Lists.newArrayList(this.getCurrentRequest().getFlightName())));
                }
            }
        } else {
            boolean isOverlappedOnOrMoreRequestItem = false;
            for(RentingRequestItemComposite rentingRequestItemComposite : children) {
                if(rentingRequestItemComposite.getCurrentRequest().isNotOverlapped(compositeItem.getCurrentRequest())) {
                    rentingRequestItemComposite.add(compositeItem);
                    if(this.getResponse().compareTo(rentingRequestItemComposite.getResponse()) < 0) {
                        this.response = rentingRequestItemComposite.getResponse();
                    }
                } else {
                    isOverlappedOnOrMoreRequestItem = true;
                }
            }
            if(isOverlappedOnOrMoreRequestItem || this.children.isEmpty()) {
                this.children.add(compositeItem);
                Collections.sort(this.children, new RentingRequestItemCompositeComparatorPrice());
            }
            this.response = children.get(0).getResponse();
            if(!root){
                this.response.add(new RentingResponse(this.getCurrentRequest().getPrice(), Lists.newArrayList(this.getCurrentRequest().getFlightName())));
            }

        }
    }

    private boolean couldReplaceCurrentRequest(RentingRequestItem request) {
        if(currentRequest.getStart().equals(request.getStart())
                && currentRequest.getDuration().equals(request.getDuration())
                && currentRequest.getPrice().compareTo(request.getPrice()) < 0) {
            return true;
        } else if(currentRequest.getStart().compareTo(request.getStart()) < 0
                && currentRequest.getDuration().equals(request.getDuration())
                && currentRequest.getPrice().compareTo(request.getPrice()) < 0) {
            return true;
        }

        return false;
    }

    private class RentingRequestItemCompositeComparatorPrice implements Comparator<RentingRequestItemComposite> {

        @Override
        public int compare(RentingRequestItemComposite o1, RentingRequestItemComposite o2) {
            return o2.getResponse().compareTo(o1.getResponse());
        }
    }

    public RentingRequestItemComposite() {
        root = true;
    }

    public RentingRequestItemComposite(RentingRequestItem currentRequest) {
        this.currentRequest = currentRequest;
    }

    public RentingRequestItem getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(RentingRequestItem currentRequest) {
        this.currentRequest = currentRequest;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public RentingResponse getResponse() {
        return response;
    }

    //    @Override
//    public int compareTo(RentingRequestItemComposite o) {
//        BigDecimal sum = BigDecimal.ZERO;
//
//        return 0;
//    }
}
