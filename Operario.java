package entidades;

public class Operario {
    private String documento;
    private String nombre;
    private double sueldo;
    private int antiguedad;
    private double sueldoFinal;

    // Constructor, Getters y Setters
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }
    public int getAntiguedad() { return antiguedad; }
    public void setAntiguedad(int antiguedad) { this.antiguedad = antiguedad; }
    public double getSueldoFinal() { return sueldoFinal; }
    public void setSueldoFinal(double sueldoFinal) { this.sueldoFinal = sueldoFinal; }
}
