package _2_UDP._2_multi_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient
{

    public static void main(String[] args) throws IOException {
      //хардкод. использование ipv4 т.к другой у него не работал
        System.setProperty("java.net.preferIPv4Stack", "true");
        //необычный соккет.Читает только те пакеты, которые пакеты которые выходят на этот соккет
        try (MulticastSocket socket = new MulticastSocket(4446)) {
            //подключение к группе
            InetAddress address = InetAddress.getByName("225.0.0.1");
            socket.joinGroup(address);

            //прием 5 пакетов
            for (int i = 0; i < 5; i++) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);//блокирующий метод, sent не блокирующий
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Quote of the Moment: " + received); //с этой приставкой означает, что цитату принял клиент
            }

            socket.leaveGroup(address);
        }
    }

}
