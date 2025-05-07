package prog2.model;

import prog2.vista.CentralUBException;

public class Reactor implements InComponent{
        private boolean activat;
        private float temperaturaReactor; //comprovar q sigui realment així

        public Reactor(){
           setTemperaturaReactor(25);
        }

        public void setTemperaturaReactor(float temperaturaReactor) {
            this.temperaturaReactor = temperaturaReactor;
        }

        public float getTemperaturaReactor() {
            return temperaturaReactor;
        }

        public void activa() throws CentralUBException {
            this.activat = true;
            //no se si es necessita una exceció
        }

        public void desactiva() throws CentralUBException{
            this.activat = false;
            //no se si es necessita una exceció
        }

        public boolean getActivat() {
            return this.activat;
        }

        public void revisa (PaginaIncidencies p){
            if (!getActivat()){
                p.afegeixIncidencia("Reactor fora de servei");
            }else{
                p.afegeixIncidencia("Reactor actiu");
            }

        }

        public float getCostOperatiu(){
            if (!getActivat())
                return 0;
            else
                return 35;
        }


        public float calculaOutput(float input){
            if (!getActivat())
                return temperaturaReactor;

            else
               setTemperaturaReactor(temperaturaReactor + ((100 - input) * 10));
               return this.temperaturaReactor;

        }

    }
