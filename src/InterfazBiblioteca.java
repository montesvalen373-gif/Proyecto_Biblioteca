import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfazBiblioteca extends JFrame {
    private Biblioteca biblioteca;

    private JTextField txtTitulo, txtAutor, txtCodigo, txtPublicacion, txtCopias, txtFiltroAutor;
    public JComboBox<String> cbGenero;
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;

    public InterfazBiblioteca() {
        biblioteca = new Biblioteca();

        setTitle("Sistemas de Gestion de Biblioteca - San Rafael");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Buevo libro"));

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
        cbGenero = new JComboBox<String>(biblioteca.getGeneros());
        panelFormulario.add(cbGenero);

        panelFormulario.add(new JLabel("Año de Publicacion:"));
        txtPublicacion = new JTextField();
        panelFormulario.add(txtPublicacion);

        panelFormulario.add(new JLabel("Copias disponibles:"));
        txtCopias = new JTextField();
        panelFormulario.add(txtCopias);

        JButton btnGuardar = new JButton("Guardar libro");
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.WEST);

        String[] columnas = {"Código", "Título", "Autor", "Género", "Año", "Copias"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaLibros = new JTable(modeloTabla);
        add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Diltrar por Autor:"));
        txtFiltroAutor = new JTextField(15);
        panelFiltro.add(txtFiltroAutor);

        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnRestaurar = new JButton("Ver Todos");
        panelFiltro.add(btnFiltrar);
        panelFiltro.add(btnRestaurar);

        add(panelFiltro, BorderLayout.NORTH);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEliminar = new JButton("Eliminar linea Seleccionada");
        panelAcciones.add(btnEliminar);

        add(panelAcciones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                agregarLibroInterfaz();
            }
        });

        btnFiltrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String autor = txtFiltroAutor.getText().trim();
                if (!autor.isEmpty()) {
                    actualizarTabla(biblioteca.filtrarAutor(autor));
                } else {
                    JOptionPane.showMessageDialog(null, "Escriba un autor para filtrar.");
                }
            }
        });

        btnRestaurar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtFiltroAutor.setText("");
                actualizarTabla(biblioteca.libros);
            }
        });

        btnEliminar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                eliminarLibroInterfaz();
            }
        });
    }

    private void agregarLibroInterfaz() {
        if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() ||
                txtCodigo.getText().isEmpty() || txtPublicacion.getText().isEmpty() ||
                txtCopias.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String codigo = txtCodigo.getText();
            String genero = (String) cbGenero.getSelectedItem();
            int publicacion = Integer.parseInt(txtPublicacion.getText());
            int copias = Integer.parseInt(txtCopias.getText());

            Libro nuevoLibro = new Libro(titulo, autor, codigo, genero, publicacion, copias);
            biblioteca.agregarLibros(nuevoLibro);

            actualizarTabla(biblioteca.libros);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Libro guarfado exitosamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año y Copias deben ser valores numéricos enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibroInterfaz() {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String codigo = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de eliminar el libro con código " + codigo + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                biblioteca.eliminarLibros(codigo);
                actualizarTabla(biblioteca.libros);
                JOptionPane.showMessageDialog(this, "Libro eliminado con éxito.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarTabla(ArrayList<Libro> lista) {
        modeloTabla.setRowCount(0);
        for (Libro l : lista) {
            Object[] fila = {
                    l.getCodigo(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getGenero(),
                    l.getPublicacion(),
                    l.getCopiasDisponibles()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtCodigo.setText("");
        txtPublicacion.setText("");
        txtCopias.setText("");
        cbGenero.setSelectedIndex(0);
    }
}