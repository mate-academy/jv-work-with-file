package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        int countSupply = 0;
        int countBuy = 0;
        String[] splitDataFromFile = dataFromFile.split(System.lineSeparator());
        for (int i = 0; i < splitDataFromFile.length; i++) {
            String[] splitString = splitDataFromFile[i].split(",");
            if (splitString[0].equals("supply")) {
                countSupply += Integer.parseInt(splitString[1]);
            } else if (splitString[0].equals("buy")) {
                countBuy += Integer.parseInt(splitString[1]);
            }
        }

        int result = countSupply - countBuy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(countSupply).append(System.lineSeparator());
        builder.append("buy,").append(countBuy).append(System.lineSeparator());
        builder.append("result,").append(result);
        String report = builder.toString();
        return report;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
