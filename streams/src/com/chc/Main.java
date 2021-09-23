package com.chc;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static List<User> users;
    private static Optional<List<Boolean>> actives;

    public static void main(String[] args) {
	// Un stream es un conjunto de funciones que se ejecutan de forma anidada , las más conocidas nos permiten reducir hacer , map
        // son envoltorios de una fuente de datos , no almacena datos.


        System.out.println("--------------------------FOREACH-----------------------------");

        setUpUser();
        Stream stream = Stream.of(users);
        users.stream();

        users.stream().forEach(e -> e.setNombre(e.getNombre() + " Apellido"));
        imprimirLista();
        System.out.println("--------------------------MAP-----------------------------");
        /*
             Map : Nos permite realizar una transformación rápida de los datos del flujo original del stream
         */
        List<String> listaString =
                users.stream()
                .map(User::getNombre)
                .collect(Collectors.toList());

        listaString.forEach(System.out::println);

    /*
        Filter: Contiene elementos de un Stream original que han pasado por unas determinadas pruebas especificadas por un predicado.
     */
        System.out.println("--------------------------FILTERS-----------------------------");
        setUpUser();
        List<User> userFilter = users.stream()
                .filter(e -> !e.getNombre().equals("Jose"))
                .filter(e -> e.getId()<3)
                .collect(Collectors.toList());

        userFilter
                .stream()
                .forEach(e -> System.out.println(e.getId() + " " + e.getNombre()));
      /*
        FindFirst:  Buscamos el primer resultado
     */
        System.out.println("--------------------------FIND FIRST-----------------------------");
        setUpUser();
        User user = users.stream()
                .filter(e -> e.getNombre().equals("Jose"))
                .findFirst()
                .orElse(null);
        System.out.println(user.getId() + " " + user.getNombre());
      /*
        FlatMap: Tiene los datos de diferentes arrays y los concatenan en un solo string, podemos tener varias listas
        y juntarlas en una sola lista. Imaginemos que nos  vienen varias fuentes de datos como puede ser usuarios pero
        divididos por puesto de trabajo y queremos saber quien cobra más sin importar los puestos , pues haríamos un
        flatMap para juntar todos los datos.
     */
        System.out.println("--------------------------FlatMap-----------------------------");

        List<List<String>> nombresVariasListas = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("Jose","María", "Pedro", "Gustavo")),
                        new ArrayList<>(Arrays.asList("Monica", "Pablo", "Moro"))));
        List<String> nombresUnicaLista = nombresVariasListas.stream()
                .flatMap(e -> e.stream())
                .collect(Collectors.toList());
        nombresUnicaLista.stream().forEach(System.out::println);
      /*
        Peek : Es similar a forEach , pero sin ser un final , quiere decir que puedes seguir haciendo cosas en el stream.
        En el stream tenemos métodos intermedios , es decir que acontinuación de ellos puede seguir habiendo más métodos
        Collect es un método final , forEach ... para verlo se puede comprobar poniendo un punto al final.
        Con Peek podemos recorrer la lista y seguir haciendo operaciones

     */

        System.out.println("--------------------------Peek-----------------------------");
        setUpUser();

        List<User> users2 = users.stream()
                .peek(e -> e.setNombre(e.getNombre() + " " + " Apellido"))
                .collect(Collectors.toList());
        users2.stream().forEach(e -> System.out.println(e.getId()+ " " + e.getNombre()));

              /*
        Count : Nos devuelve los elementos del Stream , en una línea de código podemos recoger un número de resultados
        con una condición.
     */
        System.out.println("--------------------------Count-----------------------------");
        setUpUser();

        long numeroFiltrado = users.stream()
                .filter(e -> e.getId()<3)
                .count();
        System.out.println(numeroFiltrado);

        /*
        Skip y limit : Skip , nos salta los primeros x elementos que le indiquemos y limit nos limita el número de elementos
        que queremos obtener
        skip, Imagina que los primeros 1000 elementos no nos valen y luego empezamos a operar.
        limit: Maracas el máximo de los elementos
     */
        System.out.println("--------------------------Skip y limit-----------------------------");
        setUpUser();
        String[] abc = {"a","b","c","e","f","g","h","i","j","k","..."};
        List<String> abcFinter = Arrays.stream(abc)
                .skip(2)
                .limit(4)
                .collect(Collectors.toList());
        abcFinter.forEach(System.out::println);

        /*
        Sort : nos ordena la lista de elementoss
        */
        System.out.println("--------------------------Sorted-----------------------------");
        setUpUser();
        users = users.stream()
                .sorted(Comparator.comparing(User::getNombre))
                .collect(Collectors.toList());
        imprimirLista();
        /*
        Min Max : Nos permite obtener el valor mínimo o el valor máximo de la lista
        */
        System.out.println("--------------------------Min Max-----------------------------");
        setUpUser();
        User userMin = users.stream()
                .min(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMin.getId());
        User userMax = users.stream()
                .min(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMax.getId());
        /*
        Distinct : Nos permite eliminar los valores repetidos
        */
        System.out.println("--------------------------Distinct-----------------------------");
        String[] abce = {"a","b","c","a","b","c","e","f","g","h","i","j","k","..."};
        List<String> abcFilter1 = Arrays.stream(abce).distinct().collect(Collectors.toList());
        abcFilter1.forEach(System.out::println);
        System.out.println(Arrays.asList(abce));

        /*
        AllMatch, anyMatch, noneMatch :  AllMatch verifica si el predicado es verdadero,
         verifica si un valor es verdadero y nonematch verifica si ninguno pasa el predicado

        */
        System.out.println("--------------------------AllMatch, anyMatch, noneMatch-----------------------------");
        List<Integer> listaNumeros = Arrays.asList(100,300,900,5000);

        //Todos  superan 300
        boolean allMatch = listaNumeros.stream().allMatch(e->e>300);
        System.out.println(allMatch);
        //Al menos uno supera 301
        boolean anyMatch = listaNumeros.stream().anyMatch(e->e>301);
        System.out.println(anyMatch);
        //Ninguno supera los 10000
        boolean noneMatch = listaNumeros.stream().noneMatch(e->e>300);
        System.out.println(noneMatch);

        /*
        SuM averange range: Nos va a permitir sumar , la media y el range para hacer la implentación en elementos

        */
        System.out.println("--------------------------SuM averange range-----------------------------");
        setUpUser();
        double result = users.stream()
                .mapToInt(User::getId)
                .average()
                .orElse(0);
        System.out.println(result);

        result = users.stream()
                .mapToInt(User::getId)
                .sum();
        System.out.println(result);
