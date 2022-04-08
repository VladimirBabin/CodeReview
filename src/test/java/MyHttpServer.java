import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyHttpServer {

    public static void main(String[] args) throws Exception {
        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.setServer();
    }

    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public void setServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8888), 0);
        server.createContext("/test", new MyHttpHandler());
        server.setExecutor(threadPoolExecutor);
        System.out.println("Server started at port 8888");
        server.start();
    }

    private class MyHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestParamValue = null;

            if ("GET".equals(exchange.getRequestMethod())) {
                requestParamValue = handleGetRequest(exchange);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                requestParamValue = handlePostRequest(exchange);
            }
            handleResponse(exchange, requestParamValue);
        }

        private String handleGetRequest(HttpExchange exchange) {
            return exchange.getRequestURI().toString();
        }

        private String handlePostRequest(HttpExchange exchange) {
            return exchange.getRequestURI().toString();
        }

        private void handleResponse(HttpExchange exchange, String requestParamValue) throws IOException {
            OutputStream os = exchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();

            htmlBuilder.append("<h1>");
            htmlBuilder.append("Hello ");
            htmlBuilder.append(requestParamValue);
            htmlBuilder.append("</h1>");

            String htmlResponse = htmlBuilder.toString();

            exchange.sendResponseHeaders(200, htmlBuilder.length());

            os.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();
        }
    }
}