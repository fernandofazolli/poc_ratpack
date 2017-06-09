package br.com.estudos.ratpack;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

public class RatpackPoc {

    public static void main(String... args) throws Exception {
        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.embedded().port(8080))
                .handlers(chain -> chain
                        .get(new HelloWorldHandler())
                        .get("parameter/:name", new HelloWorldParameterHandler())
                        .post("hello", new HelloWorldPostHandler())
                )
        );
    }

    static class HelloWorldHandler implements Handler {
        @Override
        public void handle(Context ctx) throws Exception {
            ctx.render("Hello World !!!");
        }
    }

    static class HelloWorldParameterHandler implements Handler {
        @Override
        public void handle(Context ctx) throws Exception {
            ctx.render("Hello " + ctx.getPathTokens().get("name") + " !!!");
        }
    }

    static class HelloWorldPostHandler implements Handler {
        @Override
        public void handle(Context ctx) throws Exception {
            ctx.getResponse().send("{\"p1\":\"v1\"}");
        }
    }
}