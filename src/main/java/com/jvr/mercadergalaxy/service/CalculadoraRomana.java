package com.jvr.mercadergalaxy.service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CalculadoraRomana {
    private static final Map<Character, Integer> valoresRomanos = new HashMap<>();
    private static final Pattern patronValido = Pattern.compile(
            "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    static {
        valoresRomanos.put('I', 1);
        valoresRomanos.put('V', 5);
        valoresRomanos.put('X', 10);
        valoresRomanos.put('L', 50);
        valoresRomanos.put('C', 100);
        valoresRomanos.put('D', 500);
        valoresRomanos.put('M', 1000);
    }

    public static int romanoAEntero(String romano) {
        if (!esRomanoValido(romano)) {
            throw new IllegalArgumentException("Número romano inválido: " + romano);
        }

        int suma = 0;
        int valorPrevio = 0;

        for (int i = romano.length() - 1; i >= 0; i--) {
            int valor = valoresRomanos.get(romano.charAt(i));
            if (valor < valorPrevio) {
                suma -= valor;
            } else {
                suma += valor;
            }
            valorPrevio = valor;
        }
        return suma;
    }

    public static String enteroARomano(int numero) {
        if (numero <= 0 || numero > 3999) {
            throw new IllegalArgumentException("Número fuera del rango para números romanos: " + numero);
        }

        int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] literalesRomanos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romano = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            while (numero >= valores[i]) {
                romano.append(literalesRomanos[i]);
                numero -= valores[i];
            }
        }
        return romano.toString();
    }

    private static boolean esRomanoValido(String romano) {
        return patronValido.matcher(romano).matches();
    }

    public static String sumar(String romano1, String romano2) {
        int resultado = romanoAEntero(romano1) + romanoAEntero(romano2);
        return enteroARomano(resultado);
    }

    public static String restar(String romano1, String romano2) {
        int resultado = romanoAEntero(romano1) - romanoAEntero(romano2);
        return enteroARomano(resultado);
    }

    public static String multiplicar(String romano1, String romano2) {
        int resultado = romanoAEntero(romano1) * romanoAEntero(romano2);
        return enteroARomano(resultado);
    }

    public static String dividir(String romano1, String romano2) {
        if (romanoAEntero(romano2) == 0) {
            throw new ArithmeticException("División por cero.");
        }
        int resultado = romanoAEntero(romano1) / romanoAEntero(romano2);
        return enteroARomano(resultado);
    }
}
