import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfazBiblioteca extends JFrame {

    private Biblioteca biblioteca;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCodigo;
    private JTextField txtPublicacion;
    private JTextField txtCopias;
    private JTextField txtFiltroAutor;

    private JComboBox<String> cbGenero;

    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;


    public InterfazBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        setTitle("Sistema de Gestion de Biblioteca");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(7, 2));

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Registrar libro")
        );

        panelFormulario.add(new JLabel("Titulo:"));

        txtTitulo = new JTextField();
        panelFormulario.add(txtTitulo);

        panelFormulario.add(new JLabel("Autor:"));

        txtAutor = new JTextField();
        panelFormulario.add(txtAutor);

        panelFormulario.add(new JLabel("Codigo:"));

        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Genero:"));

        cbGenero = new JComboBox<>(biblioteca.getGeneros());
        panelFormulario.add(cbGenero);

        panelFormulario.add(new JLabel("Año:"));

        txtPublicacion = new JTextField();
        panelFormulario.add(txtPublicacion);

        panelFormulario.add(new JLabel("Copias:"));

        txtCopias = new JTextField();
        panelFormulario.add(txtCopias);

        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.WEST);

        String[] columnas = {
                "Codigo",
                "Titulo",
                "Autor",
                "Genero",
                "Año",
                "Copias"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaLibros = new JTable(modeloTabla);

        add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        JPanel panelFiltro = new JPanel();

        panelFiltro.add(new JLabel("Autor:"));

        txtFiltroAutor = new JTextField(15);
        panelFiltro.add(txtFiltroAutor);

        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnTodos = new JButton("Ver todos");

        panelFiltro.add(btnFiltrar);
        panelFiltro.add(btnTodos);

        add(panelFiltro, BorderLayout.NORTH);

        JPanel panelEliminar = new JPanel();

        JButton btnEliminar = new JButton("Eliminar");

        panelEliminar.add(btnEliminar);

        add(panelEliminar, BorderLayout.SOUTH);

        btnGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                agregarLibro();
            }
        });

        btnFiltrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String autor = txtFiltroAutor.getText();

                if (!autor.isEmpty()) {

                    actualizarTabla(biblioteca.filtrarAutor(autor));

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Escriba un autor."
                    );
                }
            }
        });

        btnTodos.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                txtFiltroAutor.setText("");

                actualizarTabla(biblioteca.mostrarTodo());
            }
        });

        btnEliminar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                eliminarLibro();
            }
        });

        actualizarTabla(biblioteca.mostrarTodo());
    }

    public void agregarLibro() {

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String codigo = txtCodigo.getText();
        String genero = (String) cbGenero.getSelectedItem();

        String publicacionTexto = txtPublicacion.getText();
        String copiasTexto = txtCopias.getText();

        if (titulo.isEmpty() ||
                autor.isEmpty() ||
                codigo.isEmpty() ||
                publicacionTexto.isEmpty() ||
                copiasTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Todos los campos son obligatorios."
            );

            return;
        }

        try {

            int publicacion = Integer.parseInt(publicacionTexto);
            int copias = Integer.parseInt(copiasTexto);

            if (publicacion > 2026) {

                JOptionPane.showMessageDialog(
                        null,
                        "El año no puede ser mayor a 2026."
                );

                return;
            }

            if (copias < 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Las copias no pueden ser negativas."
                );

                return;
            }

            for (Libro libro : biblioteca.mostrarTodo()) {

                if (libro.getCodigo().equals(codigo)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ese codigo ya existe."
                    );

                    return;
                }
            }

            Libro libro = new Libro(
                    titulo,
                    autor,
                    codigo,
                    genero,
                    publicacion,
                    copias
            );

            biblioteca.agregarLibros(libro);

            actualizarTabla(biblioteca.mostrarTodo());

            limpiarCampos();

            JOptionPane.showMessageDialog(
                    null,
                    "Libro guardado."
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "El año y las copias deben ser numeros."
            );
        }
    }

    public void eliminarLibro() {

        int fila = tablaLibros.getSelectedRow();


        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un libro."
            );

            return;
        }

        String codigo = (String) modeloTabla.getValueAt(fila, 0);


        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Desea eliminar este libro?",
                "Eliminar",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            biblioteca.eliminarLibros(codigo);

            actualizarTabla(biblioteca.mostrarTodo());

            JOptionPane.showMessageDialog(
                    null,
                    "Libro eliminado."
            );
        }
    }

    public void actualizarTabla(ArrayList<Libro> lista) {

        modeloTabla.setRowCount(0);

        for (Libro libro : lista) {

            modeloTabla.addRow(new Object[]{
                    libro.getCodigo(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getGenero(),
                    libro.getPublicacion(),
                    libro.getCopiasDisponibles()
            });
        }
    }

    public void limpiarCampos() {

        txtTitulo.setText("");
        txtAutor.setText("");
        txtCodigo.setText("");
        txtPublicacion.setText("");
        txtCopias.setText("");

        cbGenero.setSelectedIndex(0);
    }
}