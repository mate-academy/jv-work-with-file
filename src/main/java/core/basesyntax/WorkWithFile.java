package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY = 0;
    private static final int BAY = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        StringBuilder report = createReport(data[SUPPLY], data[BAY]);
        writeToFile(report, toFileName);
    }

    private int[] readFile(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder readData = new StringBuilder();
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                readData.append(value).append(System.lineSeparator());
            }
            String[] splitReadData = readData.toString().split(System.lineSeparator());
            int supply = 0;
            int bay = 0;
            for (String spitData : splitReadData) {
                String[] findStatistic = spitData.split(SEPARATOR);
                if (findStatistic[OPERATION_INDEX].equals("supply")) {
                    supply += Integer.parseInt(findStatistic[AMOUNT_INDEX]);
                } else if (findStatistic[OPERATION_INDEX].equals("buy")) {
                    bay += Integer.parseInt(findStatistic[AMOUNT_INDEX]);
                }
            }
            return new int[]{supply, bay};
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private StringBuilder createReport(int supply, int bay) {
        int result = supply - bay;
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator());
        report.append("buy,").append(bay).append(System.lineSeparator());
        report.append("result,").append(result);
        return report;
    }

    private void writeToFile(StringBuilder report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file " + toFileName, e);
        }
    }
}
