import java.util.InputMismatchException;
import java.util.Scanner;

public class PizzeriaTot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatosBD db = new DatosBD();
        db.conectarBD();
        boolean enEjecucion = true;

        while (enEjecucion) {
            System.out.println("===== Pizzería TOT ======");
            System.out.println("1. Agregar una pizza");
            System.out.println("2. Eliminar una pizza");
            System.out.println("3. Mostrar lista de pizzas");
            System.out.println("4. Editar una pizza");
            System.out.println("5. Salir");
            System.out.print("- Ingrese una opción: ");

            try {
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre de la pizza: ");
                        String nombre = sc.nextLine();
                        System.out.print("Ingrese el precio de la pizza: ");
                        float precio = sc.nextFloat();
                        System.out.print("Ingrese el stock de la pizza: ");
                        int stock = sc.nextInt();
                        db.agregarProducto(nombre, precio, stock);
                        break;
                    case 2:
                        System.out.print("Ingrese la ID de la pizza que desea eliminar: ");
                        int idEliminar = sc.nextInt();
                        db.eliminarProducto(idEliminar);
                        break;
                    case 3:
                        db.mostrarProductos();
                        break;
                    case 4:
                        System.out.print("Ingrese el ID de la pizza que desea editar: ");
                        int idEditar = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Ingrese el nuevo nombre de la pizza: ");
                        String nombreEditar = sc.nextLine();
                        System.out.print("Ingrese el nuevo precio de la pizza: ");
                        float precioEditar = sc.nextFloat();
                        System.out.print("Ingrese el nuevo stock de la pizza: ");
                        int stockEditar = sc.nextInt();
                        db.editarProducto(idEditar, nombreEditar, precioEditar, stockEditar);
                        break;
                    case 5:
                        System.out.println("Has salido del programa.");
                        enEjecucion = false;
                        break;
                    default:
                        System.out.println("Opción no válida, intenta nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número.");
                sc.nextLine();
            }
        }

        db.cerrarConexion();
        sc.close();
    }
}