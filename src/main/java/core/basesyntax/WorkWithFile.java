package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION = 0;
    private static final int NUMBER = 1;
    private static final int SUPPLIED = 0;
    private static final int BOUGHT = 1;
    private static final int RESULT = 2;
    private static final int NUMBER_OF_ROWS = 3;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_STR = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeInFile(resultTextMaker(statisticCalculator(readFile(fromFileName))), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                result.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
        return result.toString();
    }

    private int[] statisticCalculator(String textFromFile) {
        String[] separatedText = textFromFile.split("\\R");
        int[] numbers = new int[NUMBER_OF_ROWS];
        for (String documentation: separatedText) {
            String[] separatedString = documentation.split(COMA);
            if (separatedString[ACTION].equals(SUPPLY)) {
                numbers[SUPPLIED] += Integer.parseInt(separatedString[NUMBER]);
            } else if (separatedString[ACTION].equals(BUY)) {
                numbers[BOUGHT] += Integer.parseInt(separatedString[NUMBER]);
            }
            numbers[RESULT] = numbers[SUPPLIED] - numbers[BOUGHT];
        }
        return numbers;
    }

    private String[] resultTextMaker(int[] numbers) {
        String[] output = new String[NUMBER_OF_ROWS];
        output[SUPPLIED] = SUPPLY + COMA + numbers[SUPPLIED];
        output[BOUGHT] = BUY + COMA + numbers[BOUGHT];
        output[RESULT] = RESULT_STR + COMA + numbers[RESULT];
        return output;
    }

    private void writeInFile(String[] content, String toFile) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(content[SUPPLIED]);
            bufferedWriter.newLine();
            bufferedWriter.write(content[BOUGHT]);
            bufferedWriter.newLine();
            bufferedWriter.write(content[RESULT]);
        } catch (IOException e) {
            throw new RuntimeException("Can't access file!", e);
        }
    }

}
