package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_VALUE_INDEX = 1;
    private  static  final  String  SUPPLY = "supply" ;
    private  static  final  String  BUY = "buy" ;
    private  static  final  String  RESULT = "result" ;
    private  static  final  String  SEPARATOR = "," ;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = calculateReport (dataFromFile);
        writeIntoFile (report, toFileName);
    }
    private void writeIntoFile (String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName,false))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write file data" + toFileName, e);
        }
    }
    private String[] readFromFile(String filepath) {
        try {
        List<String> lines = Files.readAllLines(Path.of(filepath));
        return lines.toArray(new String[]{});
    } catch (IOException e) {
        throw new RuntimeException("Unable to read data from file " + filepath, e);
        }
    }
    private String calculateReport (String[] report) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String reports : report) {
            String[] recortSplit = reports.split(SEPARATOR);
            String operationType = recortSplit[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(recortSplit[OPERATION_VALUE_INDEX]);
            if (SUPPLY.equals(operationType)) {
                totalSupply += amount;
            } else if (BUY.equals(operationType)) {
                totalBuy += amount;
            }
        }
            int result = totalSupply - totalBuy;
            StringBuilder stats = new StringBuilder()
                    .append(SUPPLY).append(totalSupply).append(System.lineSeparator())
                    .append(BUY).append(totalBuy).append(System.lineSeparator())
                    .append(RESULT).append(result);
            return stats.toString();
    }
}
