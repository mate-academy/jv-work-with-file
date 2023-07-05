package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATE_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readFile = readFile(fromFileName);
        String preparedData = processData(readFile);
        writeToFile(preparedData, toFileName);
    }

    public String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(SEPARATE_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException("File " + fromFileName + " not found", e);
        }
        return stringBuilder.toString();
    }

    public String processData(String readFile) {
        String[] splittedData = readFile.split(SEPARATE_LINE);
        int supply = 0;
        int buy = 0;
        for (String data : splittedData) {
            String[] separatedData = data.split(COMMA);
            if (separatedData[0].equals(SUPPLY)) {
                supply += Integer.parseInt(separatedData[1]);
            } else {
                buy += Integer.parseInt(separatedData[1]);
            }
        }
        return prepareData(supply, buy);
    }

    public String prepareData(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder(SUPPLY).append(COMMA);
        stringBuilder.append(supply).append(SEPARATE_LINE).append(BUY).append(COMMA)
                .append(buy).append(SEPARATE_LINE).append(RESULT).append(COMMA).append(result);
        return stringBuilder.toString();
    }

    public void writeToFile(String preparedData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(preparedData);
        } catch (IOException e) {
            throw new RuntimeException("Can not  write data to file " + toFileName, e);
        }
    }
}
