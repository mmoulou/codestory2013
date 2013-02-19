package net.codestory;

import java.util.Map;

/**
 * @author : Mouhcine MOULOU
 */
public class RouteResult {

    private final Controller controller;
    private final Map<String, String> matchResult;

    public RouteResult(Controller controller, Map<String, String> matchResult) {
        this.controller = controller;
        this.matchResult = matchResult;
    }

    public Controller getController() {
        return controller;
    }

    public Map<String, String> getMatchResult() {
        return matchResult;
    }
}
