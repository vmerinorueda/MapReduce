# MapReduce
En aquest repositori es pot trobar el codi corresponent a l'algorisme Map Reduce (Java) concepte del qual s'utilitza en el processament de big data. Per exemple, Hadoop utilitza el framework Map Reduce per al processament de dades en paral·lel i distribuït.

Aquest repositori conté part d'un projecte realitzat en Java per a l'assignatura d'Arquitectura i Tecnologies de Software (ATS), UAB.
En aquest se'ns planteja proporcionar un algorisme que implementi de forma pràctica l'algorisme Map Reduce utilitzat en aquest cas per a comptar les paraules i el nombre de vegades que es repeteixen en una col·lecció de dades, més concretament en fitxers.

L'objectiu d'aquesta pràctica consisteix bàsicament a fer un estudi del temps de còmput que triga en executar-se l'algorisme de lectura i recompte de paraules en seqüencial i el temps que triga un algorisme paral·lel utilitzat per al tractament de grans volum de dades. El qual ens porta a comprovar de primera mà que s'obté una millora de speedUp significativa a més volum de dades tenim (òbviament fins a un cert límit de recursos).

L'algorisme Map Reduce consta bàsicament de dues funcions principals Map i Reduce. Entre aquestes dues funcions principals executades paral·lelament trobem diferents steps que ha d'executar el codi, quedant de la següent manera:

Input -> Splitting -> Mapping -> Shuffling -> Reducing -> Final result

Per tal d'executar el programa, l'usuari ha d'indicar el número de threads que vol que executin l'algorisme Map Reduce, així com la ruta dels diferents fitxers que es volen analitzar.
