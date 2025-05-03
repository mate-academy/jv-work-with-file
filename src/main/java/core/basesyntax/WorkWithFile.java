package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String inputString = new String();
        String [] strArray = readData(fromFileName)
                .split(",");
        writeData(toFileName, strArray);
    }

    private String readData(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().replace(System.lineSeparator(), ",");
    }

    private String generateStatistic(String[] infoFromFile) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (int i = 0; i < infoFromFile.length; i++) {
            if (infoFromFile[i].equals("buy")) {
                buy += Integer.parseInt(infoFromFile[i + 1]);
            } else if (infoFromFile[i].equals("supply")) {
                supply += Integer.parseInt(infoFromFile[i + 1]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeData(String toFile, String[] data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, false))) {
            bufferedWriter.write(generateStatistic(data));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
