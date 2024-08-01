package Herencia;

import java.util.Calendar;
import java.util.Scanner;

public class Empresa {

    static Scanner lea = new Scanner(System.in);
    private static final Empleado[] empleados=new Empleado[500];
    private static int contadorEmpleados=0;

    public static void main(String[]args){

        int op;

        do{
            System.out.println("1- Agregar Empleado");
            System.out.println("2- Pagar Empleado");
            System.out.println("3- Lista de Empleados");
            System.out.println("4- Sub Menu especifico");
            System.out.println("5- Salir");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch(op){
                case 1:
                    hire();
                    break;
                case 2:
                    pay();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    submenu();
                    break;
            }
        } while(op!=5);

    }

    private static Empleado search(int cod){
        for (Empleado emp:empleados){
            if (emp!=null&&emp.getCodigo()==cod){
                return emp;
            }
        }
        return null;
    }

    private static void hire(){
        System.out.print("Ingrese el tipo de empleado (COMUN,HORA,VENTA,TEMPORAL): ");
        String tipo=lea.next().toUpperCase();

        System.out.print("Ingrese el código del empleado: ");
        int codigo=lea.nextInt();

        if (search(codigo)!=null){
            System.out.print("Ese código ya está en uso!");
            return;
        }

        System.out.print("Ingrese el nombre del empleado: ");
        String nombre=lea.next();

        if(tipo.equals("COMUN")){
            System.out.print("Ingrese el salario: $");
            double salario=lea.nextDouble();
            empleados[contadorEmpleados++]=new EmpleadoComun(codigo,nombre,salario);
        }else if(tipo.equals("HORA")){
            empleados[contadorEmpleados++]=new EmpleadoPorHora(codigo,nombre);
        }else if(tipo.equals("VENTA")){
            System.out.print("Ingrese el salario base: $");
            double salario=lea.nextDouble();
            empleados[contadorEmpleados++]=new EmpleadoPorVenta(codigo, nombre, salario);
        }else if(tipo.equals("TEMPORAL")) {
            empleados[contadorEmpleados++]=new EmpleadoTemporal(codigo,nombre);
        }else{
            System.out.println("Ese tipo de empleado no está disponible");
        }
    }

    private static void pay(){
        System.out.print("Ingrese el código del empleado: ");
        int codigo=lea.nextInt();
        Empleado emp=search(codigo);

        if(emp!=null){
            System.out.println("Se le pagó al empleado "+codigo+" la canridad de: $"+emp.pagar());
        }else{
            System.out.println("Ese empleado no está registrado");
        }
    }

    private static void list(){
        for(Empleado emp:empleados){
            if(emp!=null){
                System.out.println(emp);
            }
        }
    }

    private static void submenu(){
        int op;
        do {
            System.out.println("1-Fecha Fin Contrato a Temporales");
            System.out.println("2-Ingresar Venta");
            System.out.println("3-Ingresar Horas de Trabajo");
            System.out.println("4-Regresar al Menu Principal");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch (op) {
                case 1:
                    setFin();
                    break;
                case 2:
                    ventas();
                    break;
                case 3:
                    horas();
            }

        }while(op!=4);
    }

    private static void setFin(){
        System.out.print("Ingrese el código del empleado: ");
        int codigo=lea.nextInt();
        Empleado emp=search(codigo);

        if (emp!=null&&emp instanceof EmpleadoTemporal){
            System.out.print("Ingrese el día de fin del contrato: ");
            int dia=lea.nextInt();
            System.out.print("Ingrese el mes de fin del contrato: ");
            int mes=lea.nextInt();
            System.out.print("Ingrese el año de fin del contrato: ");
            int año=lea.nextInt();
            Calendar finContrato=Calendar.getInstance();
            finContrato.set(dia,mes,año);
            ((EmpleadoTemporal)emp).setFinContrato(finContrato);
        }else{
            System.out.println("El empleado no es temporal o no ha sido registrado");
        }
    }

    private static void ventas(){
        System.out.print("Ingrese el código del empleado: ");
        int codigo=lea.nextInt();
        Empleado emp=search(codigo);

        if (emp!=null&&emp instanceof EmpleadoPorVenta){
            System.out.print("Ingrese el monto de la venta: ");
            double monto=lea.nextDouble();
            ((EmpleadoPorVenta) emp).agregaVenta(monto);
        } else {
            System.out.println("El empleado no es por venta o no ha sido registrado");
        }
    }

    private static void horas() {
        System.out.print("Ingrese el código del empleado: ");
        int codigo=lea.nextInt();
        Empleado emp=search(codigo);

        if(emp!=null&&emp instanceof EmpleadoPorHora){
            System.out.print("Ingrese las horas trabajadas: ");
            int horas=lea.nextInt();
            ((EmpleadoPorHora)emp).setHorasT(horas);
        }else{
            System.out.println("El empleado no es por hora o no ha sido registrado");
        }
    }
}

