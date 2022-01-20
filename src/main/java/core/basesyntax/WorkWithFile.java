package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class WorkWithFile {
    void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private void writeToFile(String toFileName, String arrayToWrite) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(arrayToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file " + e);
        }
    }

    private String readFromFile(String fromFileName) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        int[] counts = new int[2];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] currentLine = line.split(",");
                if (currentLine[0].equals("supply")) {
                    counts[0] += Integer.parseInt(currentLine[1]);
                } else if (currentLine[0].equals("buy")) {
                    counts[1] += Integer.parseInt(currentLine[1]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + e);
        }
        stringBuilder.append("supply,").append(counts[0])
                .append(System.lineSeparator());
        stringBuilder.append("buy,").append(counts[1])
                .append(System.lineSeparator());
        stringBuilder.append("result").append(",")
                .append(counts[0] - counts[1])
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
