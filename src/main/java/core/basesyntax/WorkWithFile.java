package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String COMMA = ",";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fileRead = new File(fromFileName);
        File fileWrite = new File(toFileName);
        StringBuilder data = new StringBuilder();
        StringBuilder dataToWrite = new StringBuilder();
        fileWrite.getAbsoluteFile().delete();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead))) {
            String value = reader.readLine();
            while (value != null) {
                data.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read from file");
        }

        String[] dataArray = data.toString().split(System.lineSeparator());

        dataToWrite.append(SUPPLY)
                .append(COMMA)
                .append(getTotalAmount(dataArray, SUPPLY))
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(getTotalAmount(dataArray, BUY))
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(getTotalAmount(dataArray, SUPPLY) - getTotalAmount(dataArray, BUY));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite, true))) {
            bufferedWriter.write(dataToWrite.toString());
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file");
        }

    }

    private int getTotalAmount(String[] data, String operationType) {
        int amount = 0;
        for (int i = 0; i < data.length; i++) {
            if (getOperationType(data[i]).equals(operationType)) {
                amount += getAmount(data[i]);
            }
        }
        return amount;
    }

    private String getOperationType(String oneRecord) {
        return oneRecord.split(COMMA)[0];
    }

    private int getAmount(String oneRecord) {
        return Integer.parseInt(oneRecord.split(COMMA)[1]);
    }
}
