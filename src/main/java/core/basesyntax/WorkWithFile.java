package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_AS_STRING = "supply";
    private static final String BUY_AS_STRING = "buy";
    private static final String RESULT_AS_STRING = "result";
    private static final String IDENTIFIER_REGEX = "(\\w+)";
    private static final String EMPTY_STRING = "";
    private static final String IDENTIFIER_SEPARATOR = ",";
    private static final int IDENTIFIER_START_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFileName = getDataFromFile(fromFileName);
        String dataToFile = convertInputDataInOutputData(dataFromFileName);
        setDataToFile(toFileName, dataToFile);
    }

    private String getDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file" + e);
        } catch (IOException e) {
            throw new RuntimeException("Error in file reading" + e);
        }
        return stringBuilder.toString();
    }

    private String convertInputDataInOutputData(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] splitWords = dataFromFile.split(System.lineSeparator());
        String operation;
        for (String s : splitWords) {
            operation = s.substring(IDENTIFIER_START_INDEX, s.indexOf(IDENTIFIER_SEPARATOR));
            int extractedAmount = Integer.parseInt(s.replaceFirst(IDENTIFIER_REGEX
                    + IDENTIFIER_SEPARATOR, EMPTY_STRING));
            switch (operation) {
                case SUPPLY_AS_STRING:
                    supply += extractedAmount;
                    break;
                case BUY_AS_STRING:
                    buy += extractedAmount;
                    break;
                default:
                    throw new RuntimeException("Invalid data in .csv file");
            }
        }
        return parseOutputData(supply, buy);
    }

    private String parseOutputData(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_AS_STRING)
                .append(IDENTIFIER_SEPARATOR)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY_AS_STRING)
                .append(IDENTIFIER_SEPARATOR)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT_AS_STRING)
                .append(IDENTIFIER_SEPARATOR)
                .append(supply - buy)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void setDataToFile(String toFileName, String dataToFile) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(dataToFile);
        } catch (IOException e) {
            throw new RuntimeException("Exception in file writing" + e);
        }
    }
}
