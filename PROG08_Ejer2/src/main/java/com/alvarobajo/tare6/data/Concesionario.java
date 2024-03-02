package com.alvarobajo.tare6.data;

import com.alvarobajo.tare6.model.Vehiculo;

import java.util.TreeSet;

/**
 * La clase Concesionario representa la gestión de vehículos en un concesionario.
 * Permite insertar, buscar, actualizar y eliminar vehículos.
 *
 * Utiliza un TreeSet para mantener los vehículos ordenados automáticamente por matrícula.
 *
 * @author Álvaro Bajo
 * @company BriandaMendoza-Programacion
 * @version 1.0
 */
public class Concesionario {
    private TreeSet<Vehiculo> vehiculos;

    /**
     * Constructor de la clase Concesionario.
     * Inicializa el TreeSet de vehículos.
     */
    public Concesionario() {
        this.vehiculos = new TreeSet<>();
    }

    /**
     * Inserta un vehículo en el concesionario.
     *
     * Utiliza un TreeSet para mantener los vehículos ordenados automáticamente por matrícula.
     *
     * @param vehiculo El vehículo a insertar.
     * @return Un mensaje indicando el resultado de la operación.
     */
    public String insertarVehiculo(Vehiculo vehiculo) {
        if (!matriculaExiste(vehiculo.getMatricula())) {
            vehiculos.add(vehiculo);
            return vehiculo.toString() + "\n Vehículo agregado con éxito";
        } else {
            return "Error: La matrícula ya existe en el concesionario.";
        }
    }

    /**
     * Busca un vehículo por matrícula en el concesionario.
     *
     * @param matricula La matrícula del vehículo a buscar.
     * @return El vehículo encontrado, o null si no se encuentra.
     */
    public Vehiculo buscarVehiculo(String matricula) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        return null; // No se encontró el vehículo
    }

    /**
     * Actualiza los kilómetros de un vehículo en el concesionario.
     *
     * @param matricula  La matrícula del vehículo a actualizar.
     * @param nuevosKms  Los nuevos kilómetros del vehículo.
     * @return true si la actualización fue exitosa, false si no se encontró el vehículo.
     */
    public boolean actualizarKms(String matricula, int nuevosKms) {
        Vehiculo vehiculo = buscarVehiculo(matricula);
        if (vehiculo != null) {
            vehiculo.setKilometros(nuevosKms);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtiene la lista de vehículos del concesionario.
     *
     * @return La lista de vehículos ordenada por matrícula.
     */
    public TreeSet<Vehiculo> obtenerListaVehiculos() {
        return new TreeSet<>(vehiculos);
    }

    /**
     * Verifica si una matrícula ya existe en el concesionario.
     *
     * @param matricula La matrícula a verificar.
     * @return true si la matrícula existe, false si no.
     */
    public boolean matriculaExiste(String matricula) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un vehículo del concesionario por matrícula.
     *
     * @param matricula La matrícula del vehículo a eliminar.
     * @return true si el vehículo fue eliminado con éxito, false si no se encontró.
     */
    public boolean borrarVehiculo(String matricula) {
        Vehiculo vehiculo = buscarVehiculo(matricula);
        if (vehiculo != null) {
            vehiculos.remove(vehiculo);
            return true; // Vehículo eliminado con éxito
        } else {
            return false; // No se encontró el vehículo con la matrícula proporcionada
        }
    }
}
