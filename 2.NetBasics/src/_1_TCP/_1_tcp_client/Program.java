package _1_TCP._1_tcp_client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Program {
    public static void main(String[] args) throws Exception {
        //Соккет принимает в конструктор название компьюетра и соккет, на который мы хотим отправить.
        Socket socket = new Socket("java-course.ru", 80);
        socket.setSoTimeout(2000);

        //Создание строки с подключением по HTTP. Команда для отправки на сервер.
        //Тут дополнительные настройки по общению с сервером.
        //Важно понимать что мы работаем с TCP протоколом, а не HTTP. Чел просто знает команды для таких запросов.
        StringBuilder command = new StringBuilder("GET /haiku.html HTTP/1.1");
        command.append(System.lineSeparator());
        command.append("Host: java-course.ru").append(System.lineSeparator());
        command.append("Connection: close").append(System.lineSeparator());
        command.append(System.lineSeparator());

        //Распечатаем запрос
        System.out.println("Request \n"+command.toString());

        //Socket это входной поток для отправки нашего запроса.
        //Выходной поток это наш запрос. При приеме ответа всё меняется местами.
        OutputStream os = socket.getOutputStream();
        os.write(command.toString().getBytes());//Строка запроса преобразуется в поток байт.

        //Создание потока для приема ответа от сервера
        InputStream is = socket.getInputStream();
        //Преобразование потока байтов в поток символов и отправка в BufferedReader для построчного чтения
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "cp1251"));
        //Теперь у нас есть класс с которого мы можем читать строки. Мы их читаем и печатаем.
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            try {
                line = br.readLine();
            } catch (SocketTimeoutException ex) {
                ex.printStackTrace(System.out);
                break;
            }
        }
        os.close();
        br.close();
        socket.close();
    }
}
