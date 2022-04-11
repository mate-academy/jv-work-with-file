package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, readFile(fromFileName));
    }

    private List readFile(String fileNameRead) {
        List<String> str = new ArrayList<>();
        String text;
        try (FileReader fileFrom = new FileReader(fileNameRead)) {
            BufferedReader bufferedReader = new BufferedReader(fileFrom);
            while ((text = bufferedReader.readLine()) != null) {
                str.add(text);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileNameRead, e);
        }
        return str;
    }

    private void writeFile(String fileNameWrite, List<String> str) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameWrite))) {
            bufferedWriter.write(getNumber(str));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileNameWrite, e);
        }
    }

    public String getNumber(List<String> str) {
        int supplyOperation = 0;
        int buyOperation = 0;
        for (String line : str) {
            String[] splitFile = line.split(",");
            if (splitFile[0].equals("buy")) {
                buyOperation += Integer.parseInt(splitFile[1]);
            }
            if (splitFile[0].equals("supply")) {
                supplyOperation += Integer.parseInt(splitFile[1]);
            }
        }
        StringBuilder b = new StringBuilder();
        return b.append("supply,").append(supplyOperation).append(System.lineSeparator())
                .append("buy,").append(buyOperation).append(System.lineSeparator())
                .append("result,").append(supplyOperation - buyOperation).toString();
    }
}
