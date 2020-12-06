# SmartStuff
Projeto para estufa inteligente com uso de sensores

Sensores envolvidos :
  * Sensor de umidade para iniciar um fogger (manutenção da umidade do local)
  * Sensor de temperatura (apita acima de x graus)
  * Sensor para detecção de movimento (antifurto)
  * Sensor para tomadas ou saídas elétricas, que avisam possibilidade de curto ou início de curto

1 - Sensor de humidade {
  Será um servente que irá receber os dados do ambiente (usuário) e irá verificar se cumpre determinada condição para acionar o fogger.
  Serviço estará rodando no localhost, passando pela porta 5555 para receber os dados.
  }

2 - Fogger {
  Será um servidor que ficará aguardando o sinal do sensor de umidade e responderá se o fogger foi ativado ou não.
  Serviço estará rodando no localhost, passando pela porta 5556
  }

3 - Sensor de temperatura {
  Será um servente que ira receber dados do ambiente (usuário) e enviar para o sensor de humidade para testar se o fogger deve ser ligado ou não .
  Serviço estará rodando no localhost, passando pela porta 5557
}

4 - Sensor para tomada(liga/desliga){
  Servidor que ira receber o sinal de liga/desliga da tomada que estará conectado o alarme ou outro dispositivo elétrico.
  Serviço estará rodando na porta 5558.  
}


5 - Interações {
  Será o cliente que ira enviar as interações, sejam elas simulações de alteração de ambiente ou interação de usuário
  utilizará a porta 5559 para enviar as mensagens.
}
