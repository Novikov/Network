package _1_basics_io._2_coppy_array_bytes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//Копирование файла c помощью массива байт - ускоряет эту операцию.
//функция read() так же возвращает число прочитанных байт в виде -  4096б 4096 3831 -1.
public class CopyArrayBytes {
    public static void main(String[] args){
        //Try с инициализацией ресурсов в скобках. Все ресурсы автоматически закрываются при выходе из try.
        //Причем не важно словили мы исключение или нет.
        try (InputStream is = new FileInputStream("1.jpeg");
             OutputStream os = new FileOutputStream("3.jpeg",true);) {
            byte [] buffer = new byte[4096];

            int r = is.read(buffer);
            System.out.println(r);
            while (r != -1){
                //os.write(r); данный метод не сработает т.к он пишет массив байт целиком, а он может быть заполненным не до конца.
                // Поэтому нужно указывать конечную позицию
                os.write(buffer,0,r);
                r = is.read(buffer);
                System.out.println(r);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}