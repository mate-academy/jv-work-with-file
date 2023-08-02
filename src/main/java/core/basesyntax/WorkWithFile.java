package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File writeToFile = new File(toFileName);
        File writeFromFile = new File(fromFileName);
        try {
            writeToFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(writeFromFile))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeToFile))) {
                String[] sumOfTypes = getSumOpType(reader).split(" ");
                String[] types = new String[]{"supply", "buy", "result"};
                writer.write(getDataForFile(sumOfTypes,types));
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file", e);
        }
    }

    private String getDataForFile(String[] sumOpTypes, String[] types) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String typeValue: sumOpTypes) {
            builder.append(System.lineSeparator())
                    .append(types[i])
                    .append(",")
                    .append(typeValue);
            i++;
        }

        return builder.toString();
    }

    private String getSumOpType(BufferedReader reader) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        try (BufferedReader bufferedReader = reader) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                String[] data = dataLine.split("[, ]");
                if (dataLine.contains("buy")) {
                    buy += Integer.parseInt(data[1]);
                } else {
                    supply += Integer.parseInt(data[1]);
                }

                dataLine = bufferedReader.readLine();
            }
            result = supply - buy;

            return supply + " " + buy + " " + result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
