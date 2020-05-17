package _1_TCP._2_tcp_client_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//Пример однопоточного сервера. Функция accept() блокирующая и может работать только с одним потоком.
public class SimpleServerSocket
{
    public static void main(String[] args) throws Exception {
        //Реализация собственного WEB сервера
        int port = 19999; //Наша программа будет занимать этот порт, если он не занят другой програмой
        System.out.println("Try to bind to port:" + port);

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server socket is opened");
        while(true) {
            Socket socket = server.accept(); //Начать слушать порт. Если сигнал найден, то мы получим эеземпляр сокета клиента
            System.out.println("Connection is accepted");

            //Дальше начинаем работать как с обычным соккетом
            //Создаем выходной поток в который можно писать символы. Параметр autoFlush говорит о том, что если мы что то записали
            //в поток, то не нужно ничего буферизовать и сразу отправлять.
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            //Создаем входной поток для соккета и читаем из него
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                //если в строке есть строка bye - то это сигнал хакрыть соединение. Мы ему так же отправляем bye в качетсве ответа
                if (str.equals("bye")) {
                    pw.println("bye");
                    break;
                } else {
                    pw.println("Server is answering:" + str);
                }
            }
            Thread.sleep(1000);
            pw.close();
            br.close();
            socket.close();
        }
    }
}
