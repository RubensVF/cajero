import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Cajero{

    class Movimiento{
        private Date fecha;
        private double saldoAnterior;
        private double retiro;

        public Movimiento(){}

        public Movimiento(Date fecha, double saldoAnterior, double retiro){
            this.fecha = fecha;
            this.saldoAnterior = saldoAnterior;
            this.retiro = retiro;
        }
        
        public Date getFecha(){
            return fecha;
        }   
        public double getSaldoAnterior(){
            return saldoAnterior;
        }
        public double getRetiro(){
            return retiro;
        }
        public void setFecha(Date fecha){
            this.fecha = fecha;
        }
        public void setSaldoAnterior(double saldoAnterior){
            this.saldoAnterior = saldoAnterior;
        }    
        public void setRetiro(double retiro){
            this.retiro = retiro;
        }

        @Override
        public String toString() {
            return "Movimiento [fecha=" + fecha + ", saldoAnterior=" + saldoAnterior + ", retiro=" + retiro + "]";
        }    
        
    }


    private double saldo = 1000.00; //Saldo inicial
    private int pin = 12345; //Pin de acceso
    private int intentos = 3; // Intentos para ingresar el pin
    private List<Movimiento> historial = new ArrayList<>(); //Lista para guardar el historial de movimientos
    private String nombre = "Ruben"; //Nombre del usuario
    public static void main(String[] args) {
        while(true){
            Cajero cajero = new Cajero();
            if(cajero.autenticar()){
                cajero.menu();
            }else{
                System.out.println("No se pudo autenticar");
                break;
            }
        }
    }
    public boolean autenticar(){
        while(intentos > 0){
            System.out.println("Ingrese su pin");
            int pinIngresado = Integer.parseInt(System.console().readLine());
            if(pinIngresado == pin){
                System.out.println("Bienvenido " + nombre);
                return true;
            }else{
                intentos--;
                System.out.println("Pin incorrecto, intentos restantes: " + intentos);
            }
        }
        return false;
    }

    public void menu(){
        while(true){
            System.out.println("1.-Consultar saldo");
            System.out.println("2.-Retirar saldo");
            System.out.println("3.-Historial de movimientos");
            System.out.println("4.-Salir");
            int opcion = Integer.parseInt(System.console().readLine());
            switch(opcion){
                case 1:
                    System.out.println("Su saldo es: " + saldo);
                    break;
                case 2:
                    System.out.println("Ingrese la cantidad a retirar"); 
                    String cantidad = System.console().readLine();
                    if( !cantidad.matches("[0-9]+(\\.[0-9]{2})?") ){
                        System.out.println("Cantidad invalida");
                        break;
                    }
                    double retiro = Double.parseDouble(cantidad);
                    if(retiro > saldo){
                        System.out.println("No se tienen fondos suficientes");
                        break;
                    }
                    else{
                        saldo -= retiro;
                        Movimiento movimiento = new Movimiento(new Date(), saldo + retiro, retiro);
                        historial.add(movimiento);
                        System.out.println("Retiro exitoso");
                    }
                case 3:
                    for(Movimiento movimiento : historial){
                        System.out.println(movimiento);
                    }
                    break;
                case 4:
                    System.out.println("Gracias por usar el cajero");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
            while(opcion == 1 || opcion == 2 || opcion == 3 ){
                System.out.println("Desea regresar al menu? (s/n)");
                String respuesta = System.console().readLine();
                if(respuesta.equals("s")){
                    break;
                }else if(respuesta.equals("n")){
                    System.out.println("Gracias por usar el cajero");
                    System.exit(0);
                    return;
                }else{
                    System.out.println("Opcion no valida");
                }
            }
        }
    }
}

// Se añade comentario para hacer merge