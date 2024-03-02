package com.alvarobajo.tare6.util;

import com.alvarobajo.tare6.data.Concesionario;
import com.alvarobajo.tare6.model.Vehiculo;

import javax.swing.*;
import java.util.List;
import java.util.Set;

/**
 * La clase MostrarDatos proporciona métodos para mostrar información sobre vehículos del concesionario.
 * Permite modificar los kilómetros de un vehículo, mostrar la lista de vehículos y buscar vehículos por matrícula.
 *
 * Utiliza un JOptionPane para interactuar con el usuario en lugar de System.out.println.
 *
 * @author Álvaro Bajo
 * @company BriandaMendoza-Programacion
 * @version 1.0
 */
public class MostrarDatos {

    private Concesionario concesionario;

    /**
     * Constructor de la clase MostrarDatos.
     *
     * @param concesionario El concesionario que contiene la información de los vehículos.
     */
    public MostrarDatos(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    /**
     * Método para modificar los kilómetros de un vehículo.
     *
     * @param matricula La matrícula del vehículo a modificar.
     * @param km        Los nuevos kilómetros a establecer.
     * @return true si la modificación es exitosa, false si hay errores o se cancela la operación.
     */
    public boolean modificarKMDeVehiculo(String matricula, int km) {
        Vehiculo vehiculo = concesionario.buscarVehiculo(matricula);

        // Verificar si se encontró el vehículo
        if (vehiculo != null) {
            String mensaje = "Datos del Vehículo con Matrícula " + matricula + ":\n" +
                    imprimirInfoVehiculo(vehiculo) +
                    "Kilómetros actuales: " + vehiculo.getKilometros() + "\n" +
                    "Kilómetros a modificar: " + km;

            // Verificar si los kilómetros a modificar son mayores a los actuales
            if (km > vehiculo.getKilometros()) {
                // Llamar a concesionario.actualizarKms(matricula, km) y verificar el resultado
                boolean actualizacionExitosa = concesionario.actualizarKms(matricula, km);

                if (actualizacionExitosa) {
                    mensaje += "\n\nLos kilómetros se han modificado correctamente.";
                    JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    mensaje += "\n\nError al intentar actualizar los kilómetros.";
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                mensaje += "\n\nError: La nueva cantidad de kilómetros debe ser mayor a la actual.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún vehículo con la matrícula " + matricula + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Preguntar al usuario si quiere volver a intentarlo o regresar al menú
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Desea volver a intentarlo?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Sí", "No"},
                "No"
        );

        if (opcion == JOptionPane.YES_OPTION) {
            // Volver a pedir los kilómetros hasta que sea un número entero positivo
            int nuevosKmNumerico;
            do {
                String nuevosKms = UtilDatos.entradaTeclado("Ingrese la nueva cantidad de kilómetros:");
                if (UtilComprobaciones.esEnteroPositivo(nuevosKms)) {
                    nuevosKmNumerico = Integer.parseInt(nuevosKms);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Error: La cantidad de kilómetros debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (true);

            return modificarKMDeVehiculo(matricula, nuevosKmNumerico);
        }

        return false; // Si el usuario decide no volver a intentarlo, retornar false
    }

    /**
     * Método para mostrar la lista de vehículos en el concesionario.
     */
    public void mostrarListaVehiculos() {
        // Obtener la lista de vehículos del TreeSet
        Set<Vehiculo> vehiculos = concesionario.obtenerListaVehiculos();

        // Verificar si el TreeSet está vacío
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos en el concesionario.");
        } else {
            // Imprimir encabezado del listado
            String encabezado = "Listado de Vehículos en el Concesionario:\n" +
                    String.format("%-20s%-15s%-15s%-15s%-30s%n", "Marca", "Matrícula", "Precio", "Kilómetros", "Descripción") +
                    "--------------------------------------------------------------------\n";

            // Imprimir el encabezado por consola
            System.out.println(encabezado);

            // Imprimir la información de cada vehículo por consola
            for (Vehiculo vehiculo : vehiculos) {
                System.out.print(imprimirInfoVehiculo(vehiculo));
            }

            // Imprimir el total de vehículos en el concesionario por consola
            System.out.println("Total de Vehículos en el Concesionario: " + vehiculos.size());
        }
    }

    // Método auxiliar para imprimir la información de un vehículo
    private String imprimirInfoVehiculo(Vehiculo vehiculo) {
        return String.format("%-20s%-15s%-15s%-15s%-30s%n",
                vehiculo.getMarca(), vehiculo.getMatricula(), vehiculo.getPrecio(),
                vehiculo.getKilometros(), vehiculo.getDescripcion());
    }

    /**
     * Método para buscar un vehículo por matrícula e imprimir sus datos.
     *
     * @param matricula La matrícula del vehículo a buscar.
     */
    public void buscarVehiculo(String matricula) {
        Vehiculo vehiculo = concesionario.buscarVehiculo(matricula);

        if (vehiculo != null) {
            String mensaje = "Datos del Vehículo con Matrícula " + matricula + ":\n" + vehiculo.toString();
            JOptionPane.showMessageDialog(null, mensaje, "Información del Vehículo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "No se encontró ningún vehículo con la matrícula " + matricula + ".\n¿Desea volver a intentarlo?",
                    "Error",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new Object[]{"Sí", "No"},
                    "No"
            );

            if (opcion == JOptionPane.YES_OPTION) {
                // Volver a pedir la matrícula hasta que se encuentre un vehículo
                do {
                    matricula = UtilDatos.entradaTeclado("Ingrese la matrícula del vehículo a buscar:");
                    vehiculo = concesionario.buscarVehiculo(matricula);

                    if (vehiculo != null) {
                        String mensaje = "Datos del Vehículo con Matrícula " + matricula + ":\n" + vehiculo.toString();
                        JOptionPane.showMessageDialog(null, mensaje, "Información del Vehículo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } else {
                        int nuevaOpcion = JOptionPane.showOptionDialog(
                                null,
                                "No se encontró ningún vehículo con la matrícula " + matricula + ".\n¿Desea volver a intentarlo?",
                                "Error",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.ERROR_MESSAGE,
                                null,
                                new Object[]{"Sí", "No"},
                                "No"
                        );

                        if (nuevaOpcion == JOptionPane.NO_OPTION) {
                            break;
                        }
                    }
                } while (true);
            }
        }
    }

    /**
     * Método para imprimir la información completa de un vehículo por matrícula.
     *
     * @param matricula La matrícula del vehículo a imprimir.
     */
    public void imprimirVehiculoCompleto(String matricula) {
        Vehiculo vehiculo = concesionario.buscarVehiculo(matricula);

        if (vehiculo != null) {
            String mensaje = "Vehículo Completo con Matrícula " + matricula + ":\n" + vehiculo.toString();
            JOptionPane.showMessageDialog(null, mensaje, "Información del Vehículo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún vehículo con la matrícula " + matricula + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Método para preguntar y borrar un vehículo por matrícula.
     *
     * @param matricula La matrícula del vehículo a borrar.
     */
    public void preguntarBorrarVehiculo(String matricula) {
        if (concesionario.matriculaExiste(matricula)) {
            String respuesta = UtilDatos.entradaTeclado("¿Está seguro de que desea borrar el vehículo con matrícula " + matricula + "? (Sí/No)");

            if (respuesta.equalsIgnoreCase("si")) {
                boolean borradoExitoso = concesionario.borrarVehiculo(matricula);

                if (borradoExitoso) {
                    System.out.println("El vehículo con matrícula " + matricula + " ha sido borrado correctamente.");
                } else {
                    System.out.println("Error al intentar borrar el vehículo con matrícula " + matricula + ".");
                }
            } else {
                System.out.println("Operación de borrado cancelada.");
            }
        } else {
            System.out.println("No se encontró ningún vehículo con la matrícula " + matricula + ".");
        }
    }
}
