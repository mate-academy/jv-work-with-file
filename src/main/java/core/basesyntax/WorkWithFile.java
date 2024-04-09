package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String [] dataFromFile = value.split(",");
                if (dataFromFile[0].equals("supply")) {
                    supply = supply + Integer.parseInt(dataFromFile[1]);
                }
                if (dataFromFile[0].equals("buy")) {
                    buy = buy + Integer.parseInt(dataFromFile[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        writeToFile(supply, buy, toFileName);
    }

    public void writeToFile(int supply, int buy, String toFileName) {
        File file = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() == 0) {
                builder.append("supply,").append(supply).append(System.lineSeparator())
                        .append("buy,").append(buy).append(System.lineSeparator())
                        .append("result,").append(supply - buy);
                bufferedWriter.write(builder.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
