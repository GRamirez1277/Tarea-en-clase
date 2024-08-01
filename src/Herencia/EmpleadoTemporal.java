package Herencia;

import java.util.Calendar;

public class EmpleadoTemporal extends EmpleadoComun {

    private Calendar finContrato;

    public EmpleadoTemporal(int code,String name){
        super(code,name,1500);
        this.finContrato=Calendar.getInstance();

    }

    public void setFinContrato(Calendar finContrato){
        this.finContrato=finContrato;
    }

    public double pagar(){
        Calendar hoy=Calendar.getInstance();
        if(hoy.before(finContrato)){
            return super.pagar();
        }
        return 0;
    }

    public String toString(){
        return super.toString()+"Fin de Contrato= "+finContrato.getTime();

    }

}
