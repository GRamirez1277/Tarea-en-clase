package Herencia;

public class EmpleadoPorHora extends Empleado {
    private int horasT;
    
    public EmpleadoPorHora(int code, String name){
        super(code,name);
    }

    public int getHorasT() {
        return horasT;
    }

    public void setHorasT(int horasT) {
        this.horasT = horasT;
    }
    
    @Override
    public double pagar(){
        double monto = horasT * 150;
        horasT = 0;
        return monto;
    }
    

    @Override
    public double bono() {
        if(horasT > 400)
            return 1000;
        return 0;
    }
}
