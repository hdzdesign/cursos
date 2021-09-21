package com.chc;

public class Main implements InterfaceDefault{

    public static void main(String[] args) {

        MiNombre nombreAnonima = new MiNombre() {
            @Override
            public String miNombre() {
                return "Jose";
            }
        };
        System.out.println(nombreAnonima.miNombre());


        //Lambda
        MiNombre miNombreLambda = () -> "Jse Perez";
        System.out.println(miNombreLambda.miNombre());

        //Function sumar.
        Sumar suma = new Sumar() {
            @Override
            public int suma(int a, int b) {
                return a + b;
            }
        };
        System.out.println(suma.suma(2,3));

        //Funciones Lambda simple
        Sumar suma1 = (a,b) -> a+b;
        System.out.println(suma1.suma(3,3));

        //funcion lambda con bloque de código
        Sumar suma2 = (a, b) -> {
            a = a *b;
            a = a + b;
            System.out.println(" Resultado bloque de código return del mismo tipo" );
            return a;
        };
        System.out.println(suma2.suma(6,6));

        Main main = new Main();
        System.out.println(main.miNombreDefault("mi nombre por"));

        }
    }
