package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        File fromFile = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                String[] dataFromFile = (bufferedReader.readLine()).split(",");
                getResult(dataFromFile);
                value = bufferedReader.read();
            }
            } catch(FileNotFoundException e){
                throw new RuntimeException("Error! File " + fromFileName + " not found!", e);
            } catch(IOException e){
                throw new RuntimeException("Input/Output error for file " + fromFileName + "!", e);
            }
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write("supply, " + supplyCounter);
        } catch (FileNotFoundException e){
            throw new RuntimeException("Error! File " + toFileName + " not found!", e);
        } catch(IOException e){
            throw new RuntimeException("Input/Output error for file " + toFileName + "!", e);
        }
        }

    public void getResult(String[] dataFromFile) {
        int supplyCounter = 0;
        int buyCounter = 0;
          if (dataFromFile[0].equals("supply")) {
            supplyCounter += Integer.getInteger(dataFromFile[1]);
          } else {
            buyCounter += Integer.getInteger(dataFromFile[1]);
          }
    }


}

