package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        String report = createReport(getCreateDataForReport(readFromFile(fromFileName)));
        writeFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                stringBuilder.append(temp).append(System.lineSeparator());
            }
            String [] records = stringBuilder.toString().split("\\n");
            return records;

        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + fromFileName, e);
        }
    }

    private int[] getCreateDataForReport(String[] records) {
        int countBuy = 0;
        int countSupply = 0;
        int result;
        for (String element : records) {
            String[] line = element.split(",");
            if (line[0].equals(BUY)) {
                countBuy += Integer.parseInt(line[1]);
            } else {
                countSupply += Integer.parseInt(line[1]);
            }
        }
        result = countSupply - countBuy;
        return new int[]{countSupply, countBuy, result};
    }

    private String createReport(int[] report) {
        return "supply," + report[0] + System.lineSeparator()
                + "buy," + report[1] + System.lineSeparator()
                + "result," + report[2];
    }

    private void writeFile(String toFileName, String createReport) {
        File file = new File(toFileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(createReport);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file");
        }
    }
}
