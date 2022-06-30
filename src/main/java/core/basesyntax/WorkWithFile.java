package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder readFile = new StringBuilder();
        StringBuilder writeReport = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int value = bufferedReader.read();
            while (value != -1) {
                readFile.append((char) value);
                value = bufferedReader.read();
            }
            String [] wordsReadFile = readFile.toString().split("[,|\\n]");
            int buy = 0;
            int supply = 0;
            for (int i = 0; i < wordsReadFile.length; i++) {
                if (wordsReadFile[i].equals(BUY)) {
                    buy = buy+Integer.valueOf(wordsReadFile[i+1]);
                } else if (wordsReadFile[i].equals(SUPPLY)) {
                    supply = supply + Integer.valueOf(wordsReadFile[i+1]);
                }
            }
            writeReport.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply-buy);
            System.out.println(writeReport.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't to read file", e);
        }

    }

}
