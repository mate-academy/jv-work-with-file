package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int WORD_INDEX = 0;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_VALUE = "result";
    private static final String ZERO_VAR = "0";

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readText(fromFileName);
        writeText(toFileName, statistic);
    }

    private void writeText(String toFileName, String statistic) {
        try {
            Files.writeString(new File(toFileName).toPath(), statistic);
        } catch (IOException e) {
            throw new RuntimeException("can't write to file: " + toFileName, e);
        }
    }

    private String readText(String fromFileName) {
        List<String> list;
        try {
            list = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("wrong file path: " + fromFileName, e);
        }
        return getResultText(list);
    }

    private String getResultText(List<String> list) {
        StringBuilder stringList = new StringBuilder();
        String[][] information = getMapInformation(list);
        for (int i = 0; i < information.length; i++) {
            if (i != 0) {
                stringList.append(System.lineSeparator());
            }
            stringList.append(information[i][WORD_INDEX]).append(",")
                    .append(information[i][VALUE_INDEX]);
        }
        return stringList.toString();
    }

    private String[][] getMapInformation(List<String> list) {
        String[][] statistic = new String[][]{{SUPPLY, ZERO_VAR}, {BUY, ZERO_VAR},
                {RESULT_VALUE, ZERO_VAR}};
        for (String s : list) {
            String[] splitLine = s.split(",");
            setInformation(statistic, splitLine[WORD_INDEX], splitLine[VALUE_INDEX]);
        }
        setInformation(statistic, RESULT_VALUE, ZERO_VAR);
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
