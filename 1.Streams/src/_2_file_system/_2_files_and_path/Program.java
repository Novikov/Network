package _2_file_system._2_files_and_path;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) throws Exception {
        Path p = Paths.get(".","1.txt"); //путь до объект операционной системы, относитеьно текущей дирректории
        System.out.println(p);

//        Files. У данного класса куча методов

        //например как получить все строки из файла
        Stream<String> lines = Files.lines(p);
        lines.forEach((s)-> System.out.println(s));
    }
}
