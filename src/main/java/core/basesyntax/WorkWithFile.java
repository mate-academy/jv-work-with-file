package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int AMOUNT = 1;
    private static int supply = 0;
    private static int buy = 0;
    private static int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File infoFile = new File(fromFileName);
        File reportFile = new File(toFileName);
        StringBuilder stringBuilder = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
            stringBuilder = new StringBuilder();
            String textFromFileName = reader.readLine();
            while (textFromFileName != null) {
                String[] splittedLine = textFromFileName.split(",");
                for (int i = 0; i < splittedLine.length; i++) {
                    if (splittedLine[i].equals(SUPPLY)) {
                        supply += Integer.parseInt(splittedLine[AMOUNT]);
                    } else if (splittedLine[i].equals(BUY)) {
                        buy += Integer.parseInt(splittedLine[AMOUNT]);
                    }
                }
                textFromFileName = reader.readLine();
            }
            result = supply - buy;
            stringBuilder.append(SUPPLY + ",").append(supply).append(System.lineSeparator())
                    .append(BUY + ",").append(buy).append(System.lineSeparator())
                    .append(RESULT + ",").append(result);

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
                bufferedWriter.write(String.valueOf(stringBuilder));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            supply = 0;
            buy = 0;
            result = 0;
        } catch (IOException e) {
            throw new RuntimeException("Can`t find the file", e);
        }
    }
}
