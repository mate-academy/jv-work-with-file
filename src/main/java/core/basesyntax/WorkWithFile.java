package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File writeFile = new File(toFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            String stringFromFile = stringBuilder.toString();
            String[] parts = stringFromFile.split("\n");
            for (String entry : parts) {
                String[] divideParts = entry.split(",");
                if (divideParts[0].equals("supply")) {
                    supply = supply + Integer.parseInt(divideParts[1]);
                } else {
                    buy = buy + Integer.parseInt(divideParts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        int resultInt = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(resultInt);
        String result = report.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile, false))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
