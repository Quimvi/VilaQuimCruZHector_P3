package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant{
        private int id;
        private boolean activat;
        private boolean foraDeServei = false;
        private int variableUniforme ;

        public BombaRefrigerant(VariableUniforme variableUniforme,int id){
            this.variableUniforme = variableUniforme.seguentValor();
            setId(id);
        }

        public void setId(int id) throws CentralUBException{
            if (id > 3 || id < 0)
                throw new CentralUBException("El Id ha de ser de 0-3");
            else
                this.id = id;
        }

        public int getId(){
            return this.id;
        }

        public void activa() throws CentralUBException{
            if (this.foraDeServei)
                throw new CentralUBException("La bomba no pot ser activada, es troba fora de servei."); // cambiar text si cal
            else
                this.activat = true;
        }

        public void desactiva(){
            this.activat = false;
        }

        public boolean getActivat() {
            return this.activat;
        }

        public void revisa (PaginaIncidencies p){
            if (variableUniforme % 4 == id){
                this.foraDeServei = true;
                p.afegeixIncidencia("Bomba " + id + "fora de servei");
            }else{
                p.afegeixIncidencia("Bomba " + id + "en servei");
            }
        }

        public boolean getForaDeServei(){
            return this.foraDeServei;
        }

        public float getCapacitat() {
            if (!getActivat() || getForaDeServei())
                return 0;
            else
                return 250;
        }

        public float getCostOperatiu() {
            if (!getActivat() || getForaDeServei())
                return 0;
            else
                return 130;
        }

        public String toString(){
            return "Id=" + getId() + ", Activat=" + getActivat() + ", Fora de servei=" + getForaDeServei();
        }
}