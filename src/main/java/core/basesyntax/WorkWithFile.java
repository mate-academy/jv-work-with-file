package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMA = ",";
    private static final String SPACE = " ";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String[] splitText = splitTextIntoWords(fileContent);
        int[] results = calculateResult(splitText);
        String report = createReport(results);
        writeContentToFile(report, toFileName);
    }

    private String readFileContent(String fromFileName) {
        StringBuilder allText = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allText.append(line).append(SPACE);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return allText.toString();
    }

    private String[] splitTextIntoWords(String text) {
        return text.split(SPACE);
    }

    private int[] calculateResult(String[] text) {
        int supplySum = 0;
        int buySum = 0;

        for (String element : text) {
            String[] partsOfElement = element.split(COMA);
            String operation = partsOfElement[0];
            int value = Integer.parseInt(partsOfElement[1]);
            if (operation.equals(SUPPLY)) {
                supplySum += value;
            } else if (operation.equals(BUY)) {
                buySum += value;
            }
        }
        return new int[] {supplySum, buySum};
    }

    private String createReport(int[] results) {
        int supplySum = results[0];
        int buySum = results[1];
        int result = supplySum - buySum;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY + COMA).append(supplySum).append(System.lineSeparator());
        stringBuilder.append(BUY + COMA).append(buySum).append(System.lineSeparator());
        stringBuilder.append(RESULT + COMA).append(result);

        return stringBuilder.toString();
    }

    private void writeContentToFile(String text, String toFileName) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(text);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close the file", e);
                }
            }
        }
    }
}
