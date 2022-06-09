# Zadání práce 

Mám poměrně dlouhý soubor se souřadnicemi keší ze stránky geocaching.com, jenže je řazen podle abecedy, tudíž občas vyhledávání není ideální.
Z tohoto seznamu bych chtěl čerpat informace a pomocí souřadnic/jména je filtrovat. Pomocí GC kódu vytvořit odkaz na stránku keše. Do seznamu se bude dát taky přidávat nové.
### Postup:
  1)	Vytvořit modely Cache a Databáze
  2)	Vytvořit načtení, zpracování a výpis z textového souboru
  3)	Vytvořit funkce zápisu a čtení ze souboru
  4)	Vytvořit metody pracující se souřadnicemi
  5)	Vytvořit filtry a sorty databáze
  6)	Vytvořit Main program
  7)	Dodělat všechny požadované body
  8)	Testovat a ošetřovat kód

### Funkce
- Přidat novou keš do zvolené databáze<br>
- Vypsat všechny keše z textové databáze<br>
- Vypsat všechny kódy keší z binární databáze<br>
	  - Seřadit je podle Gckódu abecedně<br>
	  - Seřadit je podle množství FP<br>
	  - Filtrovat pouze nalezené<br>
	  - Filtrovat pouze nenalezené<br>
	  - Filtrovat pouze watchlist<br>
- Vybrat si jednu keš se kterou můžeme:<br>
		      - Otevřít na internetu<br>
		      - Otevřít na mapě<br>
		      - Nastavit nalezenou<br>
		      - Nastavit watchlist<br>
		      - Vzdálenost mezi 2ma body<br>

# Struktura vstupních a výstupních souborů

![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Structure1.png?raw=true) <br>
Kus zápisu z database.txt, 1. řádek je obecná informace co se na řádku nachází. Poté jsou zapsány informace v pořadí: <br>
Code, state, Latitutu, Longtitude, favorite points count, name oddělené mezerou u jména jsou mezery nahrazené _ aby se dalo číst jako 1 slovo.


![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/StructureBin.png?raw=true) <br>
Kus zápisu v BinaryDB.txt obsahuje na začátku počet kódů a poté jednotlivé kódy, které se postupně načítají

# Class diagram
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/ClassDiagram.png?raw=true)

# Testování

1)  Přidání Cache <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test11.png?raw=true) 
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test12.png?raw=true)
2)  Seřadit dle Gckodu <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test2.png?raw=true)
3) Filtrovat Watchlist <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test3.png?raw=true)
4) Vyhledat v obdelníku <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test4.png?raw=true)
5) Vypsat Binární soubor (z BinaryDB.txt) <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test5.png?raw=true)
6) Help menu <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test6.png?raw=true)
7) Špatný vstup hned na začátku <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test7.png?raw=true)
8) Soubor který neexistuje <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test8.png?raw=true)
9) Špatné souřadnice <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test9.png?raw=true)
10) Otevření odkazu na internetu <br>
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test101.png?raw=true)
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/Test102.png?raw=true)

# Externí knihovna
![alt text](https://github.com/TomasChmelar21/2122ALG2-Chmelar-Souradnice/blob/main/ReadMe_pictures/ExterniKnihovna.png?raw=true) <br>
Zvolil jsem externí knihovnu MiniGeo, která umožňuje vykreslovat segmenty na mapě. Lze v ní posouvat oddalovat, je tam měřítko vzdálenosti.

## Podmínky:
Menu, které umožní opakovaný výběr funkcí aplikácie a ukončení aplikace✅

Přehledný výpis výsledků na konzoli - použijte alespoň jednou String.format() a StringBuilder✅

Načítání vstupních dat z minimálně dvou souborů✅

Zápis výstupních dat do souboru✅

Možnosť práce s textovými a binárními soubory (alespoň někde)✅

Ideálně využití reálných otevřených dat✅

Adresář data se všemi datovými soubory a případně třídu Datastore se statickými metodami, které budou poskytovat další statická data.✅

Tri balíčky: a. ui – třídy, tvořící uživatelské rozhraní - komunikaci s uživatelem b. app – třídy, tvořící logiku s daty aplikácie - modely, kontrolery c. utils – pomocné třídy např. vlastní výjimky, vlastní rozhraní✅

Programování vůči rozhraní a použití vlastního rozhraní✅

Použití java.time API pro práci s časem✅

Použít enum typ✅

Použití kontejnerové třídy jazyka Java (ArrayList, LinkedList, HashMap ...) z Collections frameworku.✅

Alespoň dvě možnosti třídění s využitím rozhraní Comparable a Comparator✅

Použití regulárního výrazu✅

Ošetření vstupů, aby chybné vstupy nezpůsobily pád programu - pomocí existujících a vlastních výjimek✅

Vhodné ošetření povinně ošetřovaných výjimek✅

Použití Vámi vybrané externí knihovny (audio, posílání emailů, práce s obrázkem, junit testování, jiné formáty uložení dat ...)✅

Javadoc - každá třída a metoda musí mít javadoc popis, abyste mohli na závěr vygenerovat javadoc dokumentaci.✅
