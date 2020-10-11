//package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class knn {

    public static String Classificar(File B, List<Double> X, int k) {
        Double distancia;
        String[] sampleSplited;
        List<Double> P;
        List<String> sampleTrain;
        String classeTrain, E;
        Exemplo exemplo;
        List<Exemplo> exemplosList = new ArrayList<>();
        List<Exemplo> ordeneredList = new ArrayList<>();
        List<Exemplo> kList;

        int lastIndex;

        try {
            Scanner testScanner = new Scanner(B);

            while (testScanner.hasNextLine()) {
                E = testScanner.nextLine();

                sampleSplited = E.split(",");

                sampleTrain = Arrays.asList(sampleSplited);

                lastIndex = sampleTrain.size();

                classeTrain = sampleTrain.get(lastIndex - 1);

                sampleTrain.remove(lastIndex);

                P = Helpers.stringListToDouble(sampleTrain);

                distancia = calculaDistancia(P, X);

                exemplo = new Exemplo(classeTrain, distancia);

                exemplosList.add(exemplo);
            }

            Collections.sort(exemplosList);

            ordeneredList = exemplosList;

            kList = ordeneredList.subList(0, k);

            return classeMaisFrequente(kList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String classeMaisFrequente(List<Exemplo> List) {
        List<String> classes = new ArrayList<>();
        List<String> classesMaisFrequentes = new ArrayList<>();
        int counter = 0, curr_frequency;
        String classeFrequente;

        for (Exemplo e : List) {
            classes.add(e.getClasse());
        }

        classeFrequente = classes.get(0);

        for (String i : classes) {
            curr_frequency = Collections.frequency(classes, i);

            if (curr_frequency > counter) {
                counter = curr_frequency;
                classeFrequente = i;
            }
        }

        for (String i : classes) {
            if (Collections.frequency(classes, i) == counter && !classes.contains(i)) {
                classesMaisFrequentes.add(i);
            }
        }

        if (classesMaisFrequentes.size() == 1)
            return classeFrequente;
        else
            return classesMaisFrequentes.get(0);

    }

    private static Double calculaDistancia(List<Double> P, List<Double> Q) {
        Double soma = 0.0;
        Double absValue = 0.0;

        for (int i = 0; i < P.size(); i++) {
            absValue = Math.abs(P.get(i) - Q.get(i));

            soma += absValue;
        }

        return soma;
    }
}
