package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int DATA_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String contentFromFile = readFile(file);
        int totalSupply = getDataFrom(SUPPLY, contentFromFile);
        int totalBuy = getDataFrom(BUY, contentFromFile);
        String report = createReport(totalSupply, totalBuy);
        writeReportToFile(report, toFileName);
    }

    private void writeReportToFile(String report, String newFileName) {
        File file = new File(newFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("cant write to file, invalid file name");
        }
    }

    private String createReport(int supply, int buy) {
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cant read file, invalid name");
        }
        return stringBuilder.toString().trim();
    }

    private int getDataFrom(String requestedData, String fileContent) {
        int result = 0;
        String[] splitLine;
        String[] splitContentOfFile = fileContent.split(System.lineSeparator());
        for (String line : splitContentOfFile) {
            splitLine = line.split(",");
            if (splitLine[DATA_INDEX].equals(requestedData)) {
                result += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        return result;
    }
}
