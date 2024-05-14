package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_OR_BUY = 0;
    private static final int NUMBERS = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            StringBuilder readData = new StringBuilder();
            while (value != null) {
                readData.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String[] splitReadData = readData.toString().split(System.lineSeparator());
            int supply = 0;
            int bay = 0;
            for (String spitData : splitReadData) {
                String[] findStatistic = spitData.split(",");
                if (findStatistic[SUPPLY_OR_BUY].equals("supply")) {
                    supply += Integer.parseInt(findStatistic[NUMBERS]);
                } else if (findStatistic[SUPPLY_OR_BUY].equals("buy")) {
                    bay += Integer.parseInt(findStatistic[NUMBERS]);
                }
            }
            writeToFile(createReport(supply,bay),toFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeToFile(StringBuilder report,String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file " + toFileName, e);
        }
    }

    private StringBuilder createReport(int supply,int bay) {
        int result = supply - bay;
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator());
        report.append("buy,").append(bay).append(System.lineSeparator());
        report.append("result,").append(result);
        return report;
    }
}
