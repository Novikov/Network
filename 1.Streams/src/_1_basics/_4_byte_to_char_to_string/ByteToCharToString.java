package _1_basics._4_byte_to_char_to_string;

import java.io.*;

public class ByteToCharToString {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("1.txt");
        //Reader который читает поток символов
        Reader fr = new InputStreamReader(fis,"utf-8");
        //Reader который читает поток символов, а на выходе выдает поток строк. Находит символ перевода строки и отдает строку.
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();

        while (line!=null){
            System.out.println(line);
            line = br.readLine();
        }

        br.close();
        fr.close();
        fis.close();

    }
}
