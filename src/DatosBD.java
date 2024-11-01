import java.sql.*;

public class DatosBD {
    private String url = "jdbc:mysql://localhost:3306/pizzeria";
    private String user = "root";
    private String password = "root";
    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    public void conectarBD() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (rs != null) rs.close();
            if (psmt != null) psmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public void mostrarProductos() {
        try {
            String sql = "SELECT * FROM pizza";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int idpizza = rs.getInt("idpizza");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");
                System.out.println(idpizza + "\t" + nombre + "\t" + precio + "\t" + stock);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarProducto(String nombre, double precio, int stock) {
        try {
            String sql = "INSERT INTO pizza (nombre, precio, stock) VALUES (?, ?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setDouble(2, precio);
            psmt.setInt(3, stock);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        try {
            String sql = "DELETE FROM pizza WHERE idpizza = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void editarProducto(int id, String newNombre, float newPrecio, int newStock) {
        try {
            String sql = "UPDATE Pizza SET nombre = ?, precio = ?, stock = ? WHERE idpizza = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, newNombre);
            psmt.setFloat(2, newPrecio);
            psmt.setInt(3, newStock);
            psmt.setInt(4, id);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
