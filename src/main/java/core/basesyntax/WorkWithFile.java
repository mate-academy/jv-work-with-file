package core.basesyntax;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int COUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(getDataFromFile(fromFileName));
        writeDataToFile(toFileName, report);
    }

    private String getDataFromFile(String fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        String[] splitData = data.split(" ");
        int supply = 0;
        int buy = 0;
        for (String element: splitData) {
            String[] split = element.split(",");
            if (split[NAME_INDEX].equals("supply")) {
                supply += Integer.parseInt(split[COUNT_POSITION]);
            }
            if (split[NAME_INDEX].equals("buy")) {
                buy += Integer.parseInt(split[COUNT_POSITION]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return stringBuilder.toString();
    }

    private void writeDataToFile(String info, String report) {
        File file = new File(info);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(report);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file",  e);
        }
    }
}



