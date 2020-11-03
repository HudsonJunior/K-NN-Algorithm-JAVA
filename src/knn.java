import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class knn {

    public static String Classificar(List<List<Double>> B, List<Double> X, int k, List<String> listClassesBase) throws FileNotFoundException{
        Double distancia;
        Exemplo exemplo;
        List<Exemplo> ordeneredList;
        List<Exemplo> kList;
        int i = 0;
        String classeTrain;
        List<Exemplo> novaListaExemplo = new ArrayList<>();
        
        try {

            for (List<Double> E: B) {

                distancia = Helpers.calculaDistancia(E, X);

                classeTrain = listClassesBase.get(i);

                exemplo = new Exemplo(classeTrain, distancia);

                novaListaExemplo.add(exemplo);
                
                i++;
            }

            Collections.sort(novaListaExemplo);

            ordeneredList = new ArrayList<Exemplo>(novaListaExemplo);

            kList = new ArrayList<Exemplo>(ordeneredList.subList(0, k)); 

            return classeMaisFrequente(kList);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocorreu um problema ao classificar o exemplo.\n" + e.getMessage());
            return null;

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
