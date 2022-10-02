package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private int fullSupply = 0;
    private int fullBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] refactored = line.split(",");
                switch (refactored[0]) {
                    case SUPPLY:
                        fullSupply += Integer.parseInt(refactored[1]);
                        break;
                    case BUY:
                        fullBuy += Integer.parseInt(refactored[1]);
                        break;
                    default:
                        break;
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read this file" + fromFileName, e);
        }
        writeToFile(toFileName);
    }

    private void writeToFile(String toFileName) {
        StringBuilder reportAboutDay = new StringBuilder();
        int result = fullSupply - fullBuy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            reportAboutDay
                    .append(SUPPLY).append(",").append(fullSupply).append(System.lineSeparator())
                    .append(BUY).append(",").append(fullBuy).append(System.lineSeparator())
                    .append("result,").append(result);
            bufferedWriter.write(reportAboutDay.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file" + toFileName, e);
        }
    }
}
