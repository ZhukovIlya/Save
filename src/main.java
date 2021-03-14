import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class main {
    public static void main(String[] args) {
        File dir = new File("savegames");
        dir.mkdir();
        GameProgress gameProgress = new GameProgress(1,1,1,1);
        GameProgress gameProgress1 = new GameProgress(2,2,2,2);
        GameProgress gameProgress2 = new GameProgress(3,3,3,3);
        System.out.println(saveGame(gameProgress));

    }

    public static String saveGame(GameProgress gameProgress){
        String ret = "Прогрес не сохранён";
        File myFile = new File("savegames//" + "seve"+ new File("savegames").listFiles().length);
        try (FileOutputStream fos = new FileOutputStream(myFile)){
            byte[] bytes = gameProgress.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
            if(zipFiles(myFile.getName())){
                System.out.println("Zip сохранён");

            }
            ret = "Прогрес сохранён";
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return ret;
    }
    public static boolean zipFiles(String st) throws IOException {
        boolean a = false;
        File myFile = new File("savegames/" +"zip_" + st + ".zip");
        myFile.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(myFile);
        ZipOutputStream zip = new ZipOutputStream(fos)) {

            try(FileInputStream fis = new FileInputStream("savegames/" + st)) {
                ZipEntry entry = new ZipEntry("zip_" + st + ".txt");
                zip.putNextEntry(entry);
                byte[] text = new byte[fis.available()];
                fis.read(text);
                zip.write(text);
                zip.closeEntry();
                a = true;

            } catch (FileNotFoundException e){
                System.out.println("1error");
            }catch (IOException e){
                System.out.println("2error");
            }
        }

            return a;
    }
//    public GameProgress loadingTheGame(String file){
//
//        GameProgress gameProgress = new GameProgress();
//        return gameProgress;
//    }
}
