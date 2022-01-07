package com.germanbarrera.catalogopeliculas.datos;

import com.germanbarrera.catalogopeliculas.domain.Pelicula;
import com.germanbarrera.catalogopeliculas.excepciones.AccesoDatosEX;
import com.germanbarrera.catalogopeliculas.excepciones.EscrituraDatosEx;
import com.germanbarrera.catalogopeliculas.excepciones.LecturaDatosEX;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements AccesoDatos {

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEX {
        File archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEX {
        var archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                var pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEX("Excepcion al Listar peliculas");
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEX("Excepcion al Listar peliculas");
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito informacion al archivo " + pelicula);
        } catch (IOException e) {
            e.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir peliculas");
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEX {
        var archivo = new File(nombreRecurso);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            var indice = 1;
            while (linea != null) {
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    resultado = "Pelicula " + linea + "encontrada en el indice " + indice;
                    break;
                }
                linea = entrada.readLine();
                indice++;
            }
            entrada.close();
            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEX("Excepcion al buscar peliculas");
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEX("Excepcion al buscar peliculas");
        }
        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEX {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosEX("Excepcion al crear un archivo");
        }
    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEX {
        var archivo = new File(nombreRecurso);
        if (archivo.exists())
            archivo.delete();
        System.out.println("Se ha borrado el archivo");
    }
}
