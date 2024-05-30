# Panel_JTable-amelich2.0
This is my second JTable panel implemented with Java, the project is made from IntellIJ. For more information consult the manual.

## *MP03 - UF5*

### Instruccions generals

Es tracta de fer un projecte integral que englobe els diferents resultats d’aprenentatge, criteris d’avaluació i continguts de la UF (d’ara en endavant seran els continguts), seguint les pautes indicades al present document. La idea general és intentar aplicar tot l’explicat pel professor i/o contingut als apunts dins del projecte (en cas de dubte pregunteu).

La realització del projecte seguirà unes fases ben definides, al final de les quals s’haurà d’entregar algun material i/o documentació. Els documents de text s’han de lliurar en format PDF, i els heu de posar a l’arrel del repositori del Github, dins la carpeta documentacio.

La durada del projecte serà d’aproximadament 40h, que es correspon en la durada real de la UF5.   

Del projecte s’haurà d’entregar:
el projecte en sí mateix (codi font), 
la documentació sol·licitada a cada fase.
altres elements que es demanen a alguna fase en concret.

Com a material de consulta disposareu al MOODLE de tots els apunts presentats pel professor, així com de tota la informació que pugueu trobar per internet.


Calendari (aproximat)

### Fases del projecte:

Fase 1:
Què se us proporcionarà: visió general dels continguts de la UF. Exemple de projecte GUI seguint el patró MVC a l’IntelliJ.
Què s’ha de fer: 
Acceptar la tasca del github on posarem el material del projecte.
Crear el projecte a l’IntelliJ dins el repositori, com de costum que utilitze MAVEN com a sistema de construcció. 
Creeu els paquets corresponents per tal de que el projecte seguisque el paradigma MVC. També podeu començar a crear alguna de les classes, de forma similar a l’exemple fet pel professor.
Creeu una excepció personalitzada semblant a la de l’exercici 6 de la pt1, incloent els missatges similars als que vau posar al projecte de la UF3. Podeu crear un paquet a posta per posar esta nova classe. Enlloc d’usar una enumeració heu de posar els missatges i valors a una col·lecció que implemente la interficie Map<K,v> (TreeMap, HashMap, …).
NOU: per poder centralizar el tractament d’excepcions hi ha problemes en els listeners. Una solució és usar un PropertyChangeListener. La teoria de com fer-ho trobareu al següent document (faré explicació a la classe del divendres 12/4).
Què s’ha de lliurar: 
Documentació: un document explicant què heu fet al projecte fins ara (paquets, classes, missatges d’excepcions, …).
el codi font al github.   

Fase 2:
Què se us proporcionarà: explicació de col·leccions i classes genèriques.
Què s’ha de fer: 
Creeu una nova classe al paquet model anomenada SuperCollection que sigue capaç d’actuar com diferents tipos de col·leccions segons el valor d’alguna de les seues propietats. Per exemple, pot ser una ArrayList, un TreeSet, …., simplement canviant els valors d’una propietat entera o millor d’una enumeració. A més ha de ser genèrica. Vos poso una plantilla de com podria ser (falta acabar):
	
	class SuperCollection <T> implements List<T>, Set<T>, ...{

	private int tipo;    //tipo de col·lecció que simularà, podeu fer 
                           //una enum Tipo enlloc d’un int

	//Col·leccions que podrà simular la vostra (podeu triar les classes)
       //no cla que siguen exactament les que uso jo
       private ArrayList<T> llista;

       private TreeSet<T> conjunt;

       private ….. //altres col·leccions

       //Mètodes constructors, getters, setters, …
 
       //Mètodes a implementar derivats de les interficies
}





		
La idea és, la que implementarà la relació 1:N que obligarà a tindre 2 vistes o com a mínim 2 taules relacionades.

Què s’ha de lliurar: 
Documentació: explicació de què fa la classe i on/com s’ha utilitzat.
el codi font al github.   

Fase 3:
Què se us proporcionarà: explicació i exemple d’ús d’expressions regulars.
Què s’ha de fer: 
Gestionar la introducció de valors correctes dins els camps del formulari (JTextFields, …) utilitzant expressions regulars.
	Què s’ha de lliurar: 
Documentació: explicació d’on s’han usat les expressions regulars, i el seu propòsit. És important que a l’execució l’usuari sàpigue en què s’ha equivocat al posar un valor per poder així rectificar adequadament. Dieu-ho també a la documentació.
el codi font al github.   

Fase 4:
Què se us proporcionarà: explicació i exemple de fitxers d’accés aleatori, entre altres tipos ja coneguts.
Què s’ha de fer: 
Gestionar la introducció d’algun valor que guardarem a este nou tipo de fitxer, a més de guardar els objectes a fitxers binaris (ObjectStreams) com ja vam fer a la UF3.
	Què s’ha de lliurar: 
