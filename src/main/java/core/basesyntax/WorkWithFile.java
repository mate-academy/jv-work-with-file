package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName).split(System.lineSeparator());

        this.writeFile(toFileName, this.createReport(lines));
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilderInput = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilderInput.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (stringBuilderInput.length() == 0) {
            throw new RuntimeException("File is empty");
        }
        return stringBuilderInput.toString();
    }

    private void writeFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("The specified file does not exist");
        }
    }

    private String createReport(String[] lines) {
        int supply = 0;
        int buy = 0;

        for (String stringLine : lines) {
            String[] parsingLine = stringLine.split(SEPARATOR);
            int amount = Integer.parseInt(parsingLine[AMOUNT_INDEX]);
            switch (parsingLine[OPERATION_TYPE_INDEX]) {
                case SUPPLY:
                    supply += amount;
                    break;
                case BUY:
                    buy += amount;
                    break;
                default:
                    throw new RuntimeException("This type of operation is not supported");
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supply - buy)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
