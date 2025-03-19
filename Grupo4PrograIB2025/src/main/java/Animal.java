class Animal {
    String nombre, especie, tipoAlimento;
    double consumoDiario;

    public Animal(String nombre, String especie, double consumoDiario, String tipoAlimento) {
        this.nombre = nombre;
        this.especie = especie;
        this.consumoDiario = consumoDiario;
        this.tipoAlimento = tipoAlimento;
    }

    @Override
    public String toString() {
        return nombre + " (" + especie + ", consumo diario: " + consumoDiario + " kg de " + tipoAlimento + ")";
    }
}

