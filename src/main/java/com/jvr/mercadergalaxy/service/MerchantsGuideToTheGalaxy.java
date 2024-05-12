package com.jvr.mercadergalaxy.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MerchantsGuideToTheGalaxy {
    public static void main(String[] args) {
        ConversorGalacticoARomano conversor = new ConversorGalacticoARomano();
        try {
            conversor.establecerValorCommodity("Silver", new String[]{"glob", "glob"}, 34);
            conversor.establecerValorCommodity("Gold", new String[]{"glob", "prok"}, 57800);
            conversor.establecerValorCommodity("Iron", new String[]{"pish", "pish"}, 3910);

            double valorSilver = conversor.obtenerValorCommodity("Silver", new String[]{"glob", "glob"});
            double valorGold = conversor.obtenerValorCommodity("Gold", new String[]{"glob", "prok"});
            double valorIron = conversor.obtenerValorCommodity("Iron", new String[]{"pish", "pish"});

            System.out.println("Valor de Silver: " + valorSilver + " Credits");
            System.out.println("Valor de Gold: " + valorGold + " Credits");
            System.out.println("Valor de Iron: " + valorIron + " Credits");
            String valorDecimal = conversor.procesarYValidarEntrada("tegj pish Silver pish Iron pish Gold");
            System.out.println("\n");
            System.out.println(valorDecimal);


            System.out.println("**********************************************************************************");
            System.out.println("TEST");

            System.out.println("pish tegj glob glob  is  "+conversor.galacticoADecimal("pish tegj glob glob") + " Credits");
            System.out.println(conversor.procesarYValidarEntrada("glob prok Silver"));
            System.out.println(conversor.procesarYValidarEntrada("glob prok Gold"));
            System.out.println(conversor.procesarYValidarEntrada("glob prok Iron"));

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