//Nos proporciona un rango y luego hacemos la suma de ese rango
        System.out.println(IntStream.range(0,100).sum());

                /*
        Reduce: Fol , toma el string y los combina en un unico resultado mediante la acción repetida de una aplicación

        */
        System.out.println("--------------------------Reduce-----------------------------");
        setUpUser();
        int numero = users.stream()
                .map(User::getId)
                .reduce(0, Integer::sum);
        System.out.println(numero);

                        /*
        Joining: devuelve un recompilador que contatena la secuenca de charsecuency y lo devuelve como una cadena
        Nos llega como una lista de nombre de usuarios y los mostramos como un único string separado por un guión

        */
        System.out.println("--------------------------Joining-----------------------------");
        setUpUser();
        String names = users.stream()
                .map(User::getNombre)
                .collect(Collectors.joining(" - "));
        System.out.println(names);

        /*
        toSet: Nos devuelve un collector que acumula los elementos de entrada en un nuevo set

        */
        System.out.println("--------------------------toSet-----------------------------");
        setUpUser();
        Set<String> setNames = users.stream()
                .map(User::getNombre)
                .collect(Collectors.toSet());
        setNames.forEach(System.out::println);
        /*
        sumarizingDouble: Nos devuelve estadisticas muy interesantes en una variable doubleSumaryStatistics

        */
        System.out.println("--------------------------sumarizingDouble-----------------------------");
        setUpUser();
        DoubleSummaryStatistics statistics = users.stream()
                .collect(Collectors.summarizingDouble(User::getId));
        System.out.println(statistics.getAverage() + " " +
                                   statistics.getMax() + " " +
                                   statistics.getMin() + " " +
                                   statistics.getCount() + " " +
                                   statistics.getSum());
        //Segunda forma de hacerlo
        DoubleSummaryStatistics statistics1 = users.stream()
                .mapToDouble(User::getId)
                .summaryStatistics();
        System.out.println(statistics1.getAverage() + " " +
                                   statistics1.getMax() + " " +
                                   statistics1.getMin() + " " +
                                   statistics1.getCount() + " " +
                                   statistics1.getSum());

        /*
        PartitioningBy:  Nos va a devolver nuestra lista de elementos divida en 2 en una que se cumpla el predicado
        y otra en la que no
        Imaginamos que tenemos 2 salarios distiontos de salarios por directivos.

        */
        System.out.println("--------------------------PartitioningBy-----------------------------");
        setUpUser();
        List<Integer> numeros = Arrays.asList(5,7,8,6,2,1,4,7,3123);
        Map<Boolean, List<Integer>> esMayor = numeros.stream()
                .collect(Collectors.partitioningBy(e ->e>10));
        esMayor.get(true).forEach(System.out::println);
        esMayor.get(false).forEach(System.out::println);
        /*
        groupingBy: Agrupamos según queramos , se debe indicar la propiedad por la cual se debe indicar la agrupación

        */
        System.out.println("--------------------------groupingBy-----------------------------");
        setUpUser();
        Map<Character, List<User>> grupoAlfabetico = users.stream()
                //Aquí es donde indicamos como queremos agrupar y los criterios.
                .collect(Collectors.groupingBy(e -> e.getNombre().charAt(0)));
        grupoAlfabetico.get('J').forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('A').forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('C').forEach(e -> System.out.println(e.getNombre()));
        /*
        mapping:
        */
        System.out.println("--------------------------mapping-----------------------------");
        setUpUser();
        List<String> personas = users.stream()
                .map(User::getNombre)
                .collect(Collectors.toList());
        personas.forEach(System.out::println);
        /*
        stream paralelo: Es la forma de utilizar el stream por diferentes hilos para reducir los tiempos de ejecución
        Se reducen los tiempos de ejcución casi en 5 partes ... es un 400% más rápido.
        Puede ser muy útil  cuando tienes que hacer operaciones largas para reducir el tiempo
        */
        System.out.println("--------------------------stream paralelo-----------------------------");
        setUpUser();
        long tiempos = System.currentTimeMillis();
        listaString.forEach(Main::convertirAMayusculas);
        long tiempos2 = System.currentTimeMillis();
        System.out.println("Normal " + (tiempos-tiempos2));
        tiempos = System.currentTimeMillis();
        listaString.parallelStream().forEach(Main::convertirAMayusculas);
        tiempos2 = System.currentTimeMillis();
        System.out.println("Paralelo: " + (tiempos-tiempos2));


        /*
        Cuando tengas que hacer algo con una lista , casi seguro que un stream pueda resolverlo por tí
        Recapitulación sobre los Streams :
            Tenemos una fuente de datos : Una lista , un collection , un array etc ...
            Através del stream indicamos unas operaciones intermedias como filter, map, peek..
            Por último siempre tendremos una operación final para devolver el tipo de dato que hemos declarado.
         */

    }
    private static String convertirAMayusculas ( String nombre){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return nombre.toUpperCase();
    }

    private static void setUpUser(){
        users = new ArrayList<>();

        users.add(new User(1, "Jose"));
        users.add(new User(2, "Alfredo"));
        users.add(new User(3, "Pepe"));
        users.add(new User(4, "Carlos"));
        users.add(new User(5, "Rocío"));
        users.add(new User(5, "Jose"));
    }

    private static void imprimirLista(){
        users.stream().forEach(e -> System.out.println(e.getId() + " " + e.getNombre()));
    }

/*    //Proponer para WebStarter , o cambiar a boolean normal , además de mirar como hacerlo con una lambda
    public static Optional<Boolean> decide(Optional<List<Boolean>> actives) {


        return actives.map(multiplesActive -> {
            if(multiplesActive.containsAll(Arrays.asList(true, false))) {
                return null;
            }
            else if(multiplesActive.contains(true)) {
                return Boolean.TRUE;
            }
            else if(multiplesActive.contains(false)) {
                    return Boolean.FALSE;
                }
                else {
                    return null;
                }
        });
    }*/
}
