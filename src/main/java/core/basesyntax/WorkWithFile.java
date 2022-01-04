package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] toFile = new String[]{toFileName};
        File report = new File(toFileName);
        for (String result : toFile) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report, true))) {
                bufferedWriter.write(result);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
