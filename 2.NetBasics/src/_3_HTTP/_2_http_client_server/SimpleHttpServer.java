package _3_HTTP._2_http_client_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer
{

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 1000);
        //Context - это url на который мы бы хотели отвечать и некий объект который будет принимать этот запрос.
        //В зависомости от типа URL будет выбираться соответствующий обработчик
        server.createContext("/", new MyHandler()); //обработчик по умолчанию
        server.createContext("/test1", new MyHandler1());
        server.createContext("/test2", new MyHandler2());
        server.start();
    }

    static class MyHandler implements HttpHandler
    {
        public void handle(HttpExchange t) throws IOException {
            String response = "Welcome Real's HowTo test page";
            t.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class MyHandler1 implements HttpHandler
    {
        public void handle(HttpExchange t) throws IOException {
            String response = "Handler 1";
            t.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class MyHandler2 implements HttpHandler
    {
        public void handle(HttpExchange t) throws IOException {
            String response = "Handler 2";
            t.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
