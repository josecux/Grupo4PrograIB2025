/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public class Reptil extends Animal {
    private String tipoEscamas;

    public Reptil(int idAnimal, String nombre, double consumoDiario, String tipoEscamas) {
        // Llama al constructor de la superclase Animal
        super(idAnimal, nombre, "Reptil", consumoDiario, "Insectos"); // Valores por defecto para Reptil
        this.tipoEscamas = tipoEscamas;
    }

    public String getTipoEscamas() {
        return tipoEscamas;
    }

    public void setTipoEscamas(String tipoEscamas) {
        this.tipoEscamas = tipoEscamas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo de Escamas: " + tipoEscamas;
    }
}