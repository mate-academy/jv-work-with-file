package core.basesyntax;

import java.io.File;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String TOTAL = "result";
    private static final char CHAR = ',';
    private static final String SEPARATOR = System.lineSeparator();
    private static final int WORD_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        ReadFile readFile = new ReadFile();
        WriteFile writeFile = new WriteFile();
        File file = new File(fromFileName);

        String[] arrayOfContent = readFile.readFileContent(file).split(System.lineSeparator());
        for (int i = 0; i < arrayOfContent.length; i++) {
            String[] elements = arrayOfContent[i].split(",");
            if (SUPPLY.equals(elements[WORD_INDEX])) {
                supplySum += Integer.parseInt(elements[NUMBER_INDEX]);
            } else if (BUY.equals(elements[WORD_INDEX])) {
                buySum += Integer.parseInt((elements[NUMBER_INDEX]));
            }
        }
        StringBuilder countedContent = new StringBuilder(SUPPLY + CHAR
                + supplySum + SEPARATOR + BUY + CHAR + buySum
                + SEPARATOR + TOTAL + CHAR + (supplySum - buySum));
        writeFile.writeToFile(countedContent.toString(),toFileName);
    }
}
