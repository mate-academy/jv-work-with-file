package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = "::";
    private static final String COMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrStr = readFromFile(fromFileName);
        writeToFile(toFileName, makeCounts(arrStr));
    }

    private String makeCounts(String[] arrStr) {
        final int actionIndex = 0;
        final int amountIndex = 1;

        String[] tmpArrStr;
        int buy = 0;
        int supply = 0;
        for (String s : arrStr) {
            tmpArrStr = s.split(",");
            switch (tmpArrStr[actionIndex]) {
                case BUY:
                    buy += Integer.parseInt(tmpArrStr[amountIndex]);
                    break;
                case SUPPLY:
                    supply += Integer.parseInt(tmpArrStr[amountIndex]);
                    break;
                default:
                    break;
            }
        }
        return generateReport(supply, buy);
    }

    private String generateReport(int supply, int buy) {
        return SUPPLY + COMA + supply + System.lineSeparator()
                + BUY + COMA + buy + System.lineSeparator()
                + RESULT + COMA + (supply - buy);
    }

    private String[] readFromFile(String fromFileName) {
        File fin = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fin))) {
            String tmpStr = bufferedReader.readLine();
            while (tmpStr != null) {
                stringBuilder.append(tmpStr).append(SEPARATOR);
                tmpStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString().split(SEPARATOR);
    }

    private void writeToFile(String toFileName, String toOut) {
        File fout = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fout))) {
            bufferedWriter.write(toOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
