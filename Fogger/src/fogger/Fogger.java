/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fogger;
import java.net.*;
import java.lang.String;
import java.util.Objects;


public class Fogger {
    
    public static void main(String[] args) throws Exception {
        int porta = 5556;

        // Classe que possibilida a comunicação UDP
        DatagramSocket serverSocket = new DatagramSocket(porta);
        // Configura o timeout para recebimento de mensagens
        serverSocket.setSoTimeout(50000);   

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        try {
            while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    System.out.println("Esperando por datagrama UDP na porta " + porta);

                    // Método de recebimento de mensagem. Executa e espera...
                    serverSocket.receive(receivePacket);
                    System.out.println("Datagrama UDP recebido...");

                    String sentence = new String(receivePacket.getData());
                    String[] CSV = sentence.split(","); 

                    System.out.println(sentence);
                    System.out.println(CSV[0]);                               

                    Double  op;                                
                    String sresultado;                                
                    String a, b;

                    a  = (CSV[0]);
                    b  = (CSV[1]);
                    op = Double.parseDouble("1");   
                    
                    sresultado = "0";
                    if (a.equals("1")) {
                        sresultado = "1";
                        System.out.println("FOGGER ATIVADO, CONTROLANDO UMIDADE DO AR");                                    
                    } else{
                        System.out.println("Não é necessário ligar fogger");
                    }

                    // Classe que contém um endereço IP com 4 inteiros
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    //String capitalizedSentence = sentence.toUpperCase();

                    sendData = sresultado.getBytes();
                    //sendData = capitalizedSentence.getBytes();

                    // Classe para armazenar o pacote de dados a ser enviado via UDP
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                    System.out.println("Enviando " + sresultado + "...");
                    // Método de envio de mensagem. Envia a mensagem e segue a execução
                    serverSocket.send(sendPacket);
                    System.out.println("OK\n");
            }
        } catch (SocketTimeoutException e) {
                // timeout exception.
                System.out.println("Timeout!!! " + e);
                serverSocket.close();
        }
    }
    
}
