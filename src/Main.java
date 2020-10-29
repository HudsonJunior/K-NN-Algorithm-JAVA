import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
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

            List<List<Double>> listDoubleBase = (List<List<Double>>) paramsTrain.get("listDouble");
            
            List<List<Double>> listDoubleTest = (List<List<Double>>) paramsTest.get("listDouble");

            List<String> listClassesBase = (List<String>) paramsTrain.get("classedList");

            List<String> listClassesTest = (List<String>) paramsTest.get("classedList");

            String[] fileTestSplited = fileNameTest.split("_");

            typeFileTest = fileTestSplited[0];
            sizeFileTest = fileTestSplited[1];

            String[] fileTrainSplited = fileNameTrain.split("_");

            typeFileTrain = fileTrainSplited[0];
            sizeFileTrain = fileTrainSplited[1];
            
            if((Helpers.tipoValidos(typeFileTest, typeFileTrain)) && (Helpers.tamanhosValidos(sizeFileTest, sizeFileTrain))) {

                int i = 0;

                for (List<Double> X: listDoubleTest) {

                    String result = knn.Classificar(listDoubleBase, X, k, listClassesBase);

                    if(result.equals(listClassesTest.get(i))){
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
