package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORD_SUPPLY = "supply";
    private static final String WORD_BUY = "buy";
    private static final String SPLITTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        String fileContent = readFileContent(fromFileName);
        String[] fileContentArray = fileContent.split(SPLITTER);

        for (int i = 0; i < fileContentArray.length; i += 2) {
            if (fileContentArray[i].equals(WORD_SUPPLY)) {
                supplySum += Integer.parseInt(fileContentArray[1 + i]);
            } else if (fileContentArray[i].equals(WORD_BUY)) {
                buySum += Integer.parseInt(fileContentArray[1 + i]);
            }
        }

        int result = supplySum - buySum;
        String report = createReport(supplySum, buySum, result);
        writeToFile(toFileName, report);
    }

    private String readFileContent(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SPLITTER);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(int supplySum, int buySum, int result) {
        StringBuilder report1 = new StringBuilder();
        report1.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);
        return report1.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(content);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the " + fileName, e);
        }
    }
}
