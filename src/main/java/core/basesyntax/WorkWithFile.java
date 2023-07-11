package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class WorkWithFile {
    public static final String CAN_T_READ_FILE = "Can't read file";
    public static final String CAN_T_WRITE_DATA_TO_FILE = "Can't write data to file";
    public static final String SUPPLY_ACTION = "supply";
    public static final String BUY_ACTION = "buy";
    public static final String RESULT_ACTION = "result";
    public static final String COMA_SEPARATOR = ",";
    public static final int INDEX_SUPPLY_ACTION = 0;
    public static final int INDEX_ACTION_SUM = 1;
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = readFromFile(fromFileName);

        String[] actionList = stringFromFile.split(System.lineSeparator());

        String statisticString = getStatisticString(actionList);

        writeStatisticToFile(statisticString, toFileName);
    }

    private void writeStatisticToFile(String statisticString, String toFileName) {
        try {
            Files.write(Path.of(toFileName), statisticString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(CAN_T_WRITE_DATA_TO_FILE, e);
        }
    }

    private String getStatisticString(String[] actionList) {
        stringBuilder = new StringBuilder();

        int supplySum = 0;
        int buySum = 0;
        int result = 0;

        for (String actionLine : actionList) {
            String[] action = actionLine.split(COMA_SEPARATOR);
            System.out.println(Arrays.toString(action));
            if (action[INDEX_SUPPLY_ACTION].equals(SUPPLY_ACTION)) {
                supplySum += Integer.parseInt(action[INDEX_ACTION_SUM]);
            } else {
                buySum += Integer.parseInt(action[INDEX_ACTION_SUM]);
            }
        }
        result = supplySum - buySum;

        stringBuilder.append(SUPPLY_ACTION)
                .append(COMA_SEPARATOR)
                .append(supplySum)
                .append(System.lineSeparator());
        stringBuilder.append(BUY_ACTION)
                .append(COMA_SEPARATOR)
                .append(buySum)
                .append(System.lineSeparator());
        stringBuilder.append(RESULT_ACTION)
                .append(COMA_SEPARATOR)
                .append(result);

        return stringBuilder.toString();
    }

    private String readFromFile(String fromFileName) {
        stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(CAN_T_READ_FILE, e);
        }
        return stringBuilder.toString();
    }
}
