package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String DATA_SEPARATOR = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getReadFromFile(fromFileName);
        String report = generateReport(dataFromFile);
        getWriteToFile(report, toFileName);
    }

    private String getReadFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(DATA_SEPARATOR);
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file:" + fromFileName, e);
        }
    }

    private String generateReport(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] dataSplit = data.split(DATA_SEPARATOR);
        int supply = 0;
        int buy = 0;
        int result = 0;

        for (String word : dataSplit) {
            String[] arrayDataSplit = word.split(",");
            if (arrayDataSplit[ACTION_INDEX].equals("supply")) {
                supply = supply + Integer.parseInt(arrayDataSplit[AMOUNT_INDEX]);
            } else {
                buy = buy + Integer.parseInt(arrayDataSplit[AMOUNT_INDEX]);
            }
            result = supply - buy;
        }
        return stringBuilder.append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,")
                .append(result).toString();
    }

    private void getWriteToFile(String getReportToFile, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(getReportToFile);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file:" + toFileName, e);

        }
    }
}
