package com.germanbarrera.catalogopeliculas.datos;

import com.germanbarrera.catalogopeliculas.domain.Pelicula;
import com.germanbarrera.catalogopeliculas.excepciones.AccesoDatosEX;
import com.germanbarrera.catalogopeliculas.excepciones.EscrituraDatosEx;
import com.germanbarrera.catalogopeliculas.excepciones.LecturaDatosEX;

import java.io.FileNotFoundException;
import java.util.List;

public interface AccesoDatos {

    boolean existe(String nombreRecurso) throws AccesoDatosEX;

    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEX;

    void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx;

    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEX, FileNotFoundException;

    void crear(String nombreRecurso) throws AccesoDatosEX;

    void borrar(String nombreRecurso) throws AccesoDatosEX;


}
