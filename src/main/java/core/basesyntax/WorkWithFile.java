package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "\\W";

    public void getStatistic(String fromFileName, String toFileName) {
        File checkFile = new File(fromFileName);
        File newFile = new File(toFileName);
        readFromFile(checkFile);
        String[] allText = readFromFile(checkFile).split(DELIMITER);
        int countSupply = getCount(allText, "supply");
        int countBuy = getCount(allText, "buy");
        int result = countSupply - countBuy;
        String report = generateReport(countSupply, countBuy, result);
        writeReportToFile(newFile, report);
    }

    private String readFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String reader = bufferedReader.readLine();
            while (reader != null) {
                builder.append(reader).append(System.lineSeparator());
                reader = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find file " + file);
        }
        return builder.toString();
    }

    private int getCount(String[] data, String keyword) {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(keyword)) {
                count += Integer.parseInt(data[i + 1]);
            }
        }
        return count;
    }

    private String generateReport(int countSupply, int countBuy, int result) {
        return "supply," + countSupply + System.lineSeparator()
                + "buy," + countBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeReportToFile(File file, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't find file " + file);
        }
    }
}
