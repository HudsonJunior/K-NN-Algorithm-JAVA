import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class knn {

    public static String Classificar(List<List<Double>> B, List<Double> X, int k, List<String> exemplosList) throws FileNotFoundException{
        Double distancia;
        String[] sampleSplited;
        List<Double> P;
        List<String> sampleTrain;
        String classeTrain, E;
        Exemplo exemplo;
        List<Exemplo> ordeneredList;
        List<Exemplo> kList;
        int i = 0;
        int lastIndex;
        List<Exemplo> novaListaExemplo = new ArrayList<>();

        Scanner baseScanner = null;
        
        try {

            for (List<Double> E: B) {

                // sampleSplited = E.split(",");

                // sampleTrain = new LinkedList<String>(Arrays.asList(sampleSplited));

                // lastIndex = sampleTrain.size() - 1;

                // classeTrain = sampleTrain.remove(lastIndex);

                // P = new ArrayList<Double>(Helpers.stringListToDouble(sampleTrain));

                distancia = Helpers.calculaDistancia(P, X);

                classeTrain = listClassesBase.get(i);

                Exemplo exemplo = new Exemplo(classeTrain, distancia);

                novaListaExemplo.add(exemplo);
                
                i++;
            }

            Collections.sort(novaListaExemplo);

            
            ordeneredList = new ArrayList<Exemplo>(novaListaExemplo);

            kList = new ArrayList<Exemplo>(ordeneredList.subList(0, k)); 

            return classeMaisFrequente(kList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um problema ao classificar o exemplo.\n" + e.getMessage());
            throw e;

        } finally{
        }
    }

    private static String classeMaisFrequente(List<Exemplo> List) {
        List<String> classes = new ArrayList<>();
        List<String> classesMaisFrequentes = new ArrayList<>();
        int counter = 0, curr_frequency;
        String classeFrequente;

        try{

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
                
                if (Collections.frequency(classes, i) == counter && !classesMaisFrequentes.contains(i)) {
                    classesMaisFrequentes.add(i);
                }
            }
    
            if (classesMaisFrequentes.size() == 1)
                return classeFrequente;
            else
                return classesMaisFrequentes.get(0);

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Ocorreu um problema ao determinar a classe mais frequente.\n" + e.getMessage());
            throw e;
        }
    }
}
