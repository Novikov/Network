package _2_UDP._2_multi_udp;



import _2_UDP._1_simple_udp.QuoteServerThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

//рассылка сообщения множеству клиентов
//тут происходит наследование от QuoteServerThread
//в коментах могут быть ошибки. Не всё понял из лекции. При использовании пересмотреть - https://www.youtube.com/watch?v=0T-4IfQYQeg&list=PLyxk-1FCKqof7mjIJp6mcsu-o_UcbvjKY&index=17
public class MulticastServerThread extends QuoteServerThread
{
    private long FIVE_SECONDS = 5000;

    public MulticastServerThread() throws IOException {
    }

    @Override
    public void run() {
        while (moreQuotes) {
            try {
                String dString;
                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }
                byte[] buf = dString.getBytes();

                //сюда можно указывать диапазон адресов для групп рассылки. его будут принимать те, кто подпишется на этот адрес
                InetAddress group = InetAddress.getByName("225.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                System.out.println("Sent:" + dString); //с приставкой sent означает, что цитата отправлена в пустоту и ее никто не принял

                try {
                    sleep((long) (Math.random() * FIVE_SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
                moreQuotes = false;
            }
        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        new MulticastServerThread().start();
    }
}
