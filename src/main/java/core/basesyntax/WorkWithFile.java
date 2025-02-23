package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_EXPRESSION = "\\R|,";
    private static final String SUPPLY_KEYWORD = "supply";
    private static final String BUY_KEYWORD = "buy";
    private static final String RESULT_KEYWORD = "result";
    private static final String COMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFromFile(fromFileName);
        String report = createReport(infoFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String resultString;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            resultString = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file by path: " + fromFileName
                    + COMA_SEPARATOR + " " + e.getMessage());
        }
        return resultString;
    }

    private void writeToFile(String report, String toFileName) {

        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(report);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName
                    + COMA_SEPARATOR + " " + e.getMessage());
        }
    }

    private String createReport(String resultString) {
        String[] values = resultString.split(SPLIT_EXPRESSION);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].startsWith(SUPPLY_KEYWORD)) {
                supply += Integer.parseInt(values[i + 1]);
            }
            if (values[i].startsWith(BUY_KEYWORD)) {
                buy += Integer.parseInt(values[i + 1]);
            }
        }
        int result = supply - buy;
        return SUPPLY_KEYWORD + COMA_SEPARATOR + supply + System.lineSeparator()
                + BUY_KEYWORD + COMA_SEPARATOR + buy + System.lineSeparator()
                + RESULT_KEYWORD + COMA_SEPARATOR + result + System.lineSeparator();
    }
}
