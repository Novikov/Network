package _1_basics._5_memory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Memory{
    public static void main(String[] args) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[10]);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String text = "Hello Word";
        byte[] buffer = text.getBytes();

        baos.write(buffer);

        //выводим выходной поток в виде строки
        System.out.println(baos.toString());
        System.out.println();

        //переводим вывод в массив байт и выводим строку в виде байтов
        byte [] array = baos.toByteArray();
        for (byte b:array){
            System.out.print(b);
        }
    }
}
