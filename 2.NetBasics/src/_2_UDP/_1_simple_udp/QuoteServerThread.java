package _2_UDP._1_simple_udp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

//сервер принимает сообщение и в ответ посылает цитату
public class QuoteServerThread extends Thread
{
    protected DatagramSocket socket;
    protected BufferedReader in;
    protected boolean moreQuotes = true;

    public QuoteServerThread() throws IOException {
        //открываем DataGramSocket и слушаем его. Он принимает пакеты, а не поток байт.
        socket = new DatagramSocket(4445);
        try {
            //открываем файл с цитатами
            in = new BufferedReader(new FileReader("one-liners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
    }

    @Override
    public void run() {
        //Пока в файле есть цитаты
        while (moreQuotes) {
            try {
                //буфер для пакетов
                byte[] buf = new byte[256];
                //принимает в пакет сообщение
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String dString;
                //если не получилось открыть файл на чтение - то будем отправлять дату.
                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }

                buf = dString.getBytes();

                //отправка пакета
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                System.out.println("Port:" + port);
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        new QuoteServerThread().start();
    }
}
