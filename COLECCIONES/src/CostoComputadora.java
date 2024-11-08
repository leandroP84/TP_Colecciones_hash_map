import java.util.HashSet;
import java.util.Scanner;

class ComponenteCPU {
    String componente;
    String marca;
    int cantidad;
    double precio;

    public ComponenteCPU(String componente, String marca, int cantidad, double precio) {
        this.componente = componente;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public double calcularSubtotal() {
        return cantidad * precio;
    }

    @Override
    public String toString() {
        return componente + "\t" + marca + "\t" + cantidad + "\t" + precio + "\t" + calcularSubtotal();
    }
}

class Computadora {
    String marca;
    String modelo;
    HashSet<ComponenteCPU> componentes = new HashSet<>();

    public Computadora(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public void agregarComponente(ComponenteCPU componente) {
        componentes.add(componente);
    }

    public double calcularCostoTotal() {
        return componentes.stream().mapToDouble(ComponenteCPU::calcularSubtotal).sum();
    }

    public double calcularPrecioVenta() {
        double costo = calcularCostoTotal();
        return costo + (costo < 50000 ? costo * 0.4 : costo * 0.3);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------Computadora----------------\n");
        sb.append("Marca: ").append(marca).append("\n");
        sb.append("Modelo: ").append(modelo).append("\n");
        sb.append("Componentes:\n");
        sb.append("Componente\tMarca\tCantidad\tPrecio x Unidad\tSubtotal\n");
        for (ComponenteCPU comp : componentes) {
            sb.append(comp).append("\n");
        }
        sb.append("Costo Total: ").append(calcularCostoTotal()).append("\n");
        sb.append("Precio de Venta Sugerido: ").append(calcularPrecioVenta()).append("\n");
        return sb.toString();
    }
}

public class CostoComputadora {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String respuesta;
        do {
            Computadora computadora = cargarComputadora();
            System.out.println(computadora);
            System.out.print("¿Desea cotizar otra computadora? (SI/NO): ");
            respuesta = scanner.nextLine();
        } while (respuesta.equalsIgnoreCase("SI"));
    }

    private static Computadora cargarComputadora() {
        System.out.print("Ingrese la marca de la computadora: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el modelo de la computadora: ");
        String modelo = scanner.nextLine();
        Computadora computadora = new Computadora(marca, modelo);

        String continuar;
        do {
            System.out.print("Ingrese el componente: ");
            String componente = scanner.nextLine();
            System.out.print("Ingrese la marca del componente: ");
            String marcaComponente = scanner.nextLine();
            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            System.out.print("Ingrese el precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            computadora.agregarComponente(new ComponenteCPU(componente, marcaComponente, cantidad, precio));

            System.out.print("¿Desea agregar otro componente? (SI/NO): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("SI"));

        return computadora;
    }
}

