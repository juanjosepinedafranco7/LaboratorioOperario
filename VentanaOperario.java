package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import entidades.Operario;
import logica.ModeloDatos;
import logica.Procesos;

public class VentanaOperario extends JFrame implements ActionListener {

    private JLabel etiTitulo, lblDoc, lblNombre, lblSueldo, lblAntiguedad, lblResultado;
    private JTextField txtDoc, txtNombre, txtSueldo, txtAntiguedad;
    private JButton btnCalcular, btnConsultar, btnListar;
    
    // Instanciamos nuestras capas de lógica y datos
    ModeloDatos miModelo = new ModeloDatos();
    Procesos miProceso = new Procesos();

    public VentanaOperario() {
        setTitle("Cálculo de Aumentos - Talento Humano");
        setSize(450, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        etiTitulo = new JLabel("GESTIÓN DE OPERARIOS");
        etiTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiTitulo.setBounds(100, 20, 300, 30);
        add(etiTitulo);

        // Campos de entrada
        lblDoc = new JLabel("Documento:"); lblDoc.setBounds(50, 80, 100, 25); add(lblDoc);
        txtDoc = new JTextField(); txtDoc.setBounds(160, 80, 150, 25); add(txtDoc);

        lblNombre = new JLabel("Nombre:"); lblNombre.setBounds(50, 120, 100, 25); add(lblNombre);
        txtNombre = new JTextField(); txtNombre.setBounds(160, 120, 150, 25); add(txtNombre);

        lblSueldo = new JLabel("Sueldo Base:"); lblSueldo.setBounds(50, 160, 100, 25); add(lblSueldo);
        txtSueldo = new JTextField(); txtSueldo.setBounds(160, 160, 150, 25); add(txtSueldo);

        lblAntiguedad = new JLabel("Antigüedad (años):"); lblAntiguedad.setBounds(50, 200, 120, 25); add(lblAntiguedad);
        txtAntiguedad = new JTextField(); txtAntiguedad.setBounds(160, 200, 150, 25); add(txtAntiguedad);

        // Botones
        btnCalcular = new JButton("Calcular y Guardar");
        btnCalcular.setBounds(50, 260, 160, 30);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(220, 260, 120, 30);
        btnConsultar.addActionListener(this);
        add(btnConsultar);

        btnListar = new JButton("Ver Todos");
        btnListar.setBounds(50, 300, 290, 30);
        btnListar.addActionListener(this);
        add(btnListar);

        // Etiqueta de resultado
        lblResultado = new JLabel("Sueldo Final: $");
        lblResultado.setFont(new Font("Arial", Font.ITALIC, 14));
        lblResultado.setBounds(50, 350, 350, 30);
        add(lblResultado);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            registrar();
        }
        if (e.getSource() == btnConsultar) {
            consultar();
        }
        if (e.getSource() == btnListar) {
            System.out.println(miModelo.consultarListaCompleta()); // Salida por consola según PDF
        }
    }

    private void registrar() {
        try {
            double sueldo = Double.parseDouble(txtSueldo.getText());
            int ant = Integer.parseInt(txtAntiguedad.getText());

            // Llamamos a la lógica para el aumento
            double sFinal = miProceso.calcularAumento(sueldo, ant);
            
            Operario op = new Operario();
            op.setDocumento(txtDoc.getText());
            op.setNombre(txtNombre.getText());
            op.setSueldo(sueldo);
            op.setAntiguedad(ant);
            op.setSueldoFinal(sFinal);

            miModelo.registrarOperario(op);
            lblResultado.setText("Sueldo Final: $" + String.format("%.2f", sFinal));
            JOptionPane.showMessageDialog(this, "Operario registrado con éxito.");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Por favor verifique los datos numéricos.");
        }
    }

    private void consultar() {
        String doc = JOptionPane.showInputDialog("Ingrese el documento a buscar:");
        Operario encontrado = miModelo.consultarOperario(doc);
        
        if (encontrado != null) {
            txtDoc.setText(encontrado.getDocumento());
            txtNombre.setText(encontrado.getNombre());
            txtSueldo.setText(encontrado.getSueldo() + "");
            txtAntiguedad.setText(encontrado.getAntiguedad() + "");
            lblResultado.setText("Sueldo Final: $" + encontrado.getSueldoFinal());
        } else {
            JOptionPane.showMessageDialog(this, "El operario no existe.");
        }
    }
}
