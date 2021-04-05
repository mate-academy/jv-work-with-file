package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();

            int supply = 0;
            int buy = 0;
            String[] readLine;

            while (value != null) {
                readLine = value.split(",");
                if (readLine[0].equals("supply")) {
                    supply += Integer.parseInt(readLine[1]);
                } else {
                    buy += Integer.parseInt(readLine[1]);
                }
                value = bufferedReader.readLine();
            }
            writeToFile(createReport(toFileName), supply, buy);
        } catch (IOException e) {
            throw new RuntimeException("File does not exist", e);
        }
    }

    private File createReport(String newFile) {
        return new File(newFile);
    }

    private void writeToFile(File file, int supply, int buy) {
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(result);
            String finalReport = String.format("%10s", stringBuilder);
            bufferedWriter.write(finalReport);
        } catch (IOException e) {
            throw new RuntimeException("File does not exist", e);
        }
    }
}
