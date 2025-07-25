package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FILE_NAME_INDEX = 0;
    private static final int FILE_PRICE_INDEX = 1;
    private static final String BUY_TYPE = "buy";
    private static final String SUPPLY_TYPE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        int totalBuy = 0;
        int totalSupply = 0;
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(",");
                if (lineArray[FILE_NAME_INDEX].equalsIgnoreCase(BUY_TYPE)) {
                    totalBuy += Integer.parseInt(lineArray[FILE_PRICE_INDEX]);
                } else if (lineArray[FILE_NAME_INDEX].equalsIgnoreCase(SUPPLY_TYPE)) {
                    totalSupply += Integer.parseInt(lineArray[FILE_PRICE_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int result = totalSupply - totalBuy;
        String report = "supply,"
                + totalSupply
                + System.lineSeparator()
                + "buy," + totalBuy
                + System.lineSeparator()
                + "result," + result;

        BufferedWriter bufferedWriter = null;
        try {
            File outputFile = new File(toFileName);
            bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
