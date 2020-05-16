package _1_basics._1_copy_bite;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//Копирование файла по одному байту. Очень дорогая операция. Частое обращение к диску.
public class CopyByte {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("1.jpeg");
        OutputStream os = new FileOutputStream("2.jpeg");

        //Данная функция читайет байты из потока, но возвращает число int,
        // которое равняется числу прочитанных byte в виде 5 0 20 70 -1
        //Мы ее используем как ключ, в цикле. Когда данная функция вернет -1 это будет означать, что мы достигли конца файла.
        int r = is.read();

        System.out.println(r);

        while (r != -1){
            os.write(r);
            r = is.read();
            System.out.println(r);
        }

        is.close();
        os.close();
    }
}