package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";
    private static final int FIRST_PARAMETER = 0;
    private static final int SECOND_PARAMETER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value= reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("read error!" + e);
        }
        String[] infoReport = builder.toString().split("\r?\n|\r");
        ReportData reportData = new ReportData();
        String[] infoCell = new String[2];
        for (String infoElement: infoReport) {
            infoCell = infoElement.split(",");
            if (infoCell[FIRST_PARAMETER].equals(SUPPLY_NAME)) {
                reportData.addSupply(Integer.parseInt(infoCell[SECOND_PARAMETER]));
            } else if (infoCell[FIRST_PARAMETER].equals(BUY_NAME)) {
                reportData.addBuy(Integer.parseInt(infoCell[SECOND_PARAMETER]));
            }
        }
        StringBuilder finalBuilder = new StringBuilder();
        finalBuilder.append(SUPPLY_NAME + ","
                + reportData.getSupply() + System.lineSeparator()
                + BUY_NAME + ","
                + reportData.getBuy() + System.lineSeparator()
                + RESULT_NAME + ","
                + reportData.getResult() + System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(finalBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("write error!" + e);
        }
    }
}
