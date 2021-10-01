package com.chc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main implements SumarInterface  {
    /**
     * Funciones de orden superior
     * -  Pueden recibir una función como parámetro de entrada
     * - Pueden devolver una función como un return
     * <p>
     * Con que se cumpla cualuera de estas 2 afirmaciones es una función de orden superior
     */

    public static void main(String[] args) {


        Main hof = new Main();
        // -------------------------Función-----------------------//
        System.out.println(hof.suma(2, 3));
        //----------------------------Interfaz-----------------------//
        System.out.println(hof.apply(3, 8));
        //--------------------------High Order Functions----------------//
        SumarInterface sumarInterface = Integer::sum;
        System.out.println("High Order " + hof.sumarHighFunction(sumarInterface, 5, 60));
        //--------------------------Interfaz funcional Function----------------//
        /*
         * La interfaz funcional Function se declara
         * interface function<T t, R t>{
         *     R apply (T t)
         * }
         *  T -> parámetro de entrada
         *  R -> Return que puede ser cualquiera.
         * Esto nos evita ir creando interfaces como la de sumarInterface
         *
         */
        Function<String, String> convertirMayusculas = e -> e.toUpperCase();
        hof.imprimirMayusculas(convertirMayusculas, "carlos");
        //--------------------------Interfaz funcional BiFunction<T,U,R>----------------//
        /*
         * La interfaz funcional Function se declara
         * interface function<T,U,R >{
         *     R apply (T t, U u)
         * }
         * T -> Primer Parámetro de entrada
         * U -> Segundo parametro de entrada
         * R -> Retorno
         */

        //--------------------------Interfaz funcional Predicate<T>----------------//
        /*
            interface Predicate<T>{
                Boolean text (T t)
            }
            T -> parámetro de entrada
            con un método text que nos devuelve un Boolean
            Un predicado es una interfaz funcional que define una condición que un objeto determinado debe cumplir
            Es una función que nos devuelve verdadero o falto ; Ejemplo , que los números sean mayores que 0
            que la palabra que buscas sea X , etc...
         */

        List<Integer> numeros = Arrays.asList(6, 26, 56, 89, -5, -8, -6, -7);
        BiFunction<
                List<Integer>,
                Predicate<Integer>,
                List<Integer>
                > filtrar;
        filtrar = (lista, predicado) -> lista.stream().filter(e -> predicado.test(e)).collect(Collectors.toList());
        System.out.println(filtrar.apply(numeros, e -> e > 0));

        //--------------------------Interfaz Funcional Cosumer<T>----------------//
        /*
            interface Consumer<T>{
                void text (T t)
            }
            T -> parámetro de entrada
                Es una intefaz funcional que acepta un parametro de entrada y no devuelve resultado.
         */

        List<String> nombres = new ArrayList<>();
        nombres.add("Alberto");
        nombres.add("PEdro");
        nombres.add("Paco");
        nombres.add("María");

        hof.filtrar(nombres, e->System.out.println(e), 6);

    }

    public void filtrar(List<String> lista, Consumer<String> consumer, int maxCaracteres){
        lista.stream().filter(logicaPredicado(maxCaracteres)).forEach(consumer);
    }

    public Predicate<String> logicaPredicado(int maxCaracteres){
        return e ->e.length()<maxCaracteres;
    }

    public int suma(int a, int b) {

        return a + b;
    }

    public int sumarHighFunction(SumarInterface sumar, int a, int b) {
        return sumar.apply(a, b);
    }



    @Override
    public int apply(int a, int b) {
        return a + b;
    }

    /**
     * Utilizamos este método para implenentar la interfaz funcional Function
     */
    public void imprimirMayusculas(Function<String, String> function, String nombre) {
        System.out.println(function.apply(nombre));
    }
}
