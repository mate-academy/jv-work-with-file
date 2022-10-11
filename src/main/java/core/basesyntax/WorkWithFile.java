package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] separatedInput = getData(fromFileName).split(System.lineSeparator());
        writeToFile(toFileName, separatedInput);
    }

    private void writeToFile(String toFileName, String[] separatedInput) {
        File writeData = new File(toFileName);
        for (String data : formatData(separatedInput)) {
            try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(writeData, true))) {
                buffWriter.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        }
    }

    private String getData(String fromFileName) {
        File inputData = new File(fromFileName);
        StringBuilder stringBuilderInput = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputData))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilderInput.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        return stringBuilderInput.toString();
    }

    private String[] formatData(String[] input) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String i : input) {
            String[] separateValue = i.split(COMA);
            if (separateValue[0].equals(SUPPLY)) {
                supply += Integer.parseInt(separateValue[1]);
            }
            if (separateValue[0].equals(BUY)) {
                buy += Integer.parseInt(separateValue[1]);
            }
            result = supply - buy;
        }
        return new String[]{SUPPLY + COMA + supply + System.lineSeparator(), BUY + COMA + buy
                + System.lineSeparator(), RESULT + COMA + result};
    }
}

