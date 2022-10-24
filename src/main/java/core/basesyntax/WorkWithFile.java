package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SEPARATOR = "::";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private enum INDEX {
        ACTION_INDEX,
        AMOUNT_INDEX
    }
    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrStr = readFromFile(fromFileName);
        writeToFile(toFileName, makeCounts(arrStr));
    }

    private String makeCounts(String[] arrStr) {
        String[] tmpArrStr;
        int buy = 0;
        int supply = 0;
        for (String s : arrStr) {
            tmpArrStr = s.split(" ");
            switch (tmpArrStr[INDEX.ACTION_INDEX.ordinal()]) {
                case BUY:
                    buy += Integer.getInteger(tmpArrStr[INDEX.AMOUNT_INDEX.ordinal()]);
                    break;
                case SUPPLY:
                    supply += Integer.getInteger(tmpArrStr[INDEX.AMOUNT_INDEX.ordinal()]);
                    break;
            }
        }
        return System.out.printf("%s,%d%n%s,%d%n%s,%d"
                , BUY, buy, SUPPLY, supply, RESULT, buy + supply).toString();
    }
    private String[] readFromFile(String fromFileName) {
        File fin = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder(4096);
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
