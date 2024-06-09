package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

    public String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                stringBuilder.append(fileLine).append(SPACE);
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] parts = dataFromFile.split(SPACE);
        for (String part : parts) {
            if (part.contains(SUPPLY)) {
                totalSupply += Integer.parseInt(part.replaceAll(SUPPLY + COMMA, ""));
            } else if (part.contains(BUY)) {
                totalBuy += Integer.parseInt(part.replaceAll(BUY + COMMA, ""));
            }
        }
        return SUPPLY + COMMA + totalSupply + NEW_LINE
                + BUY + COMMA + totalBuy + NEW_LINE
                + RESULT + COMMA + (totalSupply - totalBuy);
    }

    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
