//package com.company;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static List<Double> stringListToDouble(List<String> list) {
        List<Double> listDouble = new ArrayList<Double>();
        for (String i : list) {
            listDouble.add(Double.parseDouble(i));
        }

        return listDouble;
    }

    public static boolean tipoValidos(String tipoTest, String tipoTrain) {

        if (!tipoTest.equals("test") && !tipoTrain.equals("train"))
            return false;

        return true;
    }

    public static boolean tamanhosValidos(String sizeTest, String sizeTrain) {
        if (!sizeTest.equals(sizeTrain))
            return false;

        return true;
    }
}
