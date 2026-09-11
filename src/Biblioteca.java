import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {

    public ArrayList<Libro> libros;

    private String[] generos = {
            "Novela",
            "Ciencia",
            "Historia",
            "Infantil",
            "Técnico",
            "Fantasía",
            "Poesía"
    };

    public String[] getGeneros() {
        return generos;
    }

    private HashMap<String, ArrayList<Libro>> librosAutor;

    public Biblioteca() {

        libros = new ArrayList<>();
        librosAutor = new HashMap<>();
    }

    public void agregarLibros(Libro libro) {

        libros.add(libro);

        String autor = libro.getAutor();

        if (!librosAutor.containsKey(autor)) {
            librosAutor.put(autor, new ArrayList<>());
        }

        librosAutor.get(autor).add(libro);
    }

    public ArrayList<Libro> mostrarTodo() {

        return libros;
    }

    public ArrayList<Libro> filtrarAutor(String autor) {

        if (librosAutor.containsKey(autor)) {
            return librosAutor.get(autor);
        }

        return new ArrayList<>();
    }

    public void eliminarLibros(String codigo) {

        for (int i = 0; i < libros.size(); i++) {

            if (codigo.equals(libros.get(i).getCodigo())) {

                Libro libro = libros.get(i);

                libros.remove(i);

                String autor = libro.getAutor();

                librosAutor.get(autor).remove(libro);

                break;
            }
        }
    }
}