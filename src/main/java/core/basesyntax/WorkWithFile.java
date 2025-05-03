package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final String COMMA_SYMBOL = ",";
    private static final String WHITESPACE_SYMBOL = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readDataFromFile(fromFileName);
        String resultData = calculate(fileData, toFileName);
        writeToFile(toFileName, resultData);
    }

    private String readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File from = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(from))) {
            String note = bufferedReader.readLine();
            while (note != null) {
                stringBuilder.append(note).append(WHITESPACE_SYMBOL);
                note = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
        return stringBuilder.toString();
    }

    private String calculate(String fileData, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        int result;
        String[] arrayOfNotes = fileData.split(WHITESPACE_SYMBOL);
        for (String note : arrayOfNotes) {
            if (note.split(COMMA_SYMBOL)[TYPE_OF_OPERATION_INDEX].equals(SUPPLY_WORD)) {
                sumSupply += Integer.parseInt(note.split(COMMA_SYMBOL)[AMOUNT_INDEX]);
            } else if (note.split(COMMA_SYMBOL)[TYPE_OF_OPERATION_INDEX].equals(BUY_WORD)) {
                sumBuy += Integer.parseInt(note.split(COMMA_SYMBOL)[AMOUNT_INDEX]);
            }
        }
        result = sumSupply - sumBuy;
        return SUPPLY_WORD + COMMA_SYMBOL + sumSupply + System.lineSeparator()
                + BUY_WORD + COMMA_SYMBOL + sumBuy + System.lineSeparator()
                + RESULT_WORD + COMMA_SYMBOL + result;
    }

    private void writeToFile(String fileName, String resultData) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file", e);
        }
    }
}
