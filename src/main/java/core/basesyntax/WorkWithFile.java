package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getSupply() {
        return supply;
    }

    public int getBuy() {
        return buy;
    }

    public String[] operations(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String row = reader.readLine();
            while (row != null) {
                stringBuilder.append(row).append(";");
                row = reader.readLine();
            }
            return stringBuilder.toString().split(";");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file " + fromFileName + "!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName + "!", e);
        }
    }

    public void calculateStatistic(String[] operations) {
        if (operations != null) {
            setSupply(0);
            setBuy(0);
            for (String operation : operations) {
                if (operation.substring(0, operation.indexOf(",")).equals("supply")) {
                    setSupply(getSupply() + Integer.parseInt(operation.substring(7)));
                } else {
                    setBuy(getBuy() + Integer.parseInt(operation.substring(4)));
                }
            }
        } else {
            throw new RuntimeException("No data to calculate is found!");
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        calculateStatistic(operations(fromFileName));
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write("supply," + getSupply() + System.lineSeparator()
                                    + "buy," + getBuy() + System.lineSeparator()
                                    + "result," + (getSupply() - getBuy()));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName + "!", e);
        }
    }
}
