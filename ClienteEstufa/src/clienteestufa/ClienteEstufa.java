/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteestufa;

import java.io.InputStream;
import java.net.*;
import java.lang.String;
import static java.lang.System.exit;
import java.util.Scanner;
//import client.sd.Som

public class ClienteEstufa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
		String servidor = "localhost";
		int porta = 5557;
                String A, B, OP;
                
                A = "";
                B = "0";
                OP = "";
                
		// Classe que contém um endereço IP com 4 inteiros
		InetAddress IPAddress = InetAddress.getByName(servidor);
 
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
                System.out.println("Montando dado para enviar...");
                int opcao;
                opcao = 0;
                Scanner ler = new Scanner(System.in);                
                                                    
                System.out.println("M E N U");
                System.out.println("1. Alta Temperatura");
                System.out.println("2. Baixa Temperatura");
                System.out.println("5. ESCREVA FIM PARA SAIR\n");                    
                opcao = ler.nextInt();
                
                if (opcao == 1 ){                                            
                    System.out.println("1. Enviando dados de alta temperatura" );
                    A = "1";
                    OP = "1";
                } else if (opcao == 2 ){
                    System.out.println("2. Enviando dados de baixa temperatura");
                    A = "0";
                    OP = "2"; 
                }
                
                /*
                if (opcao != 5){
                    System.out.println(": ");                    
                    A  = ler.next();//ler.nextLine();
                }
                */
                
                String sentence;
                
                if (opcao == 5 ){                    
                    sentence = "0,0,5,";                    
                } else sentence = A+","+B+","+OP+",";
                
                System.out.println(sentence);
                
		sendData = sentence.getBytes();
		
		// Classe para armazenar o pacote de dados a ser enviado via UDP
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
 
		System.out.println("Enviando pacote UDP para " + servidor + ":" + porta);
		// Método de envio de mensagem. Envia a mensagem e segue a execução
		clientSocket.send(sendPacket);
 
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
 
		clientSocket.receive(receivePacket);
		System.out.println("Pacote UDP recebido...");
 
		String modifiedSentence = new String(receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());
                
                if (modifiedSentence.equals("1")){
                    System.out.println("Umidade relativa do ar muito baixa, fogger ligado para otimização do cultivo : " + modifiedSentence);
                    
                    new Thread(){// BEEP DE AVISO CASO FOGGER TENHA SIDO LIGADO
                    @Override
                    public void run(){
                        Som son = new Som();                
                        son.som("beep.wav");
                    }
                }.start();
                    
                } 
                else {
                    System.out.println("Umidade relativa do ar em niveis aceitáveis, não é necessário intervenção : " + modifiedSentence);
                }
                /*              
                new Thread(){//thread para executar musica
                    @Override
                    public void run(){
                        Som son = new Som();                
                        son.som(modifiedSentence);
                    }
                }.start();
                */
		// Finaliza o DatagramSocket
                
		clientSocket.close();
		System.out.println("Socket cliente fechado!");                
    }
    
}
