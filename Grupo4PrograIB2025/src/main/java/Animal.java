public class Animal {
    protected int idAnimal;
    protected String nombre, especie, tipoAlimento;
    protected double consumoDiario;

    public Animal(int idAnimal, String nombre, String especie, double consumoDiario, String tipoAlimento) {
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.especie = especie;
        this.consumoDiario = consumoDiario;
        this.tipoAlimento = tipoAlimento;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
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
        return "ID: " + idAnimal + ", " + nombre + " (" + especie + ", consumo diario: " + consumoDiario + " lb de " + tipoAlimento + ")";
    }
}
