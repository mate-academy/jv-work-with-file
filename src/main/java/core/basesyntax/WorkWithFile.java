package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "\\W";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(new File(fromFileName));
        String report = generateReport(data);
        writeReportToFile(new File(toFileName), report);
    }

    private String readFromFile(File file) {
        StringBuilder textFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String reader = bufferedReader.readLine();
            while (reader != null) {
                textFromFile.append(reader).append(System.lineSeparator());
                reader = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find file " + file);
        }
        return textFromFile.toString();
    }

    private String generateReport(String data) {
        String[] allText = data.split(DELIMITER);
        int countBuy = 0;
        int countSupply = 0;
        for (int i = 0; i < allText.length; i++) {
            if (allText[i].equals(BUY)) {
                countBuy += Integer.parseInt(allText[i + 1]);
            }
            if (allText[i].equals(SUPPLY)) {
                countSupply += Integer.parseInt(allText[i + 1]);
            }
        }
        int result = countSupply - countBuy;
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
