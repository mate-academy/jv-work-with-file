package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFirstFile = reader(fromFileName).split(" ");
        int supply = 0;
        int buy = 0;
        for (String s : dataFromFirstFile) {
            String[] lineInfo = s.split(",");
            for (int i = 0; i < lineInfo.length - 1; i++) {
                if (lineInfo[i].equals("supply")) {
                    supply += Integer.parseInt(lineInfo[i + 1]);
                } else {
                    buy += Integer.parseInt((lineInfo[i + 1]));
                }
            }
        }
        String dataToWrite = new StringBuilder("supply,")
                .append(supply).append(System.lineSeparator())
                .append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,")
                .append(supply - buy).toString();
        writer(toFileName,dataToWrite);
    }

    private void writer(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into file", e);
        }
    }

    private String reader(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
