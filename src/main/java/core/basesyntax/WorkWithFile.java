package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int MAX_ARRAY_LENGTH = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readValue = read(fromFileName);
        String replacedWordValue = "";
        int replacedNumbValue = 0;
        int firstSum = 0;
        int secondSum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < MAX_ARRAY_LENGTH; i++) {
            String[] splitValue = readValue[i].split(",");
            replacedWordValue = splitValue[0];
            replacedNumbValue += Integer.parseInt(splitValue[1]);
            for (int k = i + 1; k < readValue.length; k++) {
                if (readValue[k].startsWith(splitValue[0])) {
                    replacedNumbValue += Integer.parseInt(
                            readValue[k].substring(readValue[k].indexOf(",") + 1)
                    );
                }
            }
            if (i == 0) {
                firstSum = replacedNumbValue;
            } else {
                secondSum = replacedNumbValue;
            }
            stringBuilder.append(replacedWordValue)
                    .append(",")
                    .append(replacedNumbValue)
                    .append("\n").append(" ");
            replacedNumbValue = 0;
        }
        int temporarySum = firstSum - secondSum;
        if (temporarySum < 0) {
            temporarySum = temporarySum * -1;
        }
        stringBuilder.append("result").append(",").append(temporarySum);
        String[] writeValue = stringBuilder.toString().split(" ");
        String temporaryField = "";
        if (writeValue[0].startsWith("buy")) {
            temporaryField = writeValue[0];
            writeValue[0] = writeValue[1];
            writeValue[1] = temporaryField;
        }

        write(toFileName, writeValue);
    }

    private String[] read(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (Exception ex) {
            System.out.println("Can't read data from the file: " + fromFileName);
        }
        return stringBuilder.toString().split("\n");
    }

    private void write(String toFileName, String[] toWrite) {
        for (String s : toWrite) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(toFileName, true))) {
                bufferedWriter.write(s);
            } catch (Exception ex) {
                System.out.println("Can't write data from the file: " + toFileName);
            }
        }
    }
}
