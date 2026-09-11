import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        Libro libro1 = new Libro("Cien años de soledad",
                "Gabriel García Márquez", "1", "Novela", 1967, 3);
        Libro libro2 = new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", "2", "Novela", 1985, 2);
        Libro libro3 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "3", "Novela", 1605, 4);
        Libro libro4 = new Libro("El principito", "Antoine de Saint-Exupéry", "4", "Infantil", 1943, 5);
        Libro libro5 = new Libro("Harry Potter y la piedra filosofal", "J. K. Rowling", "5", "Fantasía", 1997, 3);
        Libro libro6 = new Libro("Sapiens", "Yuval Noah Harari", "6", "Ciencia", 2011, 2);
        Libro libro7 = new Libro("Breve historia del tiempo", "Stephen Hawking", "7", "Ciencia", 1988, 2);
        Libro libro8 = new Libro("La vorágine", "José Eustasio Rivera", "8", "Novela", 1924, 3);
        Libro libro9 = new Libro("El olvido que seremos", "Héctor Abad Faciolince", "9", "Novela", 2006, 2);
        Libro libro10 = new Libro("Veinte poemas de amor y una canción desesperada", "Pablo Neruda", "10", "Poesía", 1924, 4);

        biblioteca.agregarLibros(libro1);
        biblioteca.agregarLibros(libro2);
        biblioteca.agregarLibros(libro3);
        biblioteca.agregarLibros(libro4);
        biblioteca.agregarLibros(libro5);
        biblioteca.agregarLibros(libro6);
        biblioteca.agregarLibros(libro7);
        biblioteca.agregarLibros(libro8);
        biblioteca.agregarLibros(libro9);
        biblioteca.agregarLibros(libro10);

        InterfazBiblioteca ventana = new InterfazBiblioteca(biblioteca);

        ventana.setVisible(true);
    }
}