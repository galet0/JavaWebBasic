package softuni.server.routing;

import softuni.server.handler.RequestHandlerImpl;


public interface RoutingContext {

    RequestHandlerImpl getHandler();

    Iterable<String> getParamNames();

}
