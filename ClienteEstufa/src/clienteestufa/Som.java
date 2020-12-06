/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteestufa;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rodrigo Batista
 */
public class Som
{    
    
    public Som(){
        //Som.class.getResourceAsStream("D:\\Arquivos\\Documents\\NetBeansProjects\\Client-SD\\umaso.wav");    
    }
    
    public void som(String path) {
            
        try {
            System.out.println("CHEGOU A EXECUTAR O SOM");
            // Carrega o arquivo de áudio (não funciona com .mp3, só .wav) 
            String resource = path;
            System.out.println(resource);
            InputStream input = getClass().getResourceAsStream(resource);
            Clip oClip = AudioSystem.getClip();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(input);            
            System.out.println("AudioInput");
            oClip.open(audioInput);
 
            oClip.loop(0); // Toca uma vez            
            //oClip.loop(Clip.LOOP_CONTINUOUSLY); // Toca continuamente (para o caso de músicas)
            System.out.println("loop");
            // Para a execução (senão o programa termina antes de você ouvir o som)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
            }            
        });
        
        } catch (Exception e) {
            System.out.println(e);  
        }
    }
    
}
