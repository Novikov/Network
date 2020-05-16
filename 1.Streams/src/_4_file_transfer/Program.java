package _4_file_transfer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Program {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("1.txt");
        FileOutputStream fos = new FileOutputStream("10.txt");
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        inChannel.transferTo(0,inChannel.size(),outChannel);//С какой позиции начать, сколько, в какой канал отправить
        //Очень быстрая передача данных

        //обратная операция
//        outChannel.transferFrom(inChannel,0,inChannel.size());

        fis.close();
        fos.close();
    }
}
