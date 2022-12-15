package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static WorkWithFile workWithFile = new WorkWithFile();
    private static ReadData readData = new ReadData();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData.readFile(fromFileName);
        String report = workWithFile.createReport(data);
        workWithFile.writeData(toFileName, report);
    }

    public String createReport(String data) {
        String[] array = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < array.length; i++) {
            int index = array[i].indexOf(",");
            if (array[i].substring(0, index).equals("supply")) {
                supply += Integer.parseInt(array[i].substring(index + 1, array[i].length()));
            } else if (array[i]
                    .substring(0, index).equals("buy")) {
                buy += Integer.parseInt(array[i].substring(index + 1, array[i].length()));
            }
        }
        int result = supply - buy;
        StringBuilder builder = new StringBuilder("supply");
        builder.append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return builder.toString();
    }

    private void writeData(String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file");
        }
    }
}
