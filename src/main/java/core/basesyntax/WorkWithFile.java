package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        if (!file.exists()) {
            String data = readFromFile(fromFileName);
            writeToFile(data, toFileName);
        }
    }

    private String readFromFile(String fileName) {
        int supplyNum = 0;
        int buyNum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }

            String[] dataArray = stringBuilder.toString().split(" ");
            for (String string : dataArray) {
                String[] arrayForNum = string.split(",");
                if (arrayForNum[0].equals(SUPPLY)) {
                    supplyNum += Integer.parseInt(arrayForNum[1]);
                } else {
                    buyNum += Integer.parseInt(arrayForNum[1]);
                }
            }
            return createReport(supplyNum, buyNum);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        int result;
        StringBuilder report = new StringBuilder();
        String separator = System.lineSeparator();
        result = supply - buy;
        report.append(SUPPLY).append(COMMA).append(supply).append(separator);
        report.append(BUY).append(COMMA).append(buy).append(separator);
        report.append(RESULT).append(COMMA).append(result);

        return report.toString();
    }

    private void writeToFile(String data, String resultFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + resultFile, e);
        }
    }
}
