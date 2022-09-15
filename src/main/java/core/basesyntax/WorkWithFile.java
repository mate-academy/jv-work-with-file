package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_INDEX = 0;
    public static final int COUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);

    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String [] line = new String[2];
        int supplyQuantity = 0;
        int buyQuantity = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String reading = bufferedReader.readLine();
            while (reading != null) {
                line = reading.split(",");
                if (line[OPERATION_INDEX].equals("buy")) {
                    supplyQuantity += Integer.parseInt(line[COUNT_INDEX]);
                } else {
                    buyQuantity += Integer.parseInt(line[COUNT_INDEX]);
                }
                reading = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can`t read file: " + fromFileName + " " + e);
        } catch (IOException e) {
            System.out.println("Can`t read line from file: " + fromFileName + " " + e);
        }
        line[0] = supplyQuantity + "," + buyQuantity;
        return line[0];
    }

    private String createReport(String dataForReport) {
        int index = dataForReport.indexOf(',');
        String report = dataForReport.substring(0, index);
        dataForReport = dataForReport.substring(index + 1);

        report = "supply," + dataForReport + System.lineSeparator()
                    + "buy," + report + System.lineSeparator()
                    + "result," + (Integer.parseInt(dataForReport)
                    - Integer.parseInt(report));
        return report;
    }

    private void writeToFile(String data, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            System.out.println("Can`t write data in " + file.getName() + ", " + e);
        }
    }
}
