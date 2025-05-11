public class Animal {
    int idAnimal;
    String nombre, especie, tipoAlimento;
    double consumoDiario;

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getConsumoDiario() {
        return consumoDiario;
    }

    public void setConsumoDiario(double consumoDiario) {
        this.consumoDiario = consumoDiario;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    @Override
    public String toString() {
        return "ID: " + idAnimal + ", Nombre: " + nombre + " (" + especie + ", consumo diario: " + consumoDiario + " lb de " + tipoAlimento + ")";
    }
}
