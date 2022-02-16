package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFromFileName = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        //read file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFromFileName));
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("\"Can't read file\"", e);
        }

        //remake array to string and split
        String[] resultFootAge = stringBuilder.toString().split("\\W+");

        //create cycle for and write to file
        for (String words: resultFootAge) {
            File fileToFileName = new File(toFileName);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToFileName));
                bufferedWriter.write(words);
            } catch (IOException e) {
                throw new RuntimeException("\"Can't write to file\"", e);
            }
        }
    }
}
