package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeData(toFileName, readFile(fromFileName));
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String[] readFromFile = stringBuilder.toString().split(",");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < readFromFile.length; i++) {
            if (readFromFile[i].equals("supply")) {
                supply += Integer.parseInt(readFromFile[i + 1]);
            } else if (readFromFile[i].equals("buy")) {
                buy += Integer.parseInt(readFromFile[i + 1]);
            }
        }

        StringBuilder report = new StringBuilder();
        return report.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(supply - buy).toString();
    }

    private void writeData(String filename, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(String.valueOf(data));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }
}
