package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        createReport(toFileName);
    }

    public void readFile(String fromFileName) {
        File fileIn = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String value = reader.readLine();
            while (value != null) {
                String[] numbersString = value.split(",");
                if (numbersString[0].equals("supply")) {
                    supply = supply + Integer.parseInt(numbersString[1]);
                }
                if (numbersString[0].equals("buy")) {
                    buy = buy + Integer.parseInt(numbersString[1]);
                }
                value = reader.readLine();
            }
            result = supply - buy;
        } catch (IOException e) {
            System.out.println("Can`t read file with name" + fileIn);
        }
    }

    public void createReport(String toFileName) {
        File fileOut = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut, true))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Can`t write data to file" + fileOut);
        }
    }
}


