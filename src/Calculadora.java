import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculadora implements ActionListener, KeyListener {
    private JFrame frame;
    private JTextField display;

    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnSuma, btnResta, btnMultiplicacion, btnDivision, btnIgual, btnLimpiar;

    private double operando1;
    private double operando2;
    private char operacion;

    public Calculadora(){
        // Creo la ventana
        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crea el campo de texto
        display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);
        display.setPreferredSize(new Dimension(200, 40));


        // Creo el panel de botones numericos
        JPanel panelNumeros = new JPanel(new GridLayout(3,4));
        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnLimpiar = new JButton("C");
        panelNumeros.add(btn1);
        panelNumeros.add(btn2);
        panelNumeros.add(btn3);
        panelNumeros.add(btn4);
        panelNumeros.add(btn5);
        panelNumeros.add(btn6);
        panelNumeros.add(btn7);
        panelNumeros.add(btn8);
        panelNumeros.add(btn9);
        panelNumeros.add(btn0);
        panelNumeros.add(btnLimpiar);
        panelNumeros.add(btn0);

        frame.add(panelNumeros,BorderLayout.CENTER);


        // Crear el panel de botones de operaciones
        JPanel panelOperaciones = new JPanel(new GridLayout(5, 1));
        btnSuma = new JButton("+");
        btnResta = new JButton("-");
        btnMultiplicacion = new JButton("*");
        btnDivision = new JButton("/");
        btnIgual = new JButton("=");
        panelOperaciones.add(btnSuma);
        panelOperaciones.add(btnResta);
        panelOperaciones.add(btnMultiplicacion);
        panelOperaciones.add(btnDivision);
        //panelOperaciones.add(btnIgual);
        panelNumeros.add(btnIgual);
        frame.add(panelOperaciones, BorderLayout.EAST);

        // Asignar el escuchador de eventos a los botones numéricos y de operaciones
        btn0.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btnSuma.addActionListener(this);
        btnResta.addActionListener(this);
        btnMultiplicacion.addActionListener(this);
        btnDivision.addActionListener(this);
        btnIgual.addActionListener(this);
        btnLimpiar.addActionListener(this);

        // Asignar el escuchador de eventos al campo de texto
        display.addKeyListener(this);


        // Configurar el evento del teclado
        frame.requestFocus();
        frame.setFocusable(true);
        //frame.addKeyListener(this);

        // Configurar propiedades de la ventana
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

        if (Character.isDigit(c)) {
            display.setText(display.getText() + c);
        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
            operando1 = Double.parseDouble(display.getText());
            operacion = c;
            display.setText("");
        } else if (c == '\n') {
            calcular();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void calcular() {
        operando2 = Double.parseDouble(display.getText());
        double resultado = 0;
        switch (operacion) {
            case '+':
                resultado = operando1 + operando2;
                break;
            case '-':
                resultado = operando1 - operando2;
                break;
            case '*':
                resultado = operando1 * operando2;
                break;
            case '/':
                resultado = operando1 / operando2;
                break;
        }
        display.setText(String.valueOf(resultado));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        String texto = boton.getText();

        if (texto.matches("[0-9]")) {
            display.setText(display.getText() + texto);
        } else if (texto.equals("C")) {
            display.setText("");
        } else if (texto.matches("[+\\-*/]")) {
            operando1 = Double.parseDouble(display.getText());
            operacion = texto.charAt(0);
            display.setText("");
        } else if (texto.equals("=")) {
            calcular();
        }
    }
}
