package softuni.server.routing;

import softuni.server.handler.RequestHandlerImpl;

import java.util.List;


public class RoutingContextImpl implements RoutingContext{

    private RequestHandlerImpl handler;

    private List<String> paramNames;

    public RoutingContextImpl(RequestHandlerImpl requestHandler, List<String> paramNames) {
        this.handler = requestHandler;
        this.paramNames = paramNames;
    }

    @Override
    public RequestHandlerImpl getHandler() {
        return this.handler;
    }

    @Override
    public Iterable<String> getParamNames() {
        return this.paramNames;
    }
}
