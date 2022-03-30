package core.basesyntax;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buysum = 0;
        int suplysum = 0;
        String[] file = fileReader(fromFileName);
        for (String lines : file) {
            String[] splitfile = lines.split(",");
            if (splitfile[0].equals("buy")) {
                buysum += Integer.parseInt(splitfile[1]);
            }
            if (splitfile[0].equals("supply")) {
                suplysum += Integer.parseInt(splitfile[1]);
            }
        }
        int result = suplysum - buysum;
        fileWriter(buysum,suplysum,result,toFileName);

    }

    private String[] fileReader(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file", e);
        }
    }

    private void fileWriter(int suplysum, int buysum, int result, String paths) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("supply,").append(buysum).append(System.lineSeparator())
                .append("buy,").append(suplysum).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        try {
            Files.writeString(Paths.get(paths), stringBuilder.toString(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("Cant write data from file", e);
        }
    }
}
