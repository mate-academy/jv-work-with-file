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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder builder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
            String wordWithoutEnter = builder.toString().replaceAll("\n", ",");
            String[] splitArray = wordWithoutEnter.split(",");
            for (int i = 0; i < splitArray.length; i++) {
                if (splitArray[i].equals("supply")) {
                    supply += Integer.parseInt(splitArray[i + 1]);
                } else if (splitArray[i].equals("buy")) {
                    buy += Integer.parseInt(splitArray[i + 1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    public void createReport(String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append("\n").append("buy,").append(buy)
                .append("\n").append("result,").append(supply - buy);
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
