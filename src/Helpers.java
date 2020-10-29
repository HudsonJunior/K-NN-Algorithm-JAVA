import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;

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

    public static HashMap<String, Object> fileToList(String fileName){
        String[] sampleSplited;
        List<Double> P;
        List<String> sampleTrain;
        String classeTrain;
        List<List<Double>> listListDouble = new ArrayList<>();
        List<String> classedList = new ArrayList<>();
        int lastIndex;

        try{
            List<String> resultado;
            HashMap<String, Object> params = new HashMap<>();

            Stream<String> lines = Files.lines(Paths.get(fileName));
            resultado = lines.collect(Collectors.toList());

            for (String x: resultado){
                sampleSplited = x.split(",");

                sampleTrain = new LinkedList<String>(Arrays.asList(sampleSplited));

                lastIndex = sampleTrain.size() - 1;

                classeTrain = sampleTrain.remove(lastIndex);

                P = new ArrayList<Double>(Helpers.stringListToDouble(sampleTrain));
                
                listListDouble.add(P);

                classedList.add(classeTrain);
                
            }

            params.put("listDouble", listListDouble);
            params.put("classedList", classedList);

            return params;

        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao transformar arquivo em string.\n" + e.getMessage());
            return null;
        }
    }
}
