# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

   
## Oppgave 1
- *Nevn noen viktige klasser og hvorfor vi trenger disse klassene?*

En av de viktigste klassene vi har er nok game hvor de fleste metodene for både TicTacToe og ConnectFour blir implimentert. Vi har også PlayerList som er en viktig klasse som implimenterer mange metoder hvordan vi skal håndtere hvilken spillers tur det er og diverse.

- *Hvor brukes abstraction, encapsulation, inheritance og polymorphism?*

Vi bruker abstraction når vi vil kunne sjule detaljene til det objectet. Det er fordi f.eks. metoder som implimenterer disse trenger ikke nødvengivis og vite hva disse objectene gjør

Encapsulation brukes når vi vil binde koden til den klassen den blir opprettet, det er grunnet at ingen klasser utenfor denne skal kunne modifisere dem.

Inheritance brukes når en annen klasse skal "arve" deler av en klasse av høyere nivå. Det er for å kunne slippe å skrive samme kode flere ganger

Polymorphism er når man implimenterer flerre metoder til en oppgave, men at f.eks. etter en viss tid vil metoden endre på seg.

- *Hvilke andre spill (eller utvidelser) vil det være enkelt å legge til i dette prosjektet, og hvilke spill vil by på utfordringer?*

De fleste spill ved et 2 dimonsjonalt spillbrett vil forsåvidt være enkelt og implimentere. Spill som vil by på udfordringer er uten tvil de som har 3 dimensjoner. Dette er fordi ved de 2 dimonsjonale spille kan vi bruke de allerede eksisterende grid klassene.

- *Hvor er SOLID prinsippene brukt/ikke brukt. Merk at SOLID prinsippene ikke er pensum før i INF112 så vi forventer ikke så mye av dere her, men de av dere som ønsker å få 15/15 må vise at dere har prøvd litt på dette.*

Single-responsibility principle: Dette blir brukt under game pakken hvor vi kan se at det er to forskjellige klasser til de 2 spillene vi har i programmet.

Open-closed principle: Dette blir brukt også i sammenheng med Game.java klassen. Der kan vi modifisere kode som andre klasser kan implimentere. Og de klassene som implimenterer denne klassen kan "arve" fra Game men kan også modifisere koden for sin egen bruk.

Liskov substitution principle: Dette blir brukt i Grid.java hvor man kan se at klassen ikke definerer hva den skal gi ut men i stede for å ha f.eks "int" så har den "< T >" som ser hvilke verdier den får inn og vurderer ut i fra subklassen som bruker den hvilken verdi den skal returnere

Interface segregation principle: I programmet blir det brukt forskjellige interfeces for grid, game, og player. Slik som prinsippet sier er det bedre å dele interfacet inn i flere små enn å kun å ha ett stort hvor alle metodene befinner seg.

Dependency inversion principle: Dette går ut på at de klassene som i en av de høyere levlene ikke skal være avhengig av klassene som er i de lavere levlene. 



## Oppgave 2
Planen for Othello er og lage en klasse hvor vi har alle funksjonene som er nødvendig, av å se på de andre spillene som allerede er implimentert så tenker jeg at jeg kunn trenger en klasse.
Metodene som vil bli implimentert er nok mest sansynlig de som allerede eksisterer i Game.java

## Oppgave 3
Når det gjelder testingen så fikk jeg lagd et par veldig enkle tester. Noen andre tester som kunne været smart og implementere er å sjekke om noMoreMoves() funker som den skal. Tror ikke det var så mye mer enn det, og alt ser ut til at det virker som det skal når man prøver å spille spillet både fra terminalen og GUI en.

