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
        int supply = 0;
        int buy = 0;

        //1st part: reading from file
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

        //2nd part: making report
        int result = supply - buy;

        //3d part: create file and write to it
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2, true))) {
            file2.createNewFile();
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Some problems", e);
        }
    }
}
