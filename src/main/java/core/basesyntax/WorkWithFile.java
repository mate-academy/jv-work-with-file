package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        File fromFile = new File(fromFileName);
//        int supplyCounter = 0;
//        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                String[] dataFromFile = (bufferedReader.readLine()).split(",");
                String result = getResult(dataFromFile);
                value = bufferedReader.read();
            }
            } catch(IOException e){
                throw new RuntimeException("Input/Output error for file " + fromFileName + "!", e);
            }
        File toFile = new File(toFileName);
        WorkWithFile workWithFile = new WorkWithFile();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(getResult());
        } catch (FileNotFoundException e){
            throw new RuntimeException("Error! File " + toFileName + " not found!", e);
        } catch(IOException e){
            throw new RuntimeException("Input/Output error for file " + toFileName + "!", e);
        }
        }

    private String getResult(String[] dataFromFile) {
        int supplyCounter = 0;
        int buyCounter = 0;
          if (dataFromFile[0].equals("supply")) {
            supplyCounter += Integer.getInteger(dataFromFile[1]);
          } else {
            buyCounter += Integer.getInteger(dataFromFile[1]);
          }
        return ("supply, " + supplyCounter + System.lineSeparator() + "bye, " + buyCounter
                + System.lineSeparator() + "result, " + (supplyCounter - buyCounter));
    }


}

