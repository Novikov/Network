package _3_nio._1_channel_read_example;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Program {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("1.txt");
        FileChannel channel = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(4096);

        //Прочить канал и забить буфер. Сейчас буфер находится в режиме записи
        int r = channel.read(bb);

        while (r !=-1){
            bb.flip();//Переключить буфер в режим чтения. Переместить начальную позицию в начало.
            //пока в буфере что то есть мы можем из него считать.
            while(bb.hasRemaining()){
                 bb.get();
            }
            //очистить буфер
            bb.clear();
            //продолжить чтение канала в уже очищенный буфер
            r = channel.read(bb);
        }
        fis.close();
    }
}
