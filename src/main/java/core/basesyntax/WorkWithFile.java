package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int WORD_INDEX = 0;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_VALUE = "result";

    public void getResultText(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        File fromFile = new File(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile));
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String resultList = makeDataList(bufferedReader);
            bufferedWriter.write(resultList);
        } catch (IOException e) {
            throw new RuntimeException("wrong file path");
        }
    }

    private String makeDataList(BufferedReader bufferedReader) throws IOException {
        StringBuilder list = new StringBuilder();
        String[][] information = getMapInformation(bufferedReader);
        for (int i = 0; i < information.length; i++) {
            if (i != 0) {
                list.append(System.lineSeparator());
            }
            list.append(information[i][WORD_INDEX]).append(",")
                    .append(information[i][VALUE_INDEX]);
        }
        return list.toString();
    }

    private String[][] getMapInformation(BufferedReader bufferedReader)
            throws IOException {
        String[][] statistic = new String[][]{{SUPPLY, "0"}, {BUY, "0"}, {RESULT_VALUE, "0"}};
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] splitLine = line.split(",");
            setInformation(statistic, splitLine[WORD_INDEX], splitLine[VALUE_INDEX]);
            line = bufferedReader.readLine();
        }
        setInformation(statistic, RESULT_VALUE, "0");
        return statistic;
    }

    private String countResultValue(String supply, String buy) {
        int numValue = Integer.parseInt(supply) - Integer.parseInt(buy);
        return Integer.toString(numValue);
    }

    private String countSumValue(String firstValue, String secondValue) {
        int numValue = Integer.parseInt(firstValue) + Integer.parseInt(secondValue);
        return Integer.toString(numValue);
    }

    private void setInformation(String[][] statistic,
                                String name, String value) {
        switch (name) {
            case SUPPLY:
                statistic[SUPPLY_INDEX][VALUE_INDEX]
                        = countSumValue(statistic[SUPPLY_INDEX][VALUE_INDEX], value);
                break;
            case BUY:
                statistic[BUY_INDEX][VALUE_INDEX]
                        = countSumValue(statistic[BUY_INDEX][VALUE_INDEX], value);
                break;
            case RESULT_VALUE:
                statistic[RESULT_INDEX][VALUE_INDEX]
                        = countResultValue(statistic[SUPPLY_INDEX][VALUE_INDEX],
                        statistic[BUY_INDEX][VALUE_INDEX]);
                break;
            default:
        }
    }
}
