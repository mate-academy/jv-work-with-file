package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String NEW_LINER = System.lineSeparator();
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    private String[] readData(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                content.append(value).append(NEW_LINER);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString().split(NEW_LINER);
    }

    private String reportReady(String[] readData) {
        StringBuilder result = new StringBuilder();
        int supply = 0;
        int buy = 0;
        for (String s : readData) {
            String[] sup = s.split(COMMA);
            if (s.contains(SUPPLY)) {
                supply = supply + Integer.parseInt(sup[1]);
            }
            if (s.contains(BUY)) {
                buy = buy + Integer.parseInt(sup[1]);
            }
        }
        return result.append(SUPPLY).append(supply).append(NEW_LINER)
                .append(BUY).append(buy).append(NEW_LINER)
                .append(RESULT).append(supply - buy).append(NEW_LINER).toString();
    }

    public void writeToFile(String reportReady, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportReady);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(reportReady(readData(fromFileName)), toFileName);
    }
}
