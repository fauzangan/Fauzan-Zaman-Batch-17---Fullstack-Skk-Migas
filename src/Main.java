import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] input = {"Pendanaan", "Terproteksi", "Untuk", "Dampak", "Berarti"};
        System.out.println(sorting(input));
    }

    public static String sorting(String[] input){
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> dictionary = new HashMap<>();

        // Menghitung jumlah kemunculan karakter
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[i].length(); j++){
                dictionary.put(input[i].charAt(j), dictionary.getOrDefault(input[i].charAt(j), 1)+1);
            }
        }

        // Mengubah hashmap menjadi entrySet agar bisa di iterasi
        Map<Character, Integer> sortedDictionary = dictionary.entrySet()
                        // menggunakan stream untuk melakukan iterasi
                        .stream()
                        ///melaukan komparasi berdasarkan jumlah nilai kemunculan huruf
                        .sorted((a, b) -> {
                            // membandingkan secara descending menggunakan compareTo dari Integer
                            int compare = b.getValue().compareTo(a.getValue());
                            // jika a dan b memiliki nilai yang sama maka akan dibandingkan berdasarkan karakter
                            if (compare == 0){
                                return compare(a.getKey(), b.getKey());
                            }
                            return compare;
                            // mengubah stream kembali menjadi Map
                        }).collect(Collectors.toMap(
                                // men-set Key
                                Map.Entry::getKey,
                                // men-set Value
                                Map.Entry::getValue,
                                // mengambil nilai lama dan mengabaikan nilai yang baru
                                (a1, a2) -> a1,
                                // map akan diurutkan menjad LinkedListMap untuk mengurutkan berdasarkan urutan diinputkan
                                LinkedHashMap::new
                ));

        // memasukan karakter yang sudah sorting ke StringBuilder
        sortedDictionary.forEach((key, value) -> res.append(key));

        // mengembalikan result
        return res.toString();
    }

    // membandingkan karakter dan huruf besar dan kecil
    public static int compare(Character a, Character b){
        return Character.compare(a, b);
    }
}