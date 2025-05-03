package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SORT_SUPPLY = "supply";
    private static final String SORT_BUY = "buy";

    public String readFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                dataFromFile.append(fileLine).append(" ");
                fileLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file content" + e);
        }
        return dataFromFile.toString();
    }

    public String generateReport(String data) {
        int supplyAmount = 0;
        int byeAmount = 0;
        String[] dataAmountValue = data.split(" ");
        for (String value : dataAmountValue) {
            if (value.contains(SORT_SUPPLY)) {
                supplyAmount += Integer.parseInt(value.replaceAll(SORT_SUPPLY + ",", ""));
            } else if (value.contains(SORT_BUY)) {
                byeAmount += Integer.parseInt(value.replaceAll(SORT_BUY + ",", ""));
            }
        }
        return SORT_SUPPLY + "," + supplyAmount + System.lineSeparator()
                + SORT_BUY + "," + byeAmount + System.lineSeparator()
                + "result," + (supplyAmount - byeAmount);
    }

    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }
}
