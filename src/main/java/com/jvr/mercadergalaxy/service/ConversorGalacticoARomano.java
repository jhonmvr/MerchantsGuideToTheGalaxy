package com.jvr.mercadergalaxy.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversorGalacticoARomano {
    private Map<String, String> conversorGalacticoARomano;
    private Map<String, Double> valorCommodities;

    public ConversorGalacticoARomano() {
        this.conversorGalacticoARomano = new HashMap<>();
        this.valorCommodities = new HashMap<>();
        // Inicializar con valores predeterminados
        agregarAsignacion("glob", "I");
        agregarAsignacion("prok", "V");
        agregarAsignacion("pish", "X");
        agregarAsignacion("tegj", "L");
        establecerValorCommodity("Silver", new String[]{"glob", "glob"}, 34);
        establecerValorCommodity("Gold", new String[]{"glob", "prok"}, 57800);
        establecerValorCommodity("Iron", new String[]{"pish", "pish"}, 3910);
    }

    public void agregarAsignacion(String galactico, String romano) {
        if (romano.matches("^[IVXLCDM]+$")) {
            conversorGalacticoARomano.put(galactico, romano);
        } else {
            throw new IllegalArgumentException("El símbolo romano proporcionado no es válido.");
        }
    }

    public String convertirAGalactico(String[] terminosGalacticos) {
        StringBuilder resultadoRomano = new StringBuilder();
        for (String termino : terminosGalacticos) {
            if (conversorGalacticoARomano.containsKey(termino)) {
                resultadoRomano.append(conversorGalacticoARomano.get(termino));
            } else {
                throw new IllegalArgumentException("Término galáctico desconocido: " + termino);
            }
        }
        return resultadoRomano.toString();
    }

    public void establecerValorCommodity(String commodity, String[] terminosGalacticos, double creditos) {
        String romanos = convertirAGalactico(terminosGalacticos);
        int valorNumerico = CalculadoraRomana.romanoAEntero(romanos);
        double valorUnitario = creditos / valorNumerico;
        valorCommodities.put(commodity, valorUnitario);
    }

    public double obtenerValorCommodity(String commodity, String[] terminosGalacticos) {
        String romanos = convertirAGalactico(terminosGalacticos);
        int valorNumerico = CalculadoraRomana.romanoAEntero(romanos);

        double valorTotal = valorNumerico * valorCommodities.get(commodity);
        return valorTotal;
    }

    public String procesarYValidarEntrada(String entrada) {
        String[] partes = entrada.split("\\s+");
        StringBuilder resultado = new StringBuilder();
        List<String> terminosGalacticos = new ArrayList<>();
        double total = 0.0;

        for (int i = 0; i < partes.length; i++) {
            if (valorCommodities.containsKey(partes[i])) {
                // Cuando encuentra un commodity, procesa el grupo anterior de términos galácticos
                if (!terminosGalacticos.isEmpty()) {
                    double valor = obtenerValorCommodity(partes[i], terminosGalacticos.toArray(new String[0]));
                    resultado.append("Valor de " + partes[i] + ": " + valor + " Credits\n");
                    total += valor;
                    terminosGalacticos.clear(); // Limpiar para el próximo commodity
                }
            } else if (conversorGalacticoARomano.containsKey(partes[i])) {
                // Acumula términos galácticos para el siguiente commodity
                terminosGalacticos.add(partes[i]);
            }
        }

        resultado.append(entrada+" is: " + total + " Credits");
        return resultado.toString();
    }


    public int galacticoADecimal(String entrada) {
        String[] partes = entrada.trim().split("\\s+");
        String romanos = convertirAGalactico(partes);
        return CalculadoraRomana.romanoAEntero(romanos);
    }


}
