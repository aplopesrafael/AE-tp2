# AE-tp2

TRABAJOS PRÁCTICOS (TP)
ALGORITMOS EVOLUTIVOS - 2016

TP 2
Un paradigma en la solución de problemas combinatorios es el problema del Cajero Viajante, más conocido como TSP (Traveling Salesman Problem), como puede verse en Wikipedia:
http://es.wikipedia.org/wiki/Problema_del_viajante

Este trabajo práctico consiste en resolver el TSP utilizando la heurística de Colonia de Hormigas o ACO (Ant Colony Optimization), estudiado en clase.

Los problemas tipos a ser resueltos pueden ser bajados del TSPLIB, disponible en:
http://www.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/tsp/
En particular, se verificará si el programa funciona utilizando las 2 instancias del TSP que se citan a continuación:
1. Burma14
2. Berlin 52

Ejemplo del archivo Berlin 52 (archivo: berlin52.tsp.gz)
NAME: berlin52
TYPE: TSP
COMMENT: 52 locations in Berlin (Groetschel)
DIMENSION: 52
EDGE_WEIGHT_TYPE: EUC_2D
NODE_COORD_SECTION
1 565.0 575.0
2 25.0 185.0
3 345.0 750.0
:
51 1340.0 725.0
52 1740.0 245.0
EOF

NOTA: eventualmente, se podrá verificar si el algoritmo funciona con la instancia: pcb1173

