/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionGimnasio;

public class Membresia implements Resumible{
    private String codigoMembresia;
    private String nombrePlan;
    private int vigenciaDias;
    private double precioPlan;
    private String descripcionPlan;
    private String estado;
    
    public Membresia(){
        this.codigoMembresia = "";
        this.nombrePlan = "Sin nombre";
        this.vigenciaDias = 0;
        this.precioPlan = 0.0;
        this.descripcionPlan = "Sin descripcion";
        this.estado = "Inactivo";
    }    
    
    // se usa para la base de datos
    public Membresia(String codigoMembresia, String nombrePlan){
        this.codigoMembresia = codigoMembresia;
        this.nombrePlan = nombrePlan;
    }    
    
    public Membresia(String codigoMembresia, String nombrePlan, int vigenciaDias, double precioPlan){
        this.codigoMembresia = codigoMembresia;
        this.nombrePlan = nombrePlan;
        setVigenciaDias(vigenciaDias);
        setPrecioPlan(precioPlan);
        this.descripcionPlan = "Sin descripcion";
        this.estado = "Activo";
 
    }      
    
    public Membresia(String codigoMembresia, String nombrePlan, int vigenciaDias, double precioPlan, String descripcionPlan, String estado){
        this.codigoMembresia = codigoMembresia;
        this.nombrePlan = nombrePlan;
        setVigenciaDias(vigenciaDias);
        setPrecioPlan(precioPlan);
        this.descripcionPlan = descripcionPlan;
        this.estado = estado;
        
    }    
    
    public String getCodigoMembresia(){
        return codigoMembresia;
    }    
    
    public void setCodigoMembresia(String codigoMembresia){
        if(codigoMembresia == null || codigoMembresia.trim().equals("")){
            System.out.println("Codigo de membresia incorrecto"); 
        } else {
            this.codigoMembresia = codigoMembresia;
        }  
    }    
    
    public String getNombrePlan(){
        return nombrePlan;
    }    
    
    public void setNombrePlan(String nombrePlan){
        if (nombrePlan == null || nombrePlan.trim().equals("")){
            System.out.println("Plan incorrecto");
            
        } else {    
            this.nombrePlan = nombrePlan;
            
        }
    }    
    
    public int getVigenciaDias(){
        return vigenciaDias;
    }    
    
    public void setVigenciaDias(int vigenciaDias){
        if (vigenciaDias <= 0){
            System.out.println("Ingresar un valor mayor a 0");
            
        } else {    
            this.vigenciaDias = vigenciaDias;
        }    
    }    
    
    public double getPrecioPlan(){
        return precioPlan;
    }    
    
    public void setPrecioPlan(double precioPlan){
        if(precioPlan < 0){
            System.out.println("El valor no puede ser negativo");
        } else {
            this.precioPlan = precioPlan;
        }    
    }    
    
    public String getDescripcionPlan(){
        return descripcionPlan;
    }    
    
    public void setDescripcionPlan(String descripcionPlan){
        if(descripcionPlan == null || descripcionPlan.trim().equals("")){
            System.out.println("Descripcion incorrecta");
            
        } else {    
            this.descripcionPlan = descripcionPlan;
        }    
    }    
    public String getEstado(){
        return estado;
    }    
    
    public void setEstado(String estado){
        this.estado = estado;
    }    
    
    @Override
    public void mostrarResumen(){
        System.out.println("MEMBRESIA");
        System.out.println("Codigo: " + codigoMembresia);
        System.out.println("Plan: " + nombrePlan);
        System.out.println("Vigencia: " + vigenciaDias + "dias");
        System.out.printf("Precio: %.2f%n", precioPlan);
        System.out.println("Descripcion: " + descripcionPlan);
        System.out.println("Estado: " + estado);
        System.out.println("----------------------------------------");
    }    
    
    @Override
    public String toString(){
        return codigoMembresia + " - " + nombrePlan + " - " + estado;
    }    
}
    
    
           
