package net.codestory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import net.codestory.uri.UriTemplate;

/**
 * @author : Mouhcine MOULOU
 */
public class Dispatcher {

    private final Map<Controller, List<UriTemplate>> resourceMap = new LinkedHashMap<Controller, List<UriTemplate>>();

    public RouteResult getResourceForUri(final String requestPath) {
        for(Controller controller : resourceMap.keySet()) {
            for (UriTemplate uriTemplate : resourceMap.get(controller)){
                if(uriTemplate.matches(requestPath) || uriTemplate.getUriTemplate().equals(requestPath)) {
                    return new RouteResult(controller, uriTemplate.match(requestPath));
                } else if(controller.IsUriRegexDefinition()) {
                    try {
                        Pattern patt = Pattern.compile(uriTemplate.getUriTemplate());
                        Matcher matcher = patt.matcher(requestPath);
                        if(matcher.matches() || matcher.find()) {
                            return new RouteResult(controller, uriTemplate.match(requestPath));
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public void subscribe(final Controller controller) {
        List<UriTemplate> templates = Lists.newArrayList();

        for(String uri : controller.getUriTemplate()) {
            templates.add(new UriTemplate(uri));
        }
        resourceMap.put(controller, templates);
    }
}
