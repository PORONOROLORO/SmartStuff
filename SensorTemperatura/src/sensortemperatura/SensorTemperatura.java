/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensortemperatura;
import java.net.*;
import java.lang.String;
import java.util.Objects;
import java.util.Scanner;

public class SensorTemperatura {

    public static String funcao(String a, String b) throws Exception {
        // Classe que possibilida a comunicação UDP
		DatagramSocket clientSocket = new DatagramSocket();
		String servidor = "localhost";
		int porta = 5555;
                String A, B, OP;
                OP = "0";
                
		// Classe que contém um endereço IP com 4 inteiros
		InetAddress IPAddress = InetAddress.getByName(servidor);
 
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
                System.out.println("Montando dado para enviar...");
                    
                String sentence;
                
                sentence = a+","+b+","+OP+",";
                
		sendData = sentence.getBytes();
		
		// Classe para armazenar o pacote de dados a ser enviado via UDP
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
 
		System.out.println("Enviando pacote UDP para " + servidor + ":" + porta);
		// Método de envio de mensagem. Envia a mensagem e segue a execução
		clientSocket.send(sendPacket);
 
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
 
		clientSocket.receive(receivePacket);
		System.out.println("Pacote UDP recebido...");
 
		String modifiedSentence = new String(receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());
                
		System.out.println("Texto recebido do servidor externo: " + modifiedSentence);
                                
                if(modifiedSentence.equals("1"))
                    System.out.println("SENSOR HUMIDADE ATIVADO = "+modifiedSentence);
                                                
                System.out.println("Variavel modifiedSentence : "+modifiedSentence);               
                
		// Finaliza o DatagramSocket
		clientSocket.close();
		System.out.println("Socket servente fechado!");
                
                return(modifiedSentence);
                
    }
    
    public static void main(String[] args)throws Exception {
    int porta = 5557;
        
        // Classe que possibilida a comunicação UDP
        DatagramSocket serverSocket = new DatagramSocket(porta);
        // Configura o timeout para recebimento de mensagens
        serverSocket.setSoTimeout(100000);   

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

                    String a,b, resultado;
                    int op;
                    String sresultado;                               
                    //resultado = 0.00;                                 

                    a  = (CSV[0]);
                    b  = (CSV[1]);   
                    op = Integer.parseInt(CSV[2]);  
                    //op = 2;

                    resultado = "NÃO CARA";
                    if (a.equals("0")) {
                        ///if ((Objects.equals(a, "admin")) && (Objects.equals(b, "admin"))){                                   
                            resultado = "0";
                            System.out.println("Temperatura ideal");

                    }else {  
                        //se nao for 0 pesquisa no outro server                                    
                        System.out.println("Alta temperatura, verificar umidade relativa do ar");

                        String aux = funcao(a, b);//resposta do sensor de umidade 
                        
                        if(aux.equals("1")){ 
                            resultado = aux;
                            System.out.println("Sensor de umidade = "+aux);
                        }
                    }

                    // Classe que contém um endereço IP com 4 inteiros
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    //String capitalizedSentence = sentence.toUpperCase();
                    if (op == 5){
                        sresultado = "SAINDO...";                                    
                    } else sresultado = resultado;

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
