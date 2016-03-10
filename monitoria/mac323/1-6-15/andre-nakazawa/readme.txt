java RandomWeb N M H A p | java Transition | java Markov T > output.txt

    Os pageranks na situacao padrao (p = 10%) foram altos para os hubs e nao
costuma diferir muito entre as paginas normais e as authorities.
Para garantir que os hubs/authorities recebessem/enviassem links de/para (isso
ta horrivel, mas nao to fazendo FLCO474 entao me perdoe) 10% - ou p - dos N
vertices, foi utilizado um vetor que guardava os vertices ja selecionados na
primeira parte < box[n] e sorteava os vertices >= v[n]... Alem disso, foram
mantidos parcialmente os hubs e authorities na hora de criar M links no conjunto
original de vertices. Isso evita que os authorities fiquem sempre isolados, sem
que nenhum link aponte a eles e nem os hubs fiquem ilhados, tendo que utilizar a
regra adicional.

Esse texto foi escrito as press

