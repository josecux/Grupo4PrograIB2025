

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */


public class Ave extends Animal {
    private String tipoVuelo;

    public Ave(int idAnimal, String nombre, double consumoDiario, String tipoVuelo) {
        // Llama al constructor de la superclase Animal
        super(idAnimal, nombre, "Ave", consumoDiario, "Granos"); // Valores por defecto para Ave
        this.tipoVuelo = tipoVuelo;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(String tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo de Vuelo: " + tipoVuelo;
    }
}