package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SPLITTER);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        String fileContent = stringBuilder.toString();
        String[] fileContentArray = fileContent.split(SPLITTER);

        for (int i = 0; i < fileContentArray.length; i += 2) {
            if (fileContentArray[i].equals(WORD_SUPPLY)) {
                supplySum += Integer.parseInt(fileContentArray[1 + i]);
            } else if (fileContentArray[i].equals(WORD_BUY)) {
                buySum += Integer.parseInt(fileContentArray[1 + i]);
            }
        }

        int result = supplySum - buySum;

        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            bufferedWriter.write(report.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + toFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the " + toFileName, e);
        }
    }
}
