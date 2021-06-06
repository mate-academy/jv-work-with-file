package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = reporting(dataFromFile);
        writeToFile(report, toFileName);

    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        return stringBuilder.toString();

    }

    private String reporting(String dataFromFile) {
        int sumsupply = 0;
        int sumbuy = 0;
        String data = dataFromFile.replaceAll("\n|\r\n"," ");
        String[]datainfo = data.split(" ");
        for (int i = 0; i < datainfo.length; i++) {
            String[] reportinfo = datainfo[i].split(",");
            if (reportinfo[0].equals("supply")) {
                sumsupply += Integer.parseInt(reportinfo[1]);
            } else {
                sumbuy += Integer.parseInt(reportinfo[1]);
            }
        }
        int result = sumsupply - sumbuy;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(sumsupply)
                .append(System.lineSeparator()).append("buy,")
                .append(sumbuy).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

}


