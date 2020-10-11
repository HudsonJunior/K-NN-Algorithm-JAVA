//package com.company;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.FileNotFoundException;

public class Main {
    private static String fileNameTrain, fileNameTest,typeFileTest, sizeFileTest, typeFileTrain, sizeFileTrain;
    private static int k;


    public static void main(String[] args) {
        System.out.println("Digite: nome do arquivo train + nome do arquivo test + k");

        try {
            fileNameTrain = args[0];
            fileNameTest = args[1];
            k = Integer.valueOf(args[2]);

            String[] fileTestSplited = fileNameTest.split("_");

            typeFileTest = fileTestSplited[0];
            sizeFileTest = fileTestSplited[1];

            String[] fileTrainSplited = fileNameTrain.split("_");

            typeFileTrain = fileTrainSplited[0];
            sizeFileTrain = fileTrainSplited[1];

            if (Helpers.tipoValidos(typeFileTest, typeFileTrain) && Helpers.tamanhosValidos(sizeFileTest, sizeFileTrain)) {
                File Base = new File(fileNameTrain);

                File Test = new File(fileNameTest);

                int i = 1;

                Scanner testScanner = new Scanner(Test);

                while (testScanner.hasNextLine()) {
                    String x = testScanner.nextLine();

                    String[] sampleSplited = x.split(",");
                    List<String> sampleTest = new ArrayList<String>();
                    sampleTest = Arrays.asList(sampleSplited);

                    int lastIndex = sampleTest.size();

                    String classeTest = sampleTest.get(lastIndex - 1);

                    List<Double> X;
                    sampleTest.remove(lastIndex);

                    X = Helpers.stringListToDouble(sampleTest);

                    String result = knn.Classificar(Base, X, k);

                    System.out.println("Análise exemplo de teste" + i);
                    System.out.println("Classe preditada:" + "result" + "\nClasse do exemplo:" + classeTest);

                    if("result".equals(classeTest))
                        System.out.println("Sucesso na predição");
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
    }
}
