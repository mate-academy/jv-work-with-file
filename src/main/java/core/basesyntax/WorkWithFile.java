package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    public static String readFile(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                String[] valueArr = value.split(",");

                if (valueArr[0].equals("supply")) {
                    supply += Integer.parseInt(valueArr[1]);
                } else {
                    buy += Integer.parseInt(valueArr[1]);
                }

                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        return (supply + "," + buy);
    }

    public static String createReport(String dataFromFile) {
        String[] dataFromFileArray = dataFromFile.split(",");
        int supply = Integer.parseInt(dataFromFileArray[0]);
        int buy = Integer.parseInt(dataFromFileArray[1]);
        int result = supply - buy;

        return (
                "supply" + "," + supply + System.lineSeparator()
              + "buy" + "," + buy + System.lineSeparator()
              + "result" + "," + result + System.lineSeparator()
            );
    }

    public static void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
