CORRECAO MANUAL
Você trocou o nome do arquivo, mandou LevelTransversal
Na linhas 33 e 34 do PC2D.java você tirou o ceil das multiplicações, isso faz com que arquivos na ultima coluna ou linha fiquem inacessíveis (ArrayIndexOutOfBoundsException). Isso aconteceu em vários testes. Se você mudar o limite dos seus vetores para G+3 vários outros testes passam. 
Os restantes falham por exceder o tempo limite (são casos um pouco grandes que demoraram para rodar), acredito eu porque você não precisa de uma tabela de símbolos para guardar os pontos da forma que guardou, bastava uma lista ligada e sua solução iria ficar considerávelmente mais eficiente.

[-10] : Sem Relatório

.........................................................................T..........................
CORREÇÃO AUTOMÁTICA
Quantidade de Testes | Veredito
99 | OK
1 | Tempo de Execução Excedido
0 | Resposta Errada
0 | Erro de Execução
