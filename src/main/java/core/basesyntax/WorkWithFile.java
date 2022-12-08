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
    private static final String RESULT = "result";
    private static final int SEARCH_BY_INDEX = 0;
    private static final int COUNT_BY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);
        try {
            readingFile(readFile, writeFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private void readingFile(File readFile, File writeFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        StringBuilder builder = new StringBuilder();
        String value = reader.readLine();
        while (value != null) {
            builder.append(value).append(System.lineSeparator());
            value = reader.readLine();
        }
        String[] informationRead = builder.toString().split(System.lineSeparator());
        preparingResult(informationRead, writeFile);
    }

    private void preparingResult(String[] informationRead, File writeFile) throws IOException {
        int supplyCount = 0;
        int buyCount = 0;
        for (String infoFromFile : informationRead) {
            String[] listFromFile = infoFromFile.split(",");
            for (int i = 1; i < listFromFile.length; i++) {
                if (listFromFile[SEARCH_BY_INDEX].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(listFromFile[COUNT_BY_INDEX]);
                }
                if (listFromFile[SEARCH_BY_INDEX].equals(BUY)) {
                    buyCount += Integer.parseInt(listFromFile[COUNT_BY_INDEX]);
                }
            }
        }
        resultFile(supplyCount, buyCount, writeFile);
    }

    private void resultFile(int supplyCount, int buyCount, File writeFile) throws IOException {
        int result = supplyCount - buyCount;
        String readInfo = SUPPLY
                + "," + supplyCount
                + System.lineSeparator()
                + BUY + ","
                + buyCount
                + System.lineSeparator()
                + RESULT + ","
                + result;
        writingFile(readInfo, writeFile);
    }

    private void writingFile(String readInfo, File writeFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, true))) {
            writer.write(readInfo);
        }
    }
}
