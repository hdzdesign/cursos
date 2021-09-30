package com.chc;

public class Main implements SumarInterface {
/** Funciones de orden superior
    -  Pueden recibir una función como parámetro de entrada
    - Pueden devolver una función como un return

 Con que se cumpla cualuera de estas 2 afirmaciones es una función de orden superior
*/

    public static void main(String[] args){


        Main hof = new Main();
        System.out.println(hof.suma(2, 3));
        System.out.println(hof.apply(3,8));
    }

    public int suma(int a , int b){

        return  a+b;
    }

    @Override
    public int apply(int a, int b) {
        return a+b;
    }
}
