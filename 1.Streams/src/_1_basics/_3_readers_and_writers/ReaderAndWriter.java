package _1_basics._3_readers_and_writers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class ReaderAndWriter {
    public static void main(String[] args)throws Exception {
        Reader r = new FileReader("1.txt");
        Writer w = new FileWriter("2.txt");
        //Единственное что тут интересного - мы читаем символы в int и записываем как int.
        // Впоследствии они преобразуются в char.
        int c = r.read();
        while (c!=-1){
            w.write(c);
            c = r.read();
        }

        r.close();
        w.close();
    }
}
