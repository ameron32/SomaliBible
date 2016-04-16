package com.ameron32.apps.somalibible.bible;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.Html;

import com.ameron32.apps.somalibible.frmk.IBible;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bible implements IBible
{

    AssetManager am;
    int[] chapterCount;
    String[][]chapters;
    String[]chapterNames;
    String[]chapterAbbrevs;


    public Bible(AssetManager am){
        chapterCount = new int[66];
        chapters = new String[66][];
        chapterNames = new String[66];
        chapterAbbrevs = new String[66];
        setupBooks();
        this.am = am;
    }








    public void load(Context c){

        populateChapterCounts(c);

        for (int i=0; i<66; i++){
            for (int j=0; j<chapterCount[i]; j++){
                chapters[i][j]=loadChapter(i, j);
            }
        }

    }

    private void populateChapterCounts(Context c){
        String pathname;
        for (int i=0; i<66; i++){
            pathname = getPathofChapterDir(i);
            chapterCount[i] = countSubDirs(pathname, c);
            chapters[i] = new String[chapterCount[i]];
        }
    }
    private String getPathofChapterDir(int i){

        String pathname = String.valueOf(i+1);
        if (pathname.length()==1){
            StringBuilder sb = new StringBuilder();
            pathname = sb.append('0').append(i+1).toString();
        }
        return pathname;
    }

    private String getPathofChapterFile(int i){

        return String.valueOf(i+1);
    }

    private int countSubDirs(String path, Context c) {

        String [] list;
        try {
            list = am.list(path);
            return list.length;
        } catch (IOException e) {
            return 0;
        }
    }

    private String loadChapter(int book, int chapter){

        StringBuilder path = new StringBuilder(getPathofChapterDir(book));
        path.append('/');
        path.append(getPathofChapterFile(chapter)+".htm");
        StringBuilder buf=new StringBuilder();
        String str;

        try {
            InputStream is = am.open(path.toString());
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(inputStreamReader);

            while ((str=br.readLine()) != null) {
                buf.append(str);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf.toString();

    }


    @Override
    public int getBookCount() {
        return books.size();
    }

    @Override
    public int getChapterCount(int book) {
        return chapterCount[book];
    }

    @Override
    public int getVerseCount(int book, int chapter) {
        try {
            throw new Exception("Method not yet implemented - Bible.getVerseCount()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setBookNames(String[] names) {
        // TODO not used
        for (int i=0; i<66; i++){
            switch (i){
                case 0: { chapterNames[i] = "Bilowgii"; break; }
                case 1: { chapterNames[i] = "Baxniintii"; break; }
                case 2: { chapterNames[i] = "Laawiyiintii"; break; }
                case 3: { chapterNames[i] = "Tirintii"; break; }
                case 4: { chapterNames[i] = "Sharciga Kunoqoshadiisa"; break; }
                case 5: { chapterNames[i] = "Yashuuca"; break; }
                case 6: { chapterNames[i] = "Xaakinnada"; break; }
                case 7: { chapterNames[i] = "Ruud"; break; }
                case 8: { chapterNames[i] = "Samuu'eel Kowaad"; break; }
                case 9: { chapterNames[i] = "Samuu'eel Labaad"; break; }
                case 10: { chapterNames[i] = "Boqorradii Kowaad"; break; }
                case 11: { chapterNames[i] = "Boqorradii Labaad"; break; }
                case 12: { chapterNames[i] = "Taariikhdii Kowaad"; break; }
                case 13: { chapterNames[i] = "Taariikhdii Labaad"; break; }
                case 14: { chapterNames[i] = "Cesraa"; break; }
                case 15: { chapterNames[i] = "Nexemyaah"; break; }
                case 16: { chapterNames[i] = "Esteer"; break; }
                case 17: { chapterNames[i] = "Ayuub"; break; }
                case 18: { chapterNames[i] = "Sabuurradii"; break; }
                case 19: { chapterNames[i] = "Maahmaahyadii"; break; }
                case 20: { chapterNames[i] = "Wacdiyahii"; break; }
                case 21: { chapterNames[i] = "Gabaygii Sulaymaan"; break; }
                case 22: { chapterNames[i] = "Ishacyaah"; break; }
                case 23: { chapterNames[i] = "Yeremyaah"; break; }
                case 24: { chapterNames[i] = "Baroorashadii Yeremyaah"; break; }
                case 25: { chapterNames[i] = "Yexesqeel"; break; }
                case 26: { chapterNames[i] = "Daanyeel"; break; }
                case 27: { chapterNames[i] = "Hoosheeca"; break; }
                case 28: { chapterNames[i] = "Yoo'eel"; break; }
                case 29: { chapterNames[i] = "Caamoos"; break; }
                case 30: { chapterNames[i] = "Cobadyaah"; break; }
                case 31: { chapterNames[i] = "Yoonis"; break; }
                case 32: { chapterNames[i] = "Miikaah"; break; }
                case 33: { chapterNames[i] = "Naxuum"; break; }
                case 34: { chapterNames[i] = "Xabaquuq"; break; }
                case 35: { chapterNames[i] = "Sefanyaah"; break; }
                case 36: { chapterNames[i] = "Xaggay"; break; }
                case 37: { chapterNames[i] = "Sekaryaah"; break; }
                case 38: { chapterNames[i] = "Malaakii"; break; }
                case 39: { chapterNames[i] = "Matayos"; break; }
                case 40: { chapterNames[i] = "Markos"; break; }
                case 41: { chapterNames[i] = "Luukos"; break; }
                case 42: { chapterNames[i] = "Yooxanaa"; break; }
                case 43: { chapterNames[i] = "Falimaha Rasuullada"; break; }
                case 44: { chapterNames[i] = "Rooma"; break; }
                case 45: { chapterNames[i] = "1 Korintos"; break; }
                case 46: { chapterNames[i] = "2 Korintos"; break; }
                case 47: { chapterNames[i] = "Galatiya"; break; }
                case 48: { chapterNames[i] = "Efesos"; break; }
                case 49: { chapterNames[i] = "Filiboy"; break; }
                case 50: { chapterNames[i] = "Kolosay"; break; }
                case 51: { chapterNames[i] = "1 Tesaloniika"; break; }
                case 52: { chapterNames[i] = "2 Tesaloniika"; break; }
                case 53: { chapterNames[i] = "1 Timoteyos"; break; }
                case 54: { chapterNames[i] = "2 Timoteyos"; break; }
                case 55: { chapterNames[i] = "Tiitos"; break; }
                case 56: { chapterNames[i] = "Filemon"; break; }
                case 57: { chapterNames[i] = "Cibraaniyada"; break; }
                case 58: { chapterNames[i] = "Yacquub"; break; }
                case 59: { chapterNames[i] = "1 Butros"; break; }
                case 60: { chapterNames[i] = "2 Butros"; break; }
                case 61: { chapterNames[i] = "1 Yooxanaa"; break; }
                case 62: { chapterNames[i] = "2 Yooxanaa"; break; }
                case 63: { chapterNames[i] = "3 Yooxanaa"; break; }
                case 64: { chapterNames[i] = "Yuudas"; break; }
                case 65: { chapterNames[i] = "Muujintii"; break; }
                default: break;
            }
        }
        chapterNames = names;
    }

    @Override
    public void setAbbrevs(String[] abbrevs) {
        // TODO not used
        for (int i=0; i<66; i++) {
            switch (i) {
                case 0: {
                    abbrevs[i] = "Bil";
                    break;
                }
                case 1: {
                    abbrevs[i] = "Bax";
                    break;
                }
                case 2: {
                    abbrevs[i] = "Laaw";
                    break;
                }
                case 3: {
                    abbrevs[i] = "Tir";
                    break;
                }
                case 4: {
                    abbrevs[i] = "Sharc";
                    break;
                }
                case 5: {
                    abbrevs[i] = "Yash";
                    break;
                }
                case 6: {
                    abbrevs[i] = "Xaak";
                    break;
                }
                case 7: {
                    abbrevs[i] = "Ru";
                    break;
                }
                case 8: {
                    abbrevs[i] = "1 Sa";
                    break;
                }
                case 9: {
                    abbrevs[i] = "2 Sa";
                    break;
                }
                case 10: {
                    abbrevs[i] = "1 Bq";
                    break;
                }
                case 11: {
                    abbrevs[i] = "2 Bq";
                    break;
                }
                case 12: {
                    abbrevs[i] = "1 Tar";
                    break;
                }
                case 13: {
                    abbrevs[i] = "2 Tar";
                    break;
                }
                case 14: {
                    abbrevs[i] = "Ces";
                    break;
                }
                case 15: {
                    abbrevs[i] = "Nex";
                    break;
                }
                case 16: {
                    abbrevs[i] = "Es";
                    break;
                }
                case 17: {
                    abbrevs[i] = "Ay";
                    break;
                }
                case 18: {
                    abbrevs[i] = "Sab";
                    break;
                }
                case 19: {
                    abbrevs[i] = "Maa";
                    break;
                }
                case 20: {
                    abbrevs[i] = "Wac";
                    break;
                }
                case 21: {
                    abbrevs[i] = "Gaba";
                    break;
                }
                case 22: {
                    abbrevs[i] = "Ish";
                    break;
                }
                case 23: {
                    abbrevs[i] = "Yer";
                    break;
                }
                case 24: {
                    abbrevs[i] = "Baro";
                    break;
                }
                case 25: {
                    abbrevs[i] = "Yex";
                    break;
                }
                case 26: {
                    abbrevs[i] = "Daa";
                    break;
                }
                case 27: {
                    abbrevs[i] = "Hoo";
                    break;
                }
                case 28: {
                    abbrevs[i] = "Yoo";
                    break;
                }
                case 29: {
                    abbrevs[i] = "Caa";
                    break;
                }
                case 30: {
                    abbrevs[i] = "Cob";
                    break;
                }
                case 31: {
                    abbrevs[i] = "Yoon";
                    break;
                }
                case 32: {
                    abbrevs[i] = "Miik";
                    break;
                }
                case 33: {
                    abbrevs[i] = "Nax";
                    break;
                }
                case 34: {
                    abbrevs[i] = "Xab";
                    break;
                }
                case 35: {
                    abbrevs[i] = "Sef";
                    break;
                }
                case 36: {
                    abbrevs[i] = "Xag";
                    break;
                }
                case 37: {
                    abbrevs[i] = "Sek";
                    break;
                }
                case 38: {
                    abbrevs[i] = "Mal";
                    break;
                }
                case 39: {
                    abbrevs[i] = "Mat";
                    break;
                }
                case 40: {
                    abbrevs[i] = "Mar";
                    break;
                }
                case 41: {
                    abbrevs[i] = "Luu";
                    break;
                }
                case 42: {
                    abbrevs[i] = "Yoox";
                    break;
                }
                case 43: {
                    abbrevs[i] = "Fal";
                    break;
                }
                case 44: {
                    abbrevs[i] = "Roo";
                    break;
                }
                case 45: {
                    abbrevs[i] = "1 Ko";
                    break;
                }
                case 46: {
                    abbrevs[i] = "2 Ko";
                    break;
                }
                case 47: {
                    abbrevs[i] = "Gal";
                    break;
                }
                case 48: {
                    abbrevs[i] = "Efe";
                    break;
                }
                case 49: {
                    abbrevs[i] = "Fil";
                    break;
                }
                case 50: {
                    abbrevs[i] = "Kol";
                    break;
                }
                case 51: {
                    abbrevs[i] = "1 Tes";
                    break;
                }
                case 52: {
                    abbrevs[i] = "2 Tes";
                    break;
                }
                case 53: {
                    abbrevs[i] = "1 Ti";
                    break;
                }
                case 54: {
                    abbrevs[i] = "2 Ti";
                    break;
                }
                case 55: {
                    abbrevs[i] = "Tiit";
                    break;
                }
                case 56: {
                    abbrevs[i] = "Fil";
                    break;
                }
                case 57: {
                    abbrevs[i] = "Cib";
                    break;
                }
                case 58: {
                    abbrevs[i] = "Yac";
                    break;
                }
                case 59: {
                    abbrevs[i] = "1 Bu";
                    break;
                }
                case 60: {
                    abbrevs[i] = "2 Bu";
                    break;
                }
                case 61: {
                    abbrevs[i] = "1 Yo";
                    break;
                }
                case 62: {
                    abbrevs[i] = "2 Yo";
                    break;
                }
                case 63: {
                    abbrevs[i] = "3 Yo";
                    break;
                }
                case 64: {
                    abbrevs[i] = "Yuu";
                    break;
                }
                case 65: {
                    abbrevs[i] = "Muuj";
                    break;
                }
                default:
                    break;
            }
        }
        this.chapterAbbrevs = abbrevs;

    }

    @Override
    public String getBookName(int chapter) {
        return books.get(chapter).name;
    }

    @Override
    public String getBookAbbrev(int chapter) {
        return books.get(chapter).abbr;
    }



    @Override
    public String getChapterVerses(int bookNumber, int chapter) {
        return chapters[bookNumber][chapter];
    }

    @Override
    public String getVerse(int bookNumber, int chapter, int verse) {
        return null;
    }

    @Override
    public String getVerses(int bookNumber, int chapter, int... verses) {
        return null;
    }

    class Book {
        int ordinal;
        String name;
        String abbr;

        public Book(int ordinal, String name, String abbr) {
            this.ordinal = ordinal;
            this.name = name;
            this.abbr = abbr;
        }
    }

    List<Book> books;
    private void setupBooks() {
        books = new ArrayList<>(66);
        books.add(new Book(0,"Bilowgii","Bil"));
        books.add(new Book(1,"Baxniintii","Bax"));
        books.add(new Book(2,"Laawiyiintii","Laaw"));
        books.add(new Book(3,"Tirintii","Tir"));
        books.add(new Book(4,"Sharciga Kunoqoshadiisa","Sharc"));
        books.add(new Book(5,"Yashuuca","Yash"));
        books.add(new Book(6,"Xaakinnada","Xaak"));
        books.add(new Book(7,"Ruud","Ru"));
        books.add(new Book(8,"Samuu'eel Kowaad","1 Sa"));
        books.add(new Book(9,"Samuu'eel Labaad","2 Sa"));
        books.add(new Book(10,"Boqorradii Kowaad","1 Bq"));
        books.add(new Book(11,"Boqorradii Labaad","2 Bq"));
        books.add(new Book(12,"Taariikhdii Kowaad","1 Tar"));
        books.add(new Book(13,"Taariikhdii Labaad","2 Tar"));
        books.add(new Book(14,"Cesraa","Ces"));
        books.add(new Book(15,"Nexemyaah","Nex"));
        books.add(new Book(16,"Esteer","Es"));
        books.add(new Book(17,"Ayuub","Ay"));
        books.add(new Book(18,"Sabuurradii","Sab"));
        books.add(new Book(19,"Maahmaahyadii","Maa"));
        books.add(new Book(20,"Wacdiyahii","Wac"));
        books.add(new Book(21,"Gabaygii Sulaymaan","Gaba"));
        books.add(new Book(22,"Ishacyaah","Ish"));
        books.add(new Book(23,"Yeremyaah","Yer"));
        books.add(new Book(24,"Baroorashadii Yeremyaah","Baro"));
        books.add(new Book(25,"Yexesqeel","Yex"));
        books.add(new Book(26,"Daanyeel","Daa"));
        books.add(new Book(27,"Hoosheeca","Hoo"));
        books.add(new Book(28,"Yoo'eel","Yoo"));
        books.add(new Book(29,"Caamoos","Caa"));
        books.add(new Book(30,"Cobadyaah","Cob"));
        books.add(new Book(31,"Yoonis","Yoon"));
        books.add(new Book(32,"Miikaah","Miik"));
        books.add(new Book(33,"Naxuum","Nax"));
        books.add(new Book(34,"Xabaquuq","Xab"));
        books.add(new Book(35,"Sefanyaah","Sef"));
        books.add(new Book(36,"Xaggay","Xag"));
        books.add(new Book(37,"Sekaryaah","Sek"));
        books.add(new Book(38,"Malaakii","Mal"));
        books.add(new Book(39,"Matayos","Mat"));
        books.add(new Book(40,"Markos","Mar"));
        books.add(new Book(41,"Luukos","Luu"));
        books.add(new Book(42,"Yooxanaa","Yoox"));
        books.add(new Book(43,"Falimaha Rasuullada","Fal"));
        books.add(new Book(44,"Rooma","Roo"));
        books.add(new Book(45,"1 Korintos","1 Ko"));
        books.add(new Book(46,"2 Korintos","2 Ko"));
        books.add(new Book(47,"Galatiya","Gal"));
        books.add(new Book(48,"Efesos","Efe"));
        books.add(new Book(49,"Filiboy","Fil"));
        books.add(new Book(50,"Kolosay","Kol"));
        books.add(new Book(51,"1 Tesaloniika","1 Tes"));
        books.add(new Book(52,"2 Tesaloniika","2 Tes"));
        books.add(new Book(53,"1 Timoteyos","1 Ti"));
        books.add(new Book(54,"2 Timoteyos","2 Ti"));
        books.add(new Book(55,"Tiitos","Tiit"));
        books.add(new Book(56,"Filemon","Fil"));
        books.add(new Book(57,"Cibraaniyada","Cib"));
        books.add(new Book(58,"Yacquub","Yac"));
        books.add(new Book(59,"1 Butros","1 Bu"));
        books.add(new Book(60,"2 Butros","2 Bu"));
        books.add(new Book(61,"1 Yooxanaa","1 Yo"));
        books.add(new Book(62,"2 Yooxanaa","2 Yo"));
        books.add(new Book(63,"3 Yooxanaa","3 Yo"));
        books.add(new Book(64,"Yuudas","Yuu"));
        books.add(new Book(65,"Muujintii","Muuj"));
    }
}
