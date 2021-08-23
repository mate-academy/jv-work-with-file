package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private final static int OPERATION_TYPE = 0;
    private final static int AMOUNT = 1;
    private final static String SUPPLY = "supply";
    private final static String COMMA = ",";
    private final static String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFile(fromFileName);
        writeFile(result, toFileName);
    }

    public String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            String[] lineData;
            StringBuilder builder = new StringBuilder();
            int buy = 0;
            int supply = 0;
            while (line != null) {
                lineData = line.split(COMMA);
                if (lineData[OPERATION_TYPE].equals(SUPPLY)) {
                    supply += Integer.parseInt(lineData[AMOUNT]);
                }
                if (lineData[OPERATION_TYPE].equals(BUY)) {
                    buy += Integer.parseInt(lineData[AMOUNT]);
                }
                line = reader.readLine();
            }
            builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                    .append("result").append(COMMA).append(supply - buy);
            return builder.toString();
        } catch(IOException e) {
        throw new RuntimeException("Can't read file", e);
    }

}

    private void writeFile(String toWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(toWrite);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
