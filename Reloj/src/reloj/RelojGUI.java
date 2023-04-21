/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reloj;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Estudiante
 */
public class RelojGUI implements ActionListener, ChangeListener  {
     private JFrame frame;
    private JPanel panel;
    private JLabel labelHora;
    private JButton btnIniciar;
    private JButton btnDetener;
    private JSlider sliderVelocidad;
    private Timer timer;
    private int velocidad = 1000; // Velocidad por defecto en milisegundos
    private boolean enEjecucion = false;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
   EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RelojGUI window = new RelojGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RelojGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Reloj");
        
        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        labelHora = new JLabel("");
        labelHora.setHorizontalAlignment(SwingConstants.CENTER);
        labelHora.setBounds(135, 50, 150, 50);
        panel.add(labelHora);
        
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(90, 120, 100, 30);
        btnIniciar.addActionListener(this);
        panel.add(btnIniciar);
        
        btnDetener = new JButton("Detener");
        btnDetener.setBounds(240, 120, 100, 30);
        btnDetener.addActionListener(this);
        panel.add(btnDetener);
        
        sliderVelocidad = new JSlider(0, 10, 5);
        sliderVelocidad.setBounds(90, 90, 250, 20);
        sliderVelocidad.addChangeListener(this);
        panel.add(sliderVelocidad);
        
        // Inicialización del timer
        timer = new Timer(velocidad, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelHora.setText(new java.util.Date().toString());
            }
        });
    }
    
    // Implementación de ActionListener para los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            iniciarTimer();
        } else if (e.getSource() == btnDetener) {
            detenerTimer();
        }
    }

    // Implementación de ChangeListener para el slider
    @Override
    public void stateChanged(ChangeEvent e) {
        velocidad = sliderVelocidad.getValue() * 100;
        if (enEjecucion) {
            detenerTimer();
            iniciarTimer();
        }
    }

    // Inicia el timer del reloj
    private void iniciarTimer() {
        timer.setDelay(velocidad);
        timer.start();
        enEjecucion = true;
    }

    // Detiene el timer del reloj
    private void detenerTimer() {
        timer.stop();
        enEjecucion = false;
    }
}