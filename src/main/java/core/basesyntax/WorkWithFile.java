package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
        crateReport(readFromFile(fromFileName));
    }

    public String readFromFile(String fromFileName) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append("/");
            }
            return stringBuilder.toString();
        }
        catch (IOException e) {throw new IOException("WTF?!");}
    }

    public String crateReport(String readFromFile) {
        String[] fileLines = readFromFile.split("/");
        int supplySum = 0;
        int buySum = 0;
        int result = supplySum - buySum;
        for (String fileLine : fileLines) {
            if (fileLine.contains("supply")) {
                String[] supplyLine = fileLine.split(",");
                supplySum += Integer.parseInt(supplyLine[1]);
            } else {
                String[] buyLine = fileLine.split(",");
                buySum += Integer.parseInt(buyLine[1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("supply,");
        return stringBuilder.append(supplySum).append("\n").append("buy,")
                .append(buySum).append("\n").append("result,").append(result).toString();
    }
}
