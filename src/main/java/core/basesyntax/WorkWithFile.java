package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = getDataArray(fromFileName);
        String[] updatedData = getUpdateData(dataFromFile);
        writeToFile(toFileName, updatedData);
    }

    private String[] getDataArray(String fileName) {
        final String Line_Separator = "_";
        File readFile = new File(fileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String value = reader.readLine();

            while (value != null) {
                builder.append(value)
                        .append(Line_Separator);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }

        return builder.toString().split(Line_Separator);
    }

    private String[] getUpdateData(String[] dataArray) {
        final int Price_Index = 1;
        final int Action_Index = 0;
        int supply = 0;
        int buy = 0;

        for (String datum : dataArray) {
            String[] arr = datum.split(",");

            if (arr[Action_Index].equals("supply")) {
                supply += Integer.parseInt(arr[Price_Index]);
            } else {
                buy += Integer.parseInt(arr[Price_Index]);
            }
        }

        int result = supply - buy;
        String suppleInfo = "supply," + supply;
        String buyInfo = "buy," + buy;
        String resultInfo = "result," + result;
        return new String[]{suppleInfo, buyInfo, resultInfo};
    }

    private void writeToFile(String fileName, String[] dataToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String writeDatum : dataToWrite) {
                writer.write(writeDatum);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}
