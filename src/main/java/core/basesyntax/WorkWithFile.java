package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result,";
    private static final String COMA = ",";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        writeFile(toFileName, prepareFile(getFile(fromFileName)));

    }

    private String getFile(String fromFileName) {
        StringBuilder file = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(new FileInputStream(fromFileName)));
            String newLine = bufferedReader.readLine();
            while (newLine != null) {
                file.append(newLine.toLowerCase()).append(" ");
                newLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return file.toString();
    }

    private String prepareFile(String file) {
        int sumOfSupply = ZERO;
        int sumOfbuy = ZERO;
        String[] fileToArr = file.split(" ");
        for (String fileInArr : fileToArr) {
            String[] temp = fileInArr.split(COMA);
            if (temp[ZERO].equals(BUY)) {
                sumOfbuy += Integer.parseInt(temp[ONE]);
            } else if (temp[ZERO].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(temp[ONE]);
            }
        }
        return new StringBuilder(SUPPLY).append(COMA).append(sumOfSupply)
                .append(System.lineSeparator()).append(BUY).append(COMA)
                .append(sumOfbuy).append(System.lineSeparator()).append(RESULT)
                .append(((sumOfSupply - sumOfbuy))).toString();
    }

    private boolean writeFile(String fileName, String fileInPut) {
        File file = new File(fileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(fileInPut);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
        return true;
    }
}
