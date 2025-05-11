/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public class Mamifero extends Animal {
    private String tipoPelaje;

    public Mamifero(int idAnimal, String nombre, double consumoDiario, String tipoPelaje) {
        // Llama al constructor de la superclase Animal
        super(idAnimal, nombre, "Mamífero", consumoDiario, "Carnívoro"); // Valores por defecto para Mamífero
        this.tipoPelaje = tipoPelaje;
    }

    public String getTipoPelaje() {
        return tipoPelaje;
    }

    public void setTipoPelaje(String tipoPelaje) {
        this.tipoPelaje = tipoPelaje;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo de Pelaje: " + tipoPelaje;
    }
}