package com.chc;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        //Descomentar
        //probarOptional(null);
        //orElseOptional(null);
       // orElseThrow(null);
        isPresent("Julian");
    }

    public static void probarOptional(String nombre){
        System.out.println(nombre.length());
    }
//recuperar optional
    public static void crearOptional(){
        Optional<String> optional = Optional.empty();
        optional.get();
    }
//Comprobamos las diferentes opciones de optional para salvar los null con ofNullable.
    public static void orElseOptional(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        //Optional<String> optional1 = Optional.of(nombre);

        String nombreOfNullable = optional.orElse("vacio");
       // String nombreOf = optional1.orElse("vacio");
        System.out.println(nombreOfNullable);
     //   System.out.println(nombreOf);
    }

    public static void orElseThrow(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        optional.orElseThrow(NullPointerException::new);
        String nombre1 = optional.get();
        System.out.println(nombre1);
    }

    public static void isPresent(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        System.out.println(optional.isPresent());
    }
}
