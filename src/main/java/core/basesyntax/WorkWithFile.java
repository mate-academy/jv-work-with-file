package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        List dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeFile(report, toFileName);
    }

    private List<String> readFile(String fromFileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                list.add(value);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return list;
    }

    private String createReport(List dataFromFile) {
        final StringBuilder builder = new StringBuilder();
        if (dataFromFile == null || dataFromFile.size() == 0) {
            return "";
        }
        int sumBuy = 0;
        int sumSupply = 0;
        int result;
        for (Object str : dataFromFile) {
            String[] strSplit = ((String) str).split(",");
            if (strSplit[0].length() > 0) {
                if (strSplit[0].equals("supply")) {
                    sumSupply += Integer.parseInt(strSplit[1]);
                } else {
                    sumBuy += Integer.parseInt(strSplit[1]);
                }
            }
        }
        result = sumSupply - sumBuy;
        builder.append("supply,").append(sumSupply).append(System.lineSeparator());
        builder.append("buy,").append(sumBuy).append(System.lineSeparator());
        builder.append("result,").append(result);
        return builder.toString();
    }

    private void writeFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
