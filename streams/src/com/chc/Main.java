package com.chc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static List<User> users;
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
        Recapitulación sobre los Streams :
            Tenemos una fuente de datos : Una lista , un collection , un array etc ...
            Através del stream indicamos unas operaciones intermedias como filter, map, peek..
            Por último siempre tendremos una operación final para devolver el tipo de dato que hemos declarado.
         */

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
}
