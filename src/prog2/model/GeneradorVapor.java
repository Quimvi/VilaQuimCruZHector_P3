package prog2.model;

import prog2.vista.CentralUBException;

public class GeneradorVapor implements InComponent{
        private boolean activat;

        public GeneradorVapor(){
            desactiva();
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
                //fer algo amb pàgina incidencies
            }

        }

        public float getCostOperatiu(){
            if (!getActivat())
                return 0;
            else
                return 25;
        }


        public float calculaOutput(float input){
            if (!getActivat())
                return 25;

            else
                return input * 0.9f;

        }

    }
