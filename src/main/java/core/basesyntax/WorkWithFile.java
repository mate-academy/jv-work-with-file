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

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            writeFile(toFileName, prepareFile(getFile(fromFileName)));
        } catch (IOException e) {
            System.out.println("File was not found");
        }
    }

    private String getFile(String fromFileName) throws IOException {
        StringBuilder file = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(new FileInputStream(fromFileName)));
        String newLine = bufferedReader.readLine();
        while (newLine != null) {
            file.append(newLine.toLowerCase()).append(" ");
            newLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        return file.toString();
    }

    private String prepareFile(String file) {
        int sumOfSupply = 0;
        int sumOfbuy = 0;
        String[] fileToArr = file.split(" ");
        for (String fileInArr: fileToArr) {
            String[] temp;
            temp = fileInArr.split(",");
            if (temp[0].equals(BUY)) {
                sumOfbuy += Integer.parseInt(temp[1]);
            } else if (temp[0].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(temp[1]);
            }
        }
        return new StringBuilder(SUPPLY + "," + sumOfSupply + System.lineSeparator()
                + BUY + "," + sumOfbuy + System.lineSeparator() + RESULT
                + (sumOfSupply - sumOfbuy)).toString();
    }

    private boolean writeFile(String fileName, String fileInPut) throws IOException {
        File file = new File(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(fileInPut);
        bufferedWriter.close();
        return true;
    }
}
