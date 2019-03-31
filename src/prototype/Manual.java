package prototype;

public class Manual {

    public void showInstructions() {
        System.out.println("Manual.showInstructions called");
        
        System.out.println("A prototype működésének bemutatására használt parancsok leírása itt található");
        System.out.println("A '>' jel után található szavak jelentik a parancsot, amit ellenőrizni szeretnénk, ");
        System.out.println("ezeket a szavakat kell szóközzel elválasztva a konzolra beírni, majd Enter megnyomásával a parancsot ellenőrizni");
        System.out.println("A '[szám]' helyére bármilyen egész számot be lehet írni");
        System.out.println("Minden parancs alatt található egy leírás, ami vázolja, hogy milyen műveleteket hajtott végre");
        System.out.println("A parancsok lefutása után a felhasználó visszajelzést kap annak sikerességéről");
        System.out.println("Kilépni az 'exit' beírásával lehet.");


        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("A panda függvényeit tesztelő parancsok:");

        System.out.println(">panda step hard");
        System.out.println("Egy csempén álló panda átlép egy másik csempére, ami semmilyen egyéb tulajdonsággal nem rendelkezik");

        System.out.println(">panda step soft [szám]");
        System.out.println("Egy csempén álló panda átlép egy törékeny csempére.");
        System.out.println("Ha a csempe eltörik a panda meghal.");
        System.out.println("A törékeny csempe élettartamát a 'soft' szó utáni számmal tudjuk beállítani");

        System.out.println(">panda step broken");
        System.out.println("Egy csempén álló panda átlép egy törött csempére és meghal.");

        System.out.println(">panda step exit");
        System.out.println("Egy csempén álló panda átlép a kijáratra");

        System.out.println(">panda follow");
        System.out.println("Egy orangután nekimegy egy mellette lévő pandának, azaz a két állat helyetcserél és a panda követni fogja az orangutánt");
        System.out.println("Az orangután átlép egy másik csempére, a panda követi az orangutánt, azaz átlép az orangután mögötti csempére");

        System.out.println(">panda jump");
        System.out.println("Ugrik egyet a panda, csökken az alatta lévő törékeny csempe élettartama");

        System.out.println(">panda scare");
        System.out.println("Megijed a panda, aminek hatására elengedi a követett és az őt követő állatok mancsát");

        System.out.println(">panda sleep");
        System.out.println("Elalszik a panda, ezáltal beül a fotelbe és a következő körben nem fog lépni");

        System.out.println(">panda letgo");
        System.out.println("A panda elengedi a mögötte és előtte álló állat mancsát.");
        System.out.println("Ennek hatására az összes mögötte álló panda is elengedi a mancsokat");


        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Az orangután függvényeit tesztelő parancsok:");

        System.out.println(">orangutan step hard");
        System.out.println("Egy csempén álló orangután átlép egy másik csempére, ami semmilyen egyéb tulajdonsággal nem rendelkezik");

        System.out.println(">orangutan step soft [szám]");
        System.out.println("Egy csempén álló orangután átlép egy törékeny csempére.");
        System.out.println("Ha a csempe eltörik a panda meghal.");
        System.out.println("A törékeny csempe élettartamát a 'soft' szó utáni számmal tudjuk beállítani");

        System.out.println(">orangutan step broken");
        System.out.println("Egy csempén álló orangután átlép egy törött csempére és meghal.");

        System.out.println(">orangutan step exit");
        System.out.println("Egy csempén álló orangután átlép a kijáratra és pontot kap az őt követő pandákért");

        System.out.println(">orangutan catch");
        System.out.println("Egy orangután, akit már követ egy panda elkap egy másik pandát, azok helyet cserélnek");


        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("A szekrény függvényeit tesztelő parancsok:");

        System.out.println(">wardrobe");
        System.out.println("Átmegy egy panda a szekrényen, azaz egy másik szekrénynél fog kijönni a szekrényből");


        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("A játékgép függvényeit tesztelő parancsok:");

        System.out.println(">arcade");
        System.out.println("A játékgép csilingel egyet, amitől a mellette lévő panda ugrik egyet, ezzel koptatva az alatta lévő törékeny csempét.");


        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("A csokiautomata függvényeit tesztelő parancsok:");

        System.out.println(">chocolateAutomat");
        System.out.println("A csokiautomata sípol egyet, amitől a mellette lévő panda megijed\nés elengedi a panda kezét, akinek a követője.");
    }

}
