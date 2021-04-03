package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATING_ELEMENT = ",|\\s+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileInformation = readFromFile(fromFileName);
        String[] fileContent = fileInformation.split(SEPARATING_ELEMENT);
        String dailyReport = getDailyReport(fileContent);
        writeToFile(toFileName, dailyReport);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readerValue = bufferedReader.readLine();
            while (readerValue != null) {
                builder.append(readerValue).append(System.lineSeparator());
                readerValue = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("File Name is Empty!" + fromFileName + " " + exception);
        }
        return builder.toString();
    }

    private String getDailyReport(String[] fileContent) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < fileContent.length - 1; i++) {
            if (fileContent[i].equals(SUPPLY)) {
                supply += Integer.parseInt(fileContent[i + 1]);
            } else if (fileContent[i].equals(BUY)) {
                buy += Integer.parseInt(fileContent[i + 1]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String dailyReport) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException exception) {
            throw new RuntimeException("Cant create file" + toFileName);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(dailyReport);
        } catch (IOException exception) {
            throw new RuntimeException("Cant write Report to File" + exception);
        }
    }
}
