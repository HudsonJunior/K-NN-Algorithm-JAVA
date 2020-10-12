package src;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static List<Double> stringListToDouble(List<String> list) {

        try{
            List<Double> listDouble = new ArrayList<Double>();
            for (String i : list) {
                listDouble.add(Double.parseDouble(i));
            }
    
            return listDouble;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao converter a lista de strings para double.\n" + e.getMessage());
            throw e;
        }
        
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

    public static Double calculaDistancia(List<Double> P, List<Double> Q) {
        try{
            Double soma = 0.0;
            Double absValue = 0.0;
    
            for (int i = 0; i < P.size(); i++) {
                absValue = Math.abs(P.get(i) - Q.get(i));
    
                soma += absValue;
            }
    
            return soma;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao calcular a distancia entre os pontos.\n" + e.getMessage());
            throw e;
        }   
    }
}
