package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final String SUPPLYWORDS = "supply";
    private static final String BUYWORDS = "buy";
    private static final String RESULTWORDS = "result";
    private int supply = 0;
    private int buy = 0;
    private int counter = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataInfo = readFile(fromFileName);
        String report = makeReport(dataInfo);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(REGEX);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String makeReport(String dataInfo) {
        String[] valueArr = dataInfo.split(REGEX);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < valueArr.length; i += 2) {
            if (valueArr[i].equals(SUPPLYWORDS)) {
                supply += Integer.parseInt(valueArr[counter]);
            } else {
                buy += Integer.parseInt(valueArr[counter]);
            }
            counter += 2;
        }
        stringBuilder.append(SUPPLYWORDS + REGEX).append(supply).append(System.lineSeparator())
                .append(BUYWORDS + REGEX).append(buy).append(System.lineSeparator())
                .append(RESULTWORDS + REGEX).append(supply - buy).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't writer file" + toFileName, e);
        }
    }
}


