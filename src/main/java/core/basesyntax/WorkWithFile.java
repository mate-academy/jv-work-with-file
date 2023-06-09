package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String[] fileInfo = workWithFile.readFile(fromFileName).split(System.lineSeparator());
        String report = workWithFile.createReport(fileInfo);


    }

    public String readFile(String fromFileName) {
        File fileWithInfo = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileWithInfo);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String info = bufferedReader.readLine();
            while (info != null) {
                stringBuilder.append(info).append(System.lineSeparator());
                info = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String[] fileInfo) {
        String[] lineInfo;
        int numberOfSupply = 0, numberOfBuy = 0, totalNumber = 0;
        StringBuilder report = new StringBuilder();
        for (String info : fileInfo) {
            lineInfo = info.split(",");
            if (lineInfo[OPERATION_TYPE].equals(SUPPLY)) {
                numberOfSupply += Integer.parseInt(lineInfo[AMMOUNT]);
                totalNumber += numberOfSupply;
            } else if (lineInfo[OPERATION_TYPE].equals(BUY)) {
                numberOfBuy += Integer.parseInt(lineInfo[AMMOUNT]);
                totalNumber -= numberOfBuy;
            }
        }
        report.append("supply," + numberOfSupply)
                .append(System.lineSeparator())
                .append("buy," + numberOfBuy)
                .append(System.lineSeparator())
                .append("result," + totalNumber);
        return report.toString();
    }
}