package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SPLITTER = ",";
    private static final String FIRST_OPTION_CHECKER = "supply";
    private static final String SECOND_OPTION_CHECKER = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        makeDayBalance(readFromFile(fromFileName));
        writeToFile(toFileName,makeDayBalance(readFromFile(fromFileName)));
    }

    protected StringBuilder readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedReader",e);
                }
            }
        }
        return stringBuilder;
    }

    protected String [] makeDayBalance(StringBuilder stringBuilder) {

        String [] dataFromFile = stringBuilder.toString().split(System.lineSeparator());
        int totalSupplyOperations = 0;
        int totalBuyOperations = 0;
        for (String value:dataFromFile) {
            String [] eachEntry = value.split(SPLITTER);
            if (eachEntry[0].equals(FIRST_OPTION_CHECKER)) {
                totalSupplyOperations += Integer.parseInt(eachEntry[1]);
            } else if (eachEntry[0].equals(SECOND_OPTION_CHECKER)) {
                totalBuyOperations += Integer.parseInt(eachEntry[1]);
            }
        }
        StringBuilder supplyEntry = new StringBuilder("supply,").append(totalSupplyOperations);
        StringBuilder buyEntry = new StringBuilder("buy,").append(totalBuyOperations);
        StringBuilder resultEntry = new StringBuilder("result,")
                .append(totalSupplyOperations - totalBuyOperations);
        return new String[]{supplyEntry.toString(),buyEntry.toString(),resultEntry.toString()};
    }

    protected void writeToFile(String toFileName, String [] data) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String value:data) {
                bufferedWriter.write(value);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter",e);
                }
            }
        }
    }
}
