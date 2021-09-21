package com.chc;

public interface InterfaceDefault {

    default String miNombreDefault(String nombre){
        return nombre + " default con";
    }
}
