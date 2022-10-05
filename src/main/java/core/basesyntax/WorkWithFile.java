package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private int fullSupply;
    private int fullBuy;
    private String caseSupplyOrBuy;
    private String amountOfBuyOrSupply;

    public void getStatistic(String fromFileName, String toFileName) {
        this.fullBuy = 0;
        this.fullSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] refactored = line.split(",");
                caseSupplyOrBuy = refactored[0];
                amountOfBuyOrSupply = refactored[1];
                totalSupplyAndBuyReport();
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read this file" + fromFileName, e);
        }
        writeToFile(toFileName);
    }

    private void totalSupplyAndBuyReport() {
        switch (caseSupplyOrBuy) {
            case SUPPLY:
                fullSupply += Integer.parseInt(amountOfBuyOrSupply);
                break;
            case BUY:
                fullBuy += Integer.parseInt(amountOfBuyOrSupply);
                break;
            default:
                break;
        }
    }

    private String workWithSupplyAndBuyValues() {
        int result = fullSupply - fullBuy;
        return SUPPLY + "," + fullSupply
                + System.lineSeparator() + BUY
                + "," + fullBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(workWithSupplyAndBuyValues());
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file" + toFileName, e);
        }
    }
}
