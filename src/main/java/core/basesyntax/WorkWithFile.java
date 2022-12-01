package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int SEARCH_BY_INDEX = 0;
    private static final int COUNT_BY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            readingFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void readingFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String value = reader.readLine();
        while (value != null) {
            builder.append(value).append(System.lineSeparator());
            value = reader.readLine();
        }
        String[] readInfo = builder.toString().split(System.lineSeparator());
        preparingResult(readInfo);
    }

    private void preparingResult(String[] readInfo) {
        int supplyCount = 0;
        int buyCount = 0;
        for (String infoFromFile : readInfo) {
            String[] listFromFile = infoFromFile.split(",");
            for (int i = 0; i < listFromFile.length; i++) {
                if (listFromFile[SEARCH_BY_INDEX].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(listFromFile[COUNT_BY_INDEX]);
                }
                if (listFromFile[SEARCH_BY_INDEX].equals(BUY)) {
                    buyCount += Integer.parseInt(listFromFile[COUNT_BY_INDEX]);
                }
            }
        }
        resultFile(supplyCount, buyCount);
    }

    private void resultFile(int supplyCount, int buyCount) {
        StringBuilder builder = new StringBuilder();
        int result = supplyCount - buyCount;
        builder.append(SUPPLY)
                .append(",").append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT).append(",")
                .append(result);
        String writeInfo = builder.toString();
    }

}

