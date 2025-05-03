package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String textFrom1 = "supply,30\n"
                + "buy,10\n"
                + "buy,13\n"
                + "supply,17\n"
                + "buy,10\n"
                + "supply,5\n"
                + "supply,80\n"
                + "buy,39\n"
                + "supply,56\n"
                + "buy,32\n"
                + "buy,11\n";
        String textFrom2 = "buy,10\n"
                + "supply,300\n"
                + "buy,130\n"
                + "buy,12\n"
                + "supply,20\n"
                + "buy,1\n"
                + "supply,50\n"
                + "supply,12\n"
                + "supply,8\n"
                + "buy,41\n"
                + "supply,10\n"
                + "buy,12\n"
                + "buy,10\n"
                + "buy,77\n"
                + "supply,91";
        File directory = new File("Directory1");
        directory.mkdir();

        String filePathFileFrom = "Directory1" + File.separator + "fileFrom.csv";
        String filePathFileTo = "Directory1" + File.separator + "fileTo.csv";
        File fileFrom = new File(filePathFileFrom);
        File fileTo = new File(filePathFileTo);
        BufferedWriter writer = null;
        try {
            fileFrom.createNewFile();
            fileTo.createNewFile();
            writer = new BufferedWriter(new FileWriter(fileFrom));
            writer.write(textFrom1);
        } catch (IOException e) {
            throw new RuntimeException("File was not created", e);
        } finally {
            if (writer != null) {

                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferWriter", e);
                }
            }
        }
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(filePathFileFrom, filePathFileTo);
    }
}
