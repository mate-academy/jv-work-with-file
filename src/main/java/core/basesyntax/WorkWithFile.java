package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, calculateResult(readFile(fromFileName)));
    }

    private String readFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file.", e);
        }
    }

    private String calculateResult(String value) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] data = value.split(",");
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supply += Integer.parseInt(data[i + 1]);
            } else if (data[i].equals("buy")) {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        return stringBuilder.append("supply").append(",").append(supply)
                .append(System.lineSeparator()).append("buy").append(",").append(buy)
                .append(System.lineSeparator()).append("result").append(",")
                .append(supply - buy).toString();
    }

    private void writeFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data file", e);
        }
    }
}
