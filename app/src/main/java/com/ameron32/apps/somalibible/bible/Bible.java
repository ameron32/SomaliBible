package com.ameron32.apps.somalibible.bible;

import android.content.Context;
import android.content.res.AssetManager;

import com.ameron32.apps.somalibible.frmk.IBible;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Bible implements IBible
{

    AssetManager am;
    int[] chapterCount;
    String[][]chapters;
    String[] bookNames;
    String[] bookAbbrevs;


    public Bible(AssetManager am){
        chapterCount = new int[66];
        chapters = new String[66][];
        bookNames = new String[66];
        bookAbbrevs = new String[66];
        setupBooks();
        this.am = am;
    }





    private boolean isLoaded = false;
    public boolean isLoaded() {
        return isLoaded;
    }

    public void load(Context c){

        populateChapterCounts(c);
        isLoaded = true;

/*        for (int i=0; i<66; i++){
            for (int j=0; j<chapterCount[i]; j++){
                chapters[i][j]=loadChapter(i, j);
            }
        }*/

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
        return 66;
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
    public String getBookName(int chapter) {
        return bookNames[chapter];
        //return books.get(chapter).name;
    }

    @Override
    public String getBookAbbrev(int chapter) {

        return bookAbbrevs[chapter];
        //return books.get(chapter).abbr;
    }



    @Override
    public String getChapterVerses(int bookNumber, int chapter) {
        chapters[bookNumber][chapter]=loadChapter(bookNumber, chapter);
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


    private void setupAbbrevs(){
        bookAbbrevs[0] = "Bil";
        bookAbbrevs[1] = "Bax";
        bookAbbrevs[2] = "Laaw";
        bookAbbrevs[3] = "Tir";
        bookAbbrevs[4] = "Shar";
        bookAbbrevs[5] = "Yash";
        bookAbbrevs[6] = "Xaak";
        bookAbbrevs[7] = "Ru";
        bookAbbrevs[8] = "1 Sa";
        bookAbbrevs[9] = "2 Sa";

        bookAbbrevs[10] = "1 Bq";
        bookAbbrevs[11] = "2 Bq";
        bookAbbrevs[12] = "1 Ta";
        bookAbbrevs[13] = "2 Ta";
        bookAbbrevs[14] = "Ces";
        bookAbbrevs[15] = "Nex";
        bookAbbrevs[16] = "Es";
        bookAbbrevs[17] = "Ay";
        bookAbbrevs[18] = "Sab";
        bookAbbrevs[19] = "Maa";

        bookAbbrevs[20] = "Wac";
        bookAbbrevs[21] = "Gab";
        bookAbbrevs[22] = "Ish";
        bookAbbrevs[23] = "Yer";
        bookAbbrevs[24] = "Bar";
        bookAbbrevs[25] = "Yex";
        bookAbbrevs[26] = "Daan";
        bookAbbrevs[27] = "Hoo";
        bookAbbrevs[28] = "Yns";
        bookAbbrevs[29] = "Caa";

        bookAbbrevs[30] = "Cob";
        bookAbbrevs[31] = "Yoon";
        bookAbbrevs[32] = "Miik";
        bookAbbrevs[33] = "Nax";
        bookAbbrevs[34] = "Xab";
        bookAbbrevs[35] = "Sef";
        bookAbbrevs[36] = "Xag";
        bookAbbrevs[37] = "Sek";
        bookAbbrevs[38] = "Mal";
        bookAbbrevs[39] = "Mat";

        bookAbbrevs[40] = "Mar";
        bookAbbrevs[41] = "Lu";
        bookAbbrevs[42] = "Yoox";
        bookAbbrevs[43] = "Fal";
        bookAbbrevs[44] = "Roo";
        bookAbbrevs[45] = "1 Ko";
        bookAbbrevs[46] = "2 Ko";
        bookAbbrevs[47] = "Ga";
        bookAbbrevs[48] = "Ef";
        bookAbbrevs[49] = "Fil";

        bookAbbrevs[50] = "Kol";
        bookAbbrevs[51] = "1 Te";
        bookAbbrevs[52] = "2 Te";
        bookAbbrevs[53] = "1 Ti";
        bookAbbrevs[54] = "2 Ti";
        bookAbbrevs[55] = "Tii";
        bookAbbrevs[56] = "Fil";
        bookAbbrevs[57] = "Cib";
        bookAbbrevs[58] = "Yac";
        bookAbbrevs[59] = "1 Bu";

        bookAbbrevs[60] = "2 Bu";
        bookAbbrevs[61] = "1 Yo";
        bookAbbrevs[62] = "2 Yo";
        bookAbbrevs[63] = "3 Yo";
        bookAbbrevs[64] = "Yuu";
        bookAbbrevs[65] = "Muuj";
    }

    private void setupBooks() {

        setupAbbrevs();

        bookNames[0] = "Bilowgii";
        bookNames[1] = "Baxniintii";
        bookNames[2] = "Laawiyiintii";
        bookNames[3] = "Tirintii";
        bookNames[4] = "Sharciga Kunoqoshadiisa";
        bookNames[5] = "Yashuuca";
        bookNames[6] = "Xaakinnada";
        bookNames[7] = "Ruud";
        bookNames[8] = "Samuu'eel Kowaad";
        bookNames[9] = "Samuu'eel Labaad";

        bookNames[10] = "Boqorradii Kowaad";
        bookNames[11] = "Boqorradii Labaad";
        bookNames[12] = "Taariikhdii Kowaad";
        bookNames[13] = "Taariikhdii Labaad";
        bookNames[14] = "Cesraa";
        bookNames[15] = "Nexemyaah";
        bookNames[16] = "Esteer";
        bookNames[17] = "Ayuub";
        bookNames[18] = "Sabuurradii";
        bookNames[19] = "Maahmaahyadii";

        bookNames[20] = "Wacdiyahii";
        bookNames[21] = "Gabaygii Sulaymaan";
        bookNames[22] = "Ishacyaah";
        bookNames[23] = "Yeremyaah";
        bookNames[24] = "Baroorashadii Yeremyaah";
        bookNames[25] = "Yexesqeel";
        bookNames[26] = "Daanyeel";
        bookNames[27] = "Hoosheeca";
        bookNames[28] = "Yoo'eel";
        bookNames[29] = "Caamoos";

        bookNames[30] = "Cobadyaah";
        bookNames[31] = "Yoonis";
        bookNames[32] = "Miikaah";
        bookNames[33] = "Naxuum";
        bookNames[34] = "Xabaquuq";
        bookNames[35] = "Sefanyaah";
        bookNames[36] = "Xaggay";
        bookNames[37] = "Sekaryaah";
        bookNames[38] = "Malaakii";
        bookNames[39] = "Matayos";

        bookNames[40] = "Markos";
        bookNames[41] = "Luukos";
        bookNames[42] = "Yooxanaa";
        bookNames[43] = "Falimaha Rasuullada";
        bookNames[44] = "Rooma";
        bookNames[45] = "1 Korintos";
        bookNames[46] = "2 Korintos";
        bookNames[47] = "Galatiya";
        bookNames[48] = "Efesos";
        bookNames[49] = "Filiboy";

        bookNames[50] = "Kolosay";
        bookNames[51] = "1 Tesaloniika";
        bookNames[52] = "2 Tesaloniika";
        bookNames[53] = "1 Timoteyos";
        bookNames[54] = "2 Timoteyos";
        bookNames[55] = "Tiitos";
        bookNames[56] = "Filemon";
        bookNames[57] = "Cibraaniyada";
        bookNames[58] = "Yacquub";
        bookNames[59] = "1 Butros";

        bookNames[60] = "2 Butros";
        bookNames[61] = "1 Yooxanaa";
        bookNames[62] = "2 Yooxanaa";
        bookNames[63] = "3 Yooxanaa";
        bookNames[64] = "Yuudas";
        bookNames[65] = "Muujintii";




        /*
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
        books.add(new Book(65,"Muujintii","Muuj"));*/
    }
}
