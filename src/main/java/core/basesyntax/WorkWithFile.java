package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "\\W+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SPACE = " ";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String reportContent = reportStatistic(fileContent);
        writeToFile(reportContent, toFileName);
    }

    public String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + file, e);
        }
        return stringBuilder.toString();
    }

    public String reportStatistic(String fileContent) {
        String[] content = fileContent.split(REGEX);
        int sumSupply = 0;
        int sumBuy = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i].equals(SUPPLY)) {
                sumSupply = sumSupply + Integer.parseInt(content[i + 1]);
            }
            if (content[i].equals(BUY)) {
                sumBuy = sumBuy + Integer.parseInt(content[i + 1]);
            }
        }
        int result = sumSupply - sumBuy;
        return SUPPLY + COMMA + sumSupply + SPACE + BUY + COMMA
                + sumBuy + SPACE + RESULT + COMMA + result;
    }

    public void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            String[] reportArray = report.split(SPACE);
            for (String element : reportArray) {
                bufferedWriter.write(element + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + file, e);
        }
    }
}
