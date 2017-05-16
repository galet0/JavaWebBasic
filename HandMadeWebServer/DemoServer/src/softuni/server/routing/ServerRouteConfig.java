package softuni.server.routing;


import softuni.server.http.HttpRequestMethod;

import java.util.Map;

public interface ServerRouteConfig {

    Map<HttpRequestMethod, Map<String, RoutingContext>> getRoutes();

}
