package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try (
                BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            int supply = 0;
            int buy = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
            }
            builder.append("supply,").append(supply).append(System.lineSeparator());
            builder.append("buy,").append(buy).append(System.lineSeparator());
            builder.append("result,").append(supply - buy);
            String report = builder.toString();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write(fromFileName);


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}