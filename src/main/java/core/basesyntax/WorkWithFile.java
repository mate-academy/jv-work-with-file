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

        List<String> dataFromFile = readFile(fromFileName);
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

    private String createReport(List<String> dataFromFile) {
        final StringBuilder builder = new StringBuilder();
        final int IndexOfName = 0;
        final int IndexOfMoney = 1;
        if (dataFromFile == null || dataFromFile.size() == 0) {
            return "";
        }
        int sumBuy = 0;
        int sumSupply = 0;
        int result;
        for (String str : dataFromFile) {
            String[] strSplit = str.split(",");
            if (strSplit[IndexOfName].length() > 0) {
                if (strSplit[IndexOfName].equals("supply")) {
                    sumSupply += Integer.parseInt(strSplit[IndexOfMoney]);
                } else {
                    sumBuy += Integer.parseInt(strSplit[IndexOfMoney]);
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
