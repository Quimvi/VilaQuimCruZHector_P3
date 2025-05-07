package prog2.model;

public abstract class PaginaBitacola {
    private int dia;

    public PaginaBitacola(int dia){
        setDia(dia);
    }

    public void setDia(int dia){
        this.dia = dia;
    }

    public int getDia(){
        return this.dia;
    }

    public abstract String toString();
}