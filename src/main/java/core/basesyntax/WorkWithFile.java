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

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        readFromFile(fromFileName, stringBuilder);
        String report = createReport(stringBuilder).toString();
        writeToFile(toFileName, report);
    }

    private StringBuilder readFromFile(String fromFileName, StringBuilder stringBuilder) {

        try {
            File fileForRead = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileForRead));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder;
        } catch (IOException e) {
            throw new RuntimeException(e + " Can't read a file: " + fromFileName);
        }
    }

    private StringBuilder createReport(StringBuilder stringBuilder) {
        String[] statistic = stringBuilder.toString().split(",");
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < statistic.length; i++) {
            if (statistic[i].equals(SUPPLY)) {
                supplySum += Integer.valueOf(statistic[i + 1]);
            } else if (statistic[i].equals(BUY)) {
                buySum += Integer.valueOf(statistic[i + 1]);
            }
        }
        int result = supplySum - buySum;
        stringBuilder.setLength(0);
        stringBuilder.append(SUPPLY).append(",").append(supplySum)
        .append(System.lineSeparator()).append(BUY).append(",").append(buySum)
        .append(System.lineSeparator()).append("result").append(",").append(result);
        return stringBuilder;
    }

    public void writeToFile(String toFileName, String report) {
        File fileForWrite = new File(toFileName);
        try {
            fileForWrite.createNewFile();
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(fileForWrite));
                bufferedWriter.write(report);
            } catch (IOException e) {
                throw new RuntimeException(e + " Can't write to a file: " + fileForWrite);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e + " Cant close a file " + fileForWrite);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e + " Can't create a file: " + fileForWrite);
        }
    }
}
