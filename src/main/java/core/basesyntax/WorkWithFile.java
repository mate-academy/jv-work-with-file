package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatistic(toFileName, getOperationType(readStatistic(fromFileName)));
    }

    public String[] readStatistic(String fromFileName) {
        File fileReade = new File(fromFileName);
        StringBuilder stringBuilder;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReade))) {
            stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        String[] allOperation = stringBuilder.toString().split(System.lineSeparator());
        return allOperation;
    }

    public String[] getOperationType(String[] allOperation) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        for (String operation : allOperation) {
            String[] oneOperation = operation.split(",");
            if (oneOperation[OPERATION_TYPE_INDEX].equals(OPERATION_TYPE_BUY)) {
                buy += Integer.parseInt(oneOperation[AMOUNT_INDEX]);
            } else if (oneOperation[OPERATION_TYPE_INDEX].equals(OPERATION_TYPE_SUPPLY)) {
                supply += Integer.parseInt(oneOperation[AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        String[] resultData = new String[]{"supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result};
        return resultData;
    }

    public void writeStatistic(String toFileName, String[] resultData) {

        File fileWrite = new File(toFileName);
        for (String data : resultData) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(fileWrite, true))) {
                bufferedWriter.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file " + toFileName, e);
            }
        }
    }
}
