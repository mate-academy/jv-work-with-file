package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        // read from file
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        String dataFromFile = stringBuilder.toString();
        // read from file ends

        // report creating
        String[] dataArray = dataFromFile.split(", ");
        int buyValues = 0;
        int supplyValues = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals("buy")) {
                buyValues += Integer.parseInt(dataArray[i + 1]);
            }
            if (dataArray[i].equals("supply")) {
                supplyValues += Integer.parseInt(dataArray[i + 1]);
            }
        }
        int result = supplyValues - buyValues;
        StringBuilder reportBuilder = new StringBuilder();
        String report = reportBuilder
                .append("supply,")
                .append(supplyValues)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyValues)
                .append(System.lineSeparator())
                .append("result,")
                .append(result)
                .toString();
        // report creating ends

        // write to file
        File file1 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
        //write to file ends
    }
}
