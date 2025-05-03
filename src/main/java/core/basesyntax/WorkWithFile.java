package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String someReadFile = readFromFile(fromFileName);
        String reportCreated = createReport(someReadFile);
        writeReport(reportCreated, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String someReadFile) {
        String[] readFromFileNameArray = someReadFile.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String nameAndValue : readFromFileNameArray) {
            String[] nameAndValueSplit = nameAndValue.split(",");
            if (nameAndValueSplit[0].equals("supply")) {
                supplyAmount += Integer.parseInt(nameAndValueSplit[1]);
            }
            if (nameAndValueSplit[0].equals("buy")) {
                buyAmount += Integer.parseInt(nameAndValueSplit[1]);
            }
        }
        int result = supplyAmount - buyAmount;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(result);
        return builder.toString();
    }

    private void writeReport(String reportCreated, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportCreated);
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file !", e);
        }
    }

}
