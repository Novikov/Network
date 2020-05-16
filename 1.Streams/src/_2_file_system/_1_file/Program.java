package _2_file_system._1_file;

import java.io.File;

public class Program {
    public static void main(String[] args) {
        File file = new File("1.txt");

        //полезные методы
        file.canExecute(); //- выполняемый или нет
        file.canRead(); // - можно читать или нет
        file.canWrite(); // - можно писать или нет

        file.deleteOnExit();// - файл удалится когда программа будет завершаться
        file.exists();

//        File file = new File("1/2/3/4/5");
        file.mkdirs(); //если файл является дирректорией - можем создать дирректорию
        file.list();// если файл является дирректорией - можно отобразить список файлов

    }
}
