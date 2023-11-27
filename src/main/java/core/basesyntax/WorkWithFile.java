package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        int indexOfName = 0;
        int indexOfCount = 1;
        File file = new File(fromFileName);
        String[] splittedData;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();

            while (value != null) {
                splittedData = value.split(",");
                if (splittedData[indexOfName].equals("supply")) {
                    supplyCount += Integer.parseInt(splittedData[indexOfCount]);
                } else {
                    buyCount += Integer.parseInt(splittedData[indexOfCount]);
                }
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't such file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        createReport(toFileName, supplyCount, buyCount);
    }

    private void createReport(String toFileName, int supply, int buy) {
        int result = supply - buy;
        StringBuilder reportMessage = new StringBuilder();
        reportMessage.append("supply," + supply).append(System.lineSeparator());
        reportMessage.append("buy," + buy).append(System.lineSeparator());
        reportMessage.append("result," + result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportMessage.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
