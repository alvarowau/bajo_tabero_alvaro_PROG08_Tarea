package com.alvarobajo.banco.controller;

import com.alvarobajo.banco.models.CuentaBancaria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un banco y gestiona cuentas bancarias.
 *
 * @author Álvaro Bajo Tabero
 */
public class Banco {

    private List<CuentaBancaria> cuentas;

    /**
     * Constructor para inicializar una instancia de Banco.
     * Inicializa la lista de cuentas.
     */
    public Banco() {
        cuentas = new ArrayList<>();
    }



    /**
     * Abre una nueva cuenta bancaria y la agrega al banco.
     *
     * @param nuevaCuenta Nueva cuenta bancaria a abrir.
     * @return true si la cuenta se abrió con éxito; false, si no se pudo abrir la cuenta.
     * @throws RuntimeException si se alcanza el límite de cuentas (si decides establecer un límite).
     */
    public boolean abrirCuenta(CuentaBancaria nuevaCuenta) {
        if (!estaIBANEnUso(nuevaCuenta.getIban())) {
            cuentas.add(nuevaCuenta);
            return true;
        } else {
            throw new RuntimeException("No se pueden abrir más cuentas. Límite alcanzado.");
        }
    }

    /**
     * Elimina una cuenta bancaria del banco si existe y su saldo es 0.
     *
     * @param iban Número de IBAN de la cuenta a eliminar.
     * @return true si la cuenta se eliminó con éxito; false, si no se pudo eliminar la cuenta.
     */
    public boolean eliminarCuenta(String iban) {
        Iterator<CuentaBancaria> iterator = cuentas.iterator();
        while (iterator.hasNext()) {
            CuentaBancaria cuenta = iterator.next();
            if (cuenta.getIban().equalsIgnoreCase(iban) && cuenta.getSaldo() == 0) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    /**
     * Lista todas las cuentas registradas en el banco.
     */
    public void listarCuentas() {
        if (!cuentas.isEmpty()) {
            System.out.println("Listado de cuentas:");
            for (CuentaBancaria cuenta : cuentas) {
                System.out.println(cuenta);
            }
        } else {
            System.out.println("No hay cuentas registradas en el banco.");
        }
    }



    /**
     * Busca una cuenta bancaria por el nombre del titular.
     *
     * @param nombreTitular Nombre del titular de la cuenta a buscar.
     * @return Cuenta bancaria encontrada o null si no se encuentra.
     */
    public CuentaBancaria buscarCuentaPorTitular(String nombreTitular) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getTitular().getNombre().equalsIgnoreCase(nombreTitular)) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Busca una cuenta bancaria por el número de IBAN.
     *
     * @param iban Número de IBAN de la cuenta a buscar.
     * @return Cuenta bancaria encontrada o null si no se encuentra.
     */
    public CuentaBancaria buscarCuentaPorIBAN(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Verifica si un número de IBAN está en uso en el banco.
     *
     * @param iban Número de IBAN a verificar.
     * @return true si el IBAN está en uso; false, si no está en uso.
     */
    public boolean estaIBANEnUso(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra las cuentas del banco.
     */
    public void imprimirCuentas() {
        listarCuentas();
    }

    /**
     * Imprime la información de la cuenta bancaria asociada al titular.
     *
     * @param nombreTitular Nombre del titular de la cuenta a imprimir.
     */
    public void imprimirCuentaPorTitular(String nombreTitular) {
        CuentaBancaria cuenta = buscarCuentaPorTitular(nombreTitular);
        if (cuenta != null) {
            System.out.println("Cuenta encontrada para el titular " + nombreTitular + ":");
            System.out.println(cuenta);
        } else {
            System.out.println("No se encontró ninguna cuenta para el titular " + nombreTitular + ".");
        }
    }

    /**
     * Imprime la información de la cuenta bancaria asociada al número de IBAN.
     *
     * @param iban Número de IBAN de la cuenta a imprimir.
     */
    public void imprimirCuentaPorIBAN(String iban) {
        CuentaBancaria cuenta = buscarCuentaPorIBAN(iban);
        if (cuenta != null) {
            System.out.println("Cuenta encontrada para el IBAN " + iban + ":");
            System.out.println(cuenta);
        } else {
            System.out.println("No se encontró ninguna cuenta para el IBAN " + iban + ".");
        }
    }

    /**
     * Obtiene el número total de cuentas registradas en el banco.
     *
     * @return Número total de cuentas registradas.
     */
    public int getNumCuentas() {
        return cuentas.size();
    }

    /**
     * Obtiene la lista de cuentas bancarias registradas en el banco.
     *
     * @return Lista de cuentas bancarias.
     */
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}
