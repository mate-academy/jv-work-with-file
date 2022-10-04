package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String EXCEPTION_TEXT = "Can't read file";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                String[] wordsFromFileLine = fileLine.split(LINE_SEPARATOR);
                for (int i = 0; i < wordsFromFileLine.length; i++) {
                    if (wordsFromFileLine[i].equals(SUPPLY)) {
                        supply = supply + Integer.parseInt(wordsFromFileLine[i + 1]);
                    }
                    if (wordsFromFileLine[i].equals(BUY)) {
                        buy = buy + Integer.parseInt(wordsFromFileLine[i + 1]);
                    }
                }
                fileLine = bufferedReader.readLine();
            }
            result.append(SUPPLY).append(LINE_SEPARATOR).append(supply)
                    .append(System.lineSeparator());
            result.append(BUY).append(LINE_SEPARATOR).append(buy)
                    .append(System.lineSeparator());
            result.append(RESULT).append(LINE_SEPARATOR).append(supply - buy)
                    .append(System.lineSeparator());
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_TEXT, e);
        }
        try {
            File outputFile = new File(toFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            bufferedWriter.write(result.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_TEXT, e);
        }
    }
}
