package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private StringBuilder stringBuilder = new StringBuilder();
    private String report = new String();

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File fileForRead = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileForRead));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            createReport();
            writeToFile(toFileName);
        } catch (IOException e) {
            throw new RuntimeException(e + " Can't read file: " + fromFileName);
        }
    }

    private void createReport() {
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
        report = stringBuilder.toString();
    }

    public void writeToFile(String toFileName) {
        File fileForWrite = new File(toFileName);
        try {
            fileForWrite.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e + " Can't create a file: " + fileForWrite);
        }
        try {
            Files.write(fileForWrite.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e + " Can't write to file: " + fileForWrite);
        }
    }
}
