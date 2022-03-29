package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file1 = new File(fromFileName);
        File file2 = new File(toFileName);
        try {
            file2.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cant create file",e);
        }
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));) {
            String string = bufferedReader.readLine();
            while (string != null) {
                if (string.contains("supply")) {
                    supply += Integer.parseInt(string.trim().substring(7));
                } else if (string.contains("buy")) {
                    buy += Integer.parseInt(string.trim().substring(4));
                }
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2, true))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
    }
}
