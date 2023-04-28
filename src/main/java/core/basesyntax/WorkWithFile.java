package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataArray = readFile(fromFileName);
        String calculations = calculate(dataArray);
        writeFile(toFileName, calculations);
    }

    public String[] readFile(String fromFileName) {
        String[] dataArray;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName, e);
        }
        dataArray = stringBuilder.toString().split(System.lineSeparator());
        return dataArray;
    }

    public String calculate(String[] dataArray) {
        int supply = 0;
        int buy = 0;
        String[] dataFromLine;
        for (String line : dataArray) {
            dataFromLine = line.split(SEPARATOR);
            if (dataFromLine[OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(dataFromLine[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(dataFromLine[AMOUNT_INDEX]);
            }
        }
        return createReport(supply, buy);
    }

    public void writeFile(String toFileName, String calculations) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculations);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    public String createReport(int supply, int buy) {
        return SUPPLY + SEPARATOR + supply + System.lineSeparator()
                + BUY + SEPARATOR + buy + System.lineSeparator()
                + RESULT + SEPARATOR + (supply - buy) + System.lineSeparator();
    }
}
