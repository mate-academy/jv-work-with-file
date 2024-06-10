package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                stringBuilder.append(fileLine).append(SPACE);
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] parts = dataFromFile.split(SPACE);
        for (String part : parts) {
            if (part.contains(SUPPLY)) {
                totalSupply += Integer.parseInt(part.replaceAll(SUPPLY + COMMA, ""));
            } else if (part.contains(BUY)) {
                totalBuy += Integer.parseInt(part.replaceAll(BUY + COMMA, ""));
            }
        }
        stringBuilder.append(SUPPLY).append(COMMA).append(totalSupply)
                .append(NEW_LINE).append(BUY).append(COMMA).append(totalBuy)
                .append(NEW_LINE).append(RESULT).append(COMMA)
                .append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
