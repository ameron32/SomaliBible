package com.ameron32.apps.somalibible.bible;

import android.content.Context;
import android.content.res.AssetManager;

import com.ameron32.apps.somalibible.frmk.IBible;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        return 66;
    }

    @Override
    public int getChapterCount(int book) {
        return chapterCount[book-1];
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
    public void setChapterNames(String[] names) {

        for (int i=0; i<66; i++){
            switch (i){
                case 0: { names[i] = "Bilowgii"; break; }
                case 1: { names[i] = "Baxniintii"; break; }
                case 2: { names[i] = "Laawiyiintii"; break; }
                case 3: { names[i] = "Tirintii"; break; }
                case 4: { names[i] = "Sharciga Kunoqoshadiisa"; break; }
                case 5: { names[i] = "Yashuuca"; break; }
                case 6: { names[i] = "Xaakinnada"; break; }
                case 7: { names[i] = "Ruud"; break; }
                case 8: { names[i] = "Samuu'eel Kowaad"; break; }
                case 9: { names[i] = "Samuu'eel Labaad"; break; }
                case 10: { names[i] = "Boqorradii Kowaad"; break; }
                case 11: { names[i] = "Boqorradii Labaad"; break; }
                case 12: { names[i] = "Taariikhdii Kowaad"; break; }
                case 13: { names[i] = "Taariikhdii Labaad"; break; }
                case 14: { names[i] = "Cesraa"; break; }
                case 15: { names[i] = "Nexemyaah"; break; }
                case 16: { names[i] = "Esteer"; break; }
                case 17: { names[i] = "Ayuub"; break; }
                case 18: { names[i] = "Sabuurradii"; break; }
                case 19: { names[i] = "Maahmaahyadii"; break; }
                case 20: { names[i] = "Wacdiyahii"; break; }
                case 21: { names[i] = "Gabaygii Sulaymaan"; break; }
                case 22: { names[i] = "Ishacyaah"; break; }
                case 23: { names[i] = "Yeremyaah"; break; }
                case 24: { names[i] = "Baroorashadii Yeremyaah"; break; }
                case 25: { names[i] = "Yexesqeel"; break; }
                case 26: { names[i] = "Daanyeel"; break; }
                case 27: { names[i] = "Hoosheeca"; break; }
                case 28: { names[i] = "Yoo'eel"; break; }
                case 29: { names[i] = "Caamoos"; break; }
                case 30: { names[i] = "Cobadyaah"; break; }
                case 31: { names[i] = "Yoonis"; break; }
                case 32: { names[i] = "Miikaah"; break; }
                case 33: { names[i] = "Naxuum"; break; }
                case 34: { names[i] = "Xabaquuq"; break; }
                case 35: { names[i] = "Sefanyaah"; break; }
                case 36: { names[i] = "Xaggay"; break; }
                case 37: { names[i] = "Sekaryaah"; break; }
                case 38: { names[i] = "Malaakii"; break; }
                case 39: { names[i] = "Matayos"; break; }
                case 40: { names[i] = "Markos"; break; }
                case 41: { names[i] = "Luukos"; break; }
                case 42: { names[i] = "Yooxanaa"; break; }
                case 43: { names[i] = "Falimaha Rasuullada"; break; }
                case 44: { names[i] = "Rooma"; break; }
                case 45: { names[i] = "1 Korintos"; break; }
                case 46: { names[i] = "2 Korintos"; break; }
                case 47: { names[i] = "Galatiya"; break; }
                case 48: { names[i] = "Efesos"; break; }
                case 49: { names[i] = "Filiboy"; break; }
                case 50: { names[i] = "Kolosay"; break; }
                case 51: { names[i] = "1 Tesaloniika"; break; }
                case 52: { names[i] = "2 Tesaloniika"; break; }
                case 53: { names[i] = "1 Timoteyos"; break; }
                case 54: { names[i] = "2 Timoteyos"; break; }
                case 55: { names[i] = "Tiitos"; break; }
                case 56: { names[i] = "Filemon"; break; }
                case 57: { names[i] = "Cibraaniyada"; break; }
                case 58: { names[i] = "Yacquub"; break; }
                case 59: { names[i] = "1 Butros"; break; }
                case 60: { names[i] = "2 Butros"; break; }
                case 61: { names[i] = "1 Yooxanaa"; break; }
                case 62: { names[i] = "2 Yooxanaa"; break; }
                case 63: { names[i] = "3 Yooxanaa"; break; }
                case 64: { names[i] = "Yuudas"; break; }
                case 65: { names[i] = "Muujintii"; break; }
                default: break;
            }
        }
        chapterNames = names;
    }

    @Override
    public void setAbbrevs(String[] abbrevs) {

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
    public String getChapterName(int chapter) {
        return chapterNames[chapter];
    }

    @Override
    public String getChapterAbbrev(int chapter) {
        return chapterAbbrevs[chapter];
    }
}
