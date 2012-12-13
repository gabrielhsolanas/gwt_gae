gwt_gae
===============

Aplicativo web, desenvolvido utilizando o framework Google Web Toolkit (GWT) para o front-end e serviço web. Esse projeto foi desenvolvido com suporte a plataforma cloud (nuvem) do Google, o App Engine. A camada de persistência é implementada utilizando o JDO (Java Data Objects) com datanucleus integrado ao App Engine.

O objetivo dessa aplicação é servir como conteúdo no estudo de desenvolvimento de soluções ricas para web (RIA) para implantação na nuvem.

O projeto foi implantado no Google App Engine (GAE), pode ser acessado pela url: http://yawdemo-ggc.appspot.com/

Detalhes da implementação
-------
Tecnologias utilizadas na implementação:
* GWT: utilizamos o GWT para desenvolvimento da camada visual (front-end) e integração com os serviços Web (server side);
* App Engine: configurações definidas para a implantação da aplicação no GAE;
* JDO: define um conjunto de interfaces para persistência de objetos Java;
* Datanucleus: provider JDO adaptado para o mecanismo de persistência do App Engine, o DataStore;

Pré-requisitos
-------
* JDK - versão 1.6 do Java, a versão suportada pelo App Engine;
* Eclipse IDE - o projeto possui as configurações do Eclipse, por contar com mais opções na integração com ferramentas do Google;
* Plugin do Google para Eclipse - plugin com suporte a implantação no GAE e para o desenvolvimento com GWT.
* Versão do SDK GWT: 2.5.0.
* Versão do SKD do App Engine: 1.7.3
* Versão do Datanuleus JDO: v1

Saiba mais
-------
Visite a página do projeto:
http://www.yaw.com.br/open/projetos/gwt-gae/
