package _3_nio._2_channel_read_and_write_example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Program {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("1.txt");
        FileOutputStream fos = new FileOutputStream("2.txt");
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        ByteBuffer inBb = ByteBuffer.allocate(4096);
        ByteBuffer outBb = ByteBuffer.allocate(4096);

        int r = inChannel.read(inBb);

        while (r !=-1){
            inBb.flip();
            while(inBb.hasRemaining()){
                byte get = inBb.get();
                outBb.put(get);
            }
            outBb.flip();//чтобы канал мог прочитать
            outChannel.write(outBb);
            inBb.clear();
            outBb.clear();
            r = inChannel.read(inBb);
        }
        fis.close();
        fos.close();
    }
}
