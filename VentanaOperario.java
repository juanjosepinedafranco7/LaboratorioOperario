package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import entidades.Operario;
import logica.ModeloDatos;
import logica.Procesos;

public class VentanaOperario extends JFrame implements ActionListener {

    // Componentes de la interfaz
    private JLabel etiTitulo, lblDoc, lblNombre, lblSueldo, lblAntiguedad, lblResultado;
    private JTextField txtDoc, txtNombre, txtSueldo, txtAntiguedad;
    private JButton btnCalcular, btnConsultar, btnListar, btnLimpiar;
    
    // Instancias de lógica y datos (Globales para que no se pierda la info)
    ModeloDatos miModelo = new ModeloDatos();
    Procesos miProceso = new Procesos();

    public VentanaOperario() {
        setTitle("Cálculo de Aumentos - Talento Humano");
        setSize(450, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        etiTitulo = new JLabel("GESTIÓN DE OPERARIOS");
        etiTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiTitulo.setBounds(100, 20, 300, 30);
        add(etiTitulo);

        // Etiquetas y Campos de texto
        lblDoc = new JLabel("Documento:"); 
        lblDoc.setBounds(50, 80, 100, 25); add(lblDoc);
        txtDoc = new JTextField(); 
        txtDoc.setBounds(180, 80, 150, 25); add(txtDoc);

        lblNombre = new JLabel("Nombre:"); 
        lblNombre.setBounds(50, 120, 100, 25); add(lblNombre);
        txtNombre = new JTextField(); 
        txtNombre.setBounds(180, 120, 150, 25); add(txtNombre);

        lblSueldo = new JLabel("Sueldo Base ($):"); 
        lblSueldo.setBounds(50, 160, 120, 25); add(lblSueldo);
        txtSueldo = new JTextField(); 
        txtSueldo.setBounds(180, 160, 150, 25); add(txtSueldo);

        lblAntiguedad = new JLabel("Antigüedad (años):"); 
        lblAntiguedad.setBounds(50, 200, 130, 25); add(lblAntiguedad);
        txtAntiguedad = new JTextField(); 
        txtAntiguedad.setBounds(180, 200, 150, 25); add(txtAntiguedad);

        // Botones de acción
        btnCalcular = new JButton("Calcular y Guardar");
        btnCalcular.setBounds(50, 260, 160, 30);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(220, 260, 120, 30);
        btnConsultar.addActionListener(this);
        add(btnConsultar);

        btnListar = new JButton("Ver Lista en Consola");
        btnListar.setBounds(50, 300, 290, 30);
        btnListar.addActionListener(this);
        add(btnListar);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(50, 340, 290, 30);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        // Resultado visual
        lblResultado = new JLabel("Sueldo a pagar: $ 0.00");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 15));
        lblResultado.setForeground(new Color(0, 102, 0)); // Color verde oscuro
        lblResultado.setBounds(50, 400, 350, 30);
        add(lblResultado);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            hacerRegistro();
        } else if (e.getSource() == btnConsultar) {
            hacerConsulta();
        } else if (e.getSource() == btnListar) {
            // Imprime en la consola de Eclipse la lista completa
            System.out.println(miModelo.consultarListaCompleta());
        } else if (e.getSource() == btnLimpiar) {
            limpiarCampos();
        }
    }

    private void hacerRegistro() {
        try {
            // Capturamos y convertimos datos
            String doc = txtDoc.getText();
            String nom = txtNombre.getText();
            double sueldo = Double.parseDouble(txtSueldo.getText());
            int ant = Integer.parseInt(txtAntiguedad.getText());

            // Usamos la lógica de la clase Procesos
            double sFinal = miProceso.calcularAumento(sueldo, ant);
            
            // Creamos el objeto Operario
            Operario op = new Operario();
            op.setDocumento(doc);
            op.setNombre(nom);
            op.setSueldo(sueldo);
            op.setAntiguedad(ant);
            op.setSueldoFinal(sFinal);

            // Guardamos en el modelo de datos
            miModelo.registrarOperario(op);
            
            lblResultado.setText("Sueldo a pagar: $ " + String.format("%.2f", sFinal));
            JOptionPane.showMessageDialog(this, "Operario registrado correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese valores numéricos válidos en Sueldo y Antigüedad.");
        }
    }

    private void hacerConsulta() {
        String documentoBuscar = JOptionPane.showInputDialog(this, "Ingrese el documento del operario:");
        if (documentoBuscar != null) {
            Operario oper = miModelo.consultarOperario(documentoBuscar);
            if (oper != null) {
                // "Pintamos" los datos de vuelta en los cuadros de texto (Mapeo inverso)
                txtDoc.setText(oper.getDocumento());
                txtNombre.setText(oper.getNombre());
                txtSueldo.setText(oper.getSueldo() + "");
                txtAntiguedad.setText(oper.getAntiguedad() + "");
                lblResultado.setText("Sueldo a pagar: $ " + String.format("%.2f", oper.getSueldoFinal()));
            } else {
                JOptionPane.showMessageDialog(this, "Operario no encontrado.");
            }
        }
    }

    private void limpiarCampos() {
        txtDoc.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtAntiguedad.setText("");
        lblResultado.setText("Sueldo a pagar: $ 0.00");
    }
}
