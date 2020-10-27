import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.FileNotFoundException;

public class Main {
    private static String fileNameTrain, fileNameTest,typeFileTest, sizeFileTest, typeFileTrain, sizeFileTrain;
    private static int k;


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        HashMap<String, Object> paramsTrain;
        HashMap<String, Object> paramsTest;

        Scanner testScanner = null;
        int count = 0;

        try {
            fileNameTrain = args[0];
            fileNameTest = args[1];
            k = Integer.valueOf(args[2]);

            paramsTrain = Helpers.fileToList(fileNameTrain);
            paramsTest = Helpers.fileToList(fileNameTest);

            List<List<Double>> listDoubleBase = paramsTrain.get("listDouble");
            
            List<List<Double>> listDoubleTest = paramsTest.get("listDouble");

            List<String> listClassesBase = paramsTrain.get("classedList");

            List<String> listClassesTest = paramsTest.get("classedList");

            String[] fileTestSplited = fileNameTest.split("_");

            typeFileTest = fileTestSplited[0];
            sizeFileTest = fileTestSplited[1];

            String[] fileTrainSplited = fileNameTrain.split("_");

            typeFileTrain = fileTrainSplited[0];
            sizeFileTrain = fileTrainSplited[1];
            
            if((Helpers.tipoValidos(typeFileTest, typeFileTrain)) && (Helpers.tamanhosValidos(sizeFileTest, sizeFileTrain))) {

                int i = 1;

                for (List<Double> X: listDoubleTest) {

                    // String[] sampleSplited = x.split(",");

                    // List<String> sampleTest = new LinkedList<String>(Arrays.asList(sampleSplited));
                

                    // int lastIndex = sampleTest.size() - 1;

                    // List<Double> X;
 
                    // String classeTest = sampleTest.remove(lastIndex);

                    // X = Helpers.stringListToDouble(sampleTest);

                    String result = knn.Classificar(listDoubleBase, X, k);

                    if(result.equals(classeTest)){
                        System.out.println("Sucesso na predição");
                        count++;
                    }
                    else
                        System.out.println("Falha na predição");
                    
                    i = i + 1;

                }
            }
            else{
                System.out.println("Arquivos incompatíveis!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println(count);

            long stopTime = System.nanoTime();
            System.out.println("TEMPO DE EXECUÇÃO" + (stopTime - startTime));
        }
    }
}
