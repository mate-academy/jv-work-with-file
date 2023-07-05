package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getDataFromFile(fromFileName);
        String dataToFile = getRapport(dataFromFile);
        writeDataToFile(dataToFile,toFileName);
    }

    private String getRapport(String dateFromFile) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder builder = new StringBuilder();
        String[] dataArray = dateFromFile.split(System.lineSeparator());
        for (String data: dataArray) {
            String[] temp = data.split(",");
            if (temp[0].equals("supply")) {
                supplyAmount += Integer.parseInt(temp[1]);
            }
            if (temp[0].equals("buy")) {
                buyAmount += Integer.parseInt(temp[1]);
            }
        }
        builder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount).append(System.lineSeparator());
        return builder.toString();
    }

    private String getDataFromFile(String fileName) {
        File fileFrom = new File(fileName);
        StringBuilder builder = new StringBuilder((int) fileFrom.length());
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            int value = reader.read();
            if (value == -1) {
                return "";
            }
            while (value != -1) {
                builder.append((char)value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
        return builder.toString();
    }

    private void writeDataToFile(String data, String fileName) {
        File fileTo = new File(fileName);
        if (!fileTo.exists() && fileTo.isDirectory()) {
            fileTo.mkdirs();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
