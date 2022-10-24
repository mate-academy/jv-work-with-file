package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SEPARATOR = "::";
    private static final String COMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private enum INDEX {
        ACTION_INDEX,
        AMOUNT_INDEX
    }
    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrStr = readFromFile(fromFileName);
        String tmp = makeCounts(arrStr);
        writeToFile(toFileName, tmp);
    }

    private String makeCounts(String[] arrStr) {
        String[] tmpArrStr;
        int buy = 0;
        int supply = 0;
        for (String s : arrStr) {
            tmpArrStr = s.split(",");
            switch (tmpArrStr[INDEX.ACTION_INDEX.ordinal()]) {
                case BUY:
                    buy += Integer.parseInt(tmpArrStr[INDEX.AMOUNT_INDEX.ordinal()]);
                    break;
                case SUPPLY:
                    supply += Integer.parseInt(tmpArrStr[INDEX.AMOUNT_INDEX.ordinal()]);
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(1024);
        stringBuilder.append(SUPPLY).append(COMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(supply - buy);
        return stringBuilder.toString();
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
