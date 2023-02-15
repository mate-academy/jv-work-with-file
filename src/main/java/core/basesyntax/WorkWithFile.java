package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int COST_INDEX = 1;
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final String VIRGULE_SYMBOL = ",";
    private static final String WHITESPACE_SYMBOL = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File from = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(from))) {
            String note = bufferedReader.readLine();
            while (note != null) {
                stringBuilder.append(note).append(WHITESPACE_SYMBOL);
                note = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
        calculate(stringBuilder, toFileName);
    }

    private void calculate(StringBuilder stringBuilder, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] arrayOfNotes = stringBuilder.toString().split(WHITESPACE_SYMBOL);
        for (String note : arrayOfNotes) {
            if (note.split(VIRGULE_SYMBOL)[TYPE_OF_OPERATION_INDEX].equals(SUPPLY_WORD)) {
                sumSupply += Integer.parseInt(note.split(VIRGULE_SYMBOL)[COST_INDEX]);
            } else if (note.split(VIRGULE_SYMBOL)[TYPE_OF_OPERATION_INDEX].equals(BUY_WORD)) {
                sumBuy += Integer.parseInt(note.split(VIRGULE_SYMBOL)[COST_INDEX]);
            }
        }
        writeToFile(toFileName, sumSupply, sumBuy);
    }

    private void writeToFile(String fileName, int sumSupply, int sumBuy) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(SUPPLY_WORD + VIRGULE_SYMBOL + sumSupply + System.lineSeparator()
                    + BUY_WORD + VIRGULE_SYMBOL + sumBuy + System.lineSeparator()
                    + RESULT_WORD + VIRGULE_SYMBOL + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file", e);
        }
    }
}
