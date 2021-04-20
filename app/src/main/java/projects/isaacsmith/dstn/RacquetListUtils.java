package projects.isaacsmith.dstn;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import projects.isaacsmith.dstn.model.Racquet;
import projects.isaacsmith.dstn.model.Section;
import projects.isaacsmith.dstn.model.SectionHeader;
import projects.isaacsmith.dstn.model.StrungRacquet;

public class RacquetListUtils {
    public static ArrayList<Section> splitRacquetsByDate(ArrayList<Racquet> racquets) {
        ArrayList<Racquet> sortedRacquets = new ArrayList<>(racquets);
        Collections.sort(sortedRacquets);
        Collections.reverse(sortedRacquets);
        LocalDate prevDate = null;
        StrungRacquet prevRacquet = null;
        int section = 0;
        ArrayList<Section> result = new ArrayList<>();
        for (Racquet r : sortedRacquets) {
            LocalDate date = r.date;
            if (!date.equals(prevDate)) {
                section++;
                if (prevRacquet != null) {
                    prevRacquet.lastInSection = true;
                }
                result.add(new SectionHeader(section, date));
            }
            StrungRacquet newR = new StrungRacquet(r, section, false);
            result.add(newR);
            prevRacquet = newR;
            prevDate = r.date;
        }
        if (prevRacquet != null) {
            prevRacquet.lastInSection = true;
        }
        return result;
    }
    public static ArrayList<Racquet> suggestionize(String nameSoFar, ArrayList<Racquet> racquets) {
        ArrayList<Racquet> matchingRacquets = new ArrayList<>();
        for (Racquet r : racquets) {
            String[] fullName = r.clientName.split(" ");
            for (String name : fullName) {
                if (name.toLowerCase().startsWith(nameSoFar.toLowerCase())) {
                    matchingRacquets.add(r);
                }
            }
        }
        return matchingRacquets;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<Racquet> sortAlphabetically(ArrayList<Racquet> racquets) {
        ArrayList<Racquet> sortedRacquets = new ArrayList<>(racquets);
        Collections.sort(sortedRacquets, new Comparator<Racquet>() {
            @Override
            public int compare(Racquet racquet, Racquet t1) {
                return racquet.clientName.compareTo(t1.clientName);
            }
        });
        return sortedRacquets;
    }

    public static String[] racquetsToNames(ArrayList<Racquet> racquets) {
        String[] suggestions = new String[racquets.size()];
        for (int i = 0; i < racquets.size(); i++) {
            suggestions[i] = racquets.get(i).clientName;
        }
        return suggestions;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Racquet> fromDate(ArrayList<Racquet> racquets, LocalDate fromDate) {
        if (fromDate == null) {
            return racquets;
        }
        ArrayList<Racquet> result = new ArrayList<>();
        for (Racquet r : racquets) {
            if ((r.date.compareTo(fromDate) >= 0 ) && (r.date.compareTo(LocalDate.now()) <= 0)) {
                result.add(r);
            }
        }
        return result;
    }

    public static int getProfitTotal(ArrayList<Racquet> racquets) {
        return 12 * getRacquetTotal(racquets);
    }
    public static int getRacquetTotal(ArrayList<Racquet> racquets) {
        int total = 0;
        for (Racquet r : racquets) {
            if (!(r.quantity.equals("") || r.quantity == null)) {
                total += Integer.parseInt(r.quantity);
            }
        }
        return total;
    }
    public static int getClientTotal(ArrayList<Racquet> racquets) {
        ArrayList<String> names = new ArrayList<>();
        for (Racquet r : racquets) {
            if (!names.contains(r.clientName)) {
                names.add(r.clientName);
            }
        }
        return names.size();
    }
}
