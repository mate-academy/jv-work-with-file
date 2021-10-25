package core.basesyntax;

import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] data = readFile(fromFileName);
        String result = createReport(data);
        writeFile(toFileName, result);
    }

    public void writeFile(String fileName, String data) {
        try (FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write new file " + fileName, e);
        }
    }

    public String createReport(String[][] arr) {
        int allSupply = 0;
        int allBuy = 0;
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i][0]) {
                case SUPPLY_FIELD:
                    allSupply += Integer.valueOf(arr[i][1]);
                    break;
                case BUY_FIELD:
                    allBuy += Integer.valueOf(arr[i][1]);
                    break;
                default:
                    throw new FieldNotFoundException("Some field in file is incorrect");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(allSupply).append(System.lineSeparator())
                .append("buy,").append(allBuy).append(System.lineSeparator())
                .append("result,").append(allSupply - allBuy);
        return stringBuilder.toString();
    }

    public String[][] readFile(String fileName) {
        List<String[]> allLines;
        try (FileReader fileReader = new FileReader(fileName);
                CSVReader csvReader = new CSVReader(fileReader)) {
            allLines = csvReader.readAll();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fileName, e);
        }
        String[][] result = allLines.toArray(new String[0][0]);
        return result;
    }
}
