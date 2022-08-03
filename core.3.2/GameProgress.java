import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, int distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public static void main(String[] args) throws IOException {
        GameProgress game = new GameProgress(200, 7, 40, 45);
        GameProgress game1 = new GameProgress(150, 15, 55, 70);
        GameProgress game2 = new GameProgress(100, 30, 60, 90);
        saveGame("C://Games/savegames/save.dat", game);
        saveGame("C://Games/savegames/save2.dat", game1);
        saveGame("C://Games/savegames/save3.dat", game2);
        List<String> list = new ArrayList<>();
        list.add("save.dat");
        list.add("save2.dat");
        list.add("save3.dat");
        zipFiles("C://Games/savegames/zip.zip", list);
        deleteFiles("C://Games/savegames", list);


    }

    private static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
             FileInputStream fis = new FileInputStream(path)) {
            for (String list2 : list) {
                ZipEntry entry = new ZipEntry(list2);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
            }
            zout.closeEntry();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteFiles(String path, List<String> list) {
        File file = new File(path);
        for (String string3 : list) {
            File file1 = new File(path + "/" + string3);
            file1.delete();
        }

    }

    private static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }


    @Override
    public String toString() {
        return "GameProgress{ " +
                "health = " + health +
                ", weapons = " + weapons +
                ", lvl = " + lvl +
                ", distance = " + distance +
                '}';
    }
}