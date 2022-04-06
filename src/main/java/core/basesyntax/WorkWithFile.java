package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(calculateStatistic(readFrom(fromFileName)), toFileName);
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
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return readBuilder.toString();
    }

    private String[] calculateStatistic(String read) {
        int supply = 0;
        int buy = 0;
        String[] lines = read.split(System.lineSeparator());
        for (String line : lines) {
            String[] arraySplit = line.split(",");
            if (arraySplit[0].equals("supply")) {
                supply += Integer.parseInt(arraySplit[1]);
            } else {
                buy += Integer.parseInt(arraySplit[1]);
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        return new String[]{resultBuilder.append("supply").append(",").append(supply)
                .toString(),
                resultBuilder.append(System.lineSeparator()).append("buy").append(",").append(buy)
                        .toString(),
                resultBuilder.append(System.lineSeparator()).append("result").append(",")
                        .append(supply - buy).toString()};
    }

    private void writeToFile(String[] reportStatistic, String toFileName) {
        for (String reportStatistics : reportStatistic) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(reportStatistics);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file " + toFileName, e);
            }
        }
    }
}
