package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String resultRead = readFrom(fromFileName);

        String[] lines = resultRead.split(System.lineSeparator());
        for (String line : lines) {
            String[] arraySplit = line.split(",");
            if (arraySplit[0].equals("supply")) {
                supply += Integer.parseInt(arraySplit[1]);
            } else {
                buy += Integer.parseInt(arraySplit[1]);
            }
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("supply").append(",").append(supply).append(System.lineSeparator())
                    .append("buy").append(",").append(buy).append(System.lineSeparator())
                    .append("result").append(",").append(supply - buy)
                    .append(System.lineSeparator());
            String reportStatistic = resultBuilder.toString();
            writeToFile(toFileName, reportStatistic);
        }
    }

    private String readFrom(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder readBuilder = new StringBuilder();
        try (BufferedReader readReader = new BufferedReader(new FileReader(file))) {
            String read = readReader.readLine();
            while (read != null) {
                readBuilder.append(read).append(System.lineSeparator());
                read = readReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return readBuilder.toString();
    }

    private void writeToFile(String toFileName, String reportStatistic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportStatistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
