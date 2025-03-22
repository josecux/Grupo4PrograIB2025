public class Animal {
    String nombre, especie, tipoAlimento;
    double consumoDiario;

    public Animal(String nombre, String especie, double consumoDiario, String tipoAlimento) {
        this.nombre = nombre;
        this.especie = especie;
        this.consumoDiario = consumoDiario;
        this.tipoAlimento = tipoAlimento;
    }

    
    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public double getConsumoDiario() {
        return consumoDiario;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    @Override
    public String toString() {
        return nombre + " (" + especie + ", consumo diario: " + consumoDiario + " lb de " + tipoAlimento + ")";
    }
}