Documentació: explicació d’on s’han usat els diferents fitxers, i el seu propòsit. Mostreu el codi que heu hagut de posar als diferents fitxers i mètodes del programa, acompanyat d’exemples d’execució
el codi font al github.  



Fase 5:
Què se us proporcionarà: explicació i exemple de streams en Java.
Què s’ha de fer: 
Usar els streams al projecte mostrant exemples del seu ús. Es valorarà l’ús d’Optional.
	Què s’ha de lliurar: 
Documentació: explicació d’on s’han usat els streams, i el seu propòsit. Mostreu el codi, acompanyat d’exemples d’execució
el codi font al github.  


Continguts
A. Resultats d’aprenentatge i criteris d’avaluació

1. Escriu programes que manipulin informació seleccionant i utilitzant els tipus avançats de dades facilitats pel llenguatge
1.1. Escriu programes que utilitzin taules (arrays)
1.2. Reconeix les llibreries de classes relacionades amb la representació i manipulació de col·leccions
1.3. Utilitza les classes bàsiques (vectors, llistes, piles, cues, taules de Hash) per emmagatzemar i processar informació.
1.4. Utilitza iteradors per recórrer els elements de les col·leccions.
1.5. Reconeix les característiques i avantatges de cada una de les col·leccions de dades disponibles.
1.6. Crea classes i mètodes genèrics.
1.7. Utilitza expressions regulars en la recerca de patrons en cadenes de text.
1.8. Identifica les classes relacionades amb el tractament de documents XML.
1.9. Dissenya programes que realitzen manipulacions sobre documents XML.
	
2. Gestiona els errors que poden aparèixer en els programes, utilitzant el control d’excepcions facilitat pel llenguatge.
2.1. Reconeix els mecanismes de control d’excepcions facilitats pel llenguatge.
2.2. Implementa la gestió d’excepcions en la utilització de classes facilitades pel llenguatge.
2.3. Implementa el llançament d’excepcions en les classes que desenvolupa.
2.4. Reconeix la incidència de l’herència en la gestió d’excepcions.

3. Desenvolupa interfícies gràfiques d’usuari simples, utilitzant les llibreries de classes adequades.
3.1. Utilitza les eines de l'entorn de desenvolupament per crear interfícies gràfiques d'usuari simples.
3.2. Programa controladors d'esdeveniments.
3.3. Escriu programes que utilitzin interfícies gràfiques per a l'entrada i sortida d'informació.

4. Realitza operacions bàsiques d'entrada/sortida de informació, sobre consola i fitxers, utilitzant les llibreries de classes adequades.
4.1. Utilitza la consola per realitzar operacions d'entrada i sortida d'informació.
4.2. Aplica formats en la visualització de la informació.
4.3. Reconeix les possibilitats d'entrada / sortida del llenguatge i les llibreries associades.
4.4. Utilitza fitxers per emmagatzemar i recuperar informació.
4.5. Crea programes que utilitzen diversos mètodes d'accés al contingut dels fitxers.

B. Continguts

1. Aplicació de les estructures d'emmagatzematge en la programació orientada a objectes
1.1. Estructures de dades avançades .
1.2. Creació d'arrays.
1.3. Arrays multidimensionals.
1.4. Cadenes de caràcters.
1.5. Col·leccions i iteradors.
1.6. Classes i mètodes genèrics
1.7. Manipulació de documents XML. Expressions regulars de cerca

2. Control d’excepcions
2.1. Captura d’excepcions.
2.2. Captura front delegació.
2.3. Llançament d’excepcions.
2.4. Excepcions i herència.

3. Interfícies gràfiques d’usuari
3.1. Creació i ús d’interfícies gràfiques d’usuari simples.
3.2. Concepte d'esdeveniment. Creació de controladors d'esdeveniments.
3.3. Paquets de classes per al disseny d’interfícies.

4. Lectura i escriptura d'informació
4.1. Tipus de fluxos. Fluxos de bytes i de caràcters.
4.2. Classes relatives a fluxos. Utilització de fluxos.
4.3. Entrada/Sortida. Llibreries associades.
4.4. Fitxers de dades. Registres.
4.5. Gestió de fitxers
4.5.1. Modes d'accés.
4.5.2. Lectura/escriptura
4.5.3. Utilització dels sistemes de fitxers.
4.5.4. Creació i eliminació de fitxers i directoris.

Criteris d’avaluació

Per calcular la nota se distingiran 2 blocs:
documentació entregada
aplicació o programari desenvolupat

El pes de cadascuna pot variar, però en principi serà de 35% i 65%, respectivament.

Les faltes d’assistència compten com en qualsevol UF, per tant superar el 20% de faltes, pot implicar la pèrdua d’avaluació continua i per tant el suspens en la UF. 

Per poder aprovar el projecte s’ha de treure una nota mínima de 5 a cadascuna de les parts, en cas contrari l’alumne haurà de recuperar la part o parts suspeses, i/o presentar-se a un examen a la convocatòria extraordinària.

