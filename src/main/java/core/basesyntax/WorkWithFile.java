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
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e + " Can't read a file: " + fromFileName);
        }
    }

    private String createReport(String dataFromFile) {
        String[] statistic = dataFromFile.split(",");
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < statistic.length; i++) {
            if (statistic[i].equals(SUPPLY)) {
                supplySum += Integer.valueOf(statistic[i + 1]);
            } else if (statistic[i].equals(BUY)) {
                buySum += Integer.valueOf(statistic[i + 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        int result = supplySum - buySum;
        stringBuilder.append(SUPPLY).append(",").append(supplySum)
        .append(System.lineSeparator()).append(BUY).append(",").append(buySum)
        .append(System.lineSeparator()).append("result").append(",").append(result);
        return stringBuilder.toString();
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
