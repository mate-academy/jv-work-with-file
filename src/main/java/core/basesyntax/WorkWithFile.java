package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int COUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(getDataFromFile(fromFileName));
        writeDataToFile(toFileName, report);
    }

    private String getDataFromFile(String fromFile) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        String[] split1 = data.split(" ");
        int countSupply = 0;
        int countBuy = 0;
        for (String element : split1) {
            String[] split = element.split(",");
            if (split[NAME_INDEX].equals("supply")) {
                countSupply += Integer.parseInt(split[COUNT_POSITION]);
            }
            if (split[NAME_INDEX].equals("buy")) {
                countBuy += Integer.parseInt(split[COUNT_POSITION]);
            }
        }
        int result = countSupply - countBuy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply").append(",").append(countSupply).append(System.lineSeparator())
                .append("buy").append(",").append(countBuy).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return builder.toString();
    }

    private void writeDataToFile(String info, String report) {
        File file = new File(info);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(report);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
