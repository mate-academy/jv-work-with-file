package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public String getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] strings = value.split(",");
                if (strings[0].equals("buy")) {
                    buy = buy + Integer.parseInt(strings[1]);
                } else {
                    supply = supply + Integer.parseInt(strings[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file - " + fromFileName, e);
        }
        StringBuilder result = new StringBuilder().append("supply, ").append(supply)
                .append(System.lineSeparator()).append("buy, ").append(buy)
                .append(System.lineSeparator()).append("result, ").append(supply - buy);
        return result.toString();
    }

    private void printInFile(String toFileName, String result) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            fileToWrite.createNewFile();
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file - " + toFileName, e);
        }
    }
}
