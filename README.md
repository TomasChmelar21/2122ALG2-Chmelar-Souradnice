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

### Příklad ze souboru
GC kód jméno souřadnice

GC28VHF Stihacka 49°41.041N, 014°00.291E

GC3EV4M Stinkyho fretci keska 50°21.550N, 014°28.833E

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
