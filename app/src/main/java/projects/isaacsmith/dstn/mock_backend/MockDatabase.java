package projects.isaacsmith.dstn.mock_backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import projects.isaacsmith.dstn.RacquetListUtils;
import projects.isaacsmith.dstn.model.Racquet;
import projects.isaacsmith.dstn.model.Section;

public class MockDatabase {
    private static MockDatabase instance;
    private static int racquetTotal;
    private static int clientTotal;
    private static int profitTotal;
    private static ArrayList<String> names;
    private static ArrayList<Racquet> racquets;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MockDatabase() {
        racquetTotal = 72;
        clientTotal = 21;
        profitTotal = 230;

        names = new ArrayList<>();
        names.add("MON. MARCH 18 2021");
        names.add("Ben Howard");
        names.add("Damien Rice");
        names.add("John Jameon");
        names.add("Podward Harding");
        names.add("TUES. MARCH 19 2021");
        names.add("Harry Hugo");
        names.add("Callum Scallop");
        names.add("Poames Johnson");
        names.add("WED. MARCH 20 2021");
        names.add("Caitlin Smith");
        names.add("Poisaac Smith");
        names.add("MON. MARCH 18 2021");
        names.add("Ben Howard");
        names.add("Damien Rice");
        names.add("John Jameson");
        names.add("Podward Harding");
        names.add("TUES. MARCH 19 2021");
        names.add("Harry Hugo");
        names.add("Callum Scallop");
        names.add("Poames Johnson");
        names.add("WED. MARCH 20 2021");
        names.add("Caitlin Smith");
        names.add("Poisaac Smith");
        names.add("MON. MARCH 18 2021");
        names.add("Ben Howard");
        names.add("Damien Rice");
        names.add("John Jameson");
        names.add("Podward Harding");
        names.add("TUES. MARCH 19 2021");
        names.add("Harry Hugo");
        names.add("Callum Scallop");
        names.add("Poames Johnson");
        names.add("WED. MARCH 20 2021");
        names.add("Caitlin Smith");
        names.add("Poisaac Smith");
        names.add("MON. MARCH 18 2021");
        names.add("Ben Howard");
        names.add("Damien Rice");
        names.add("John Jameson");
        names.add("Podward Harding");
        names.add("TUES. MARCH 19 2021");
        names.add("Harry Hugo");
        names.add("Callum Scallop");
        names.add("Poames Johnson");
        names.add("WED. MARCH 20 2021");
        names.add("Caitlin Smith");
        names.add("Poisaac Smith");

        racquets = new ArrayList<>();

        Racquet r0 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 4, 10), "Isaac Smith", "Babolat", "RPM", "50", "lb", "2", "4 knots");
        Racquet r1 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 10, 1), "Harry Smith", "Wilson", "NXT", "49", "lb", "1", "");
        Racquet r2 = new Racquet(UUID.randomUUID(), LocalDate.of(2019, 11, 13), "John Smith", "Solinco", "Tour Bite", "54", "lb", "3", "");
        Racquet r3 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 12, 12), "Tim Smith", "Babolat", "RPM", "44", "lb", "1", "");
        Racquet r4 = new Racquet(UUID.randomUUID(), LocalDate.of(2019, 11, 13), "Bryan Smith", "Wilson", "NXT", "56", "lb", "5", "");
        Racquet r5 = new Racquet(UUID.randomUUID(), LocalDate.of(2019, 11, 13), "Colin Smith", "Solinco", "Tour Bite", "46", "lb", "1", "4 knots");
        Racquet r6 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 1, 5), "Harold Smith", "Solinco", "Tour Bite", "60", "lb", "2", "4 knots");
        Racquet r7 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 1, 5), "Todd Smith", "Babolat", "RPM", "45", "lb", "1", "4 knots");
        Racquet r8 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 1, 5), "Sarah Smith", "Wilson", "NXT", "37", "lb", "2", "4 knots");
        Racquet r9 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 9, 4), "Caitlin Smith", "Babolat", "RPM", "49", "lb", "1", "2 knots");
        Racquet r10 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 5, 7), "Ally Smith", "Solinco", "Tour Bite", "57", "lb", "2", "4 knots");
        Racquet r11 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 5, 8), "Mary Smith", "Wilson", "NXT", "58", "lb", "1", "4 knots");
        Racquet r12 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 1, 17), "Tami Smith", "Babolat", "RPM", "61", "lb", "1", "4 knots");
        Racquet r13 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 2, 15), "Sally Smith", "Solinco", "Tour Bite", "51", "lb", "1", "4 knots");
        Racquet r14 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 2, 15), "Carry Smith", "Wilson", "NXT", "52", "lb", "1", "4 knots");
        Racquet r15 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 2, 10), "Isaac Smith", "Babolat", "RPM", "54", "lb", "2", "4 knots");
        Racquet r16 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 2, 1), "Harry Smith", "Wilson", "NXT", "49", "lb", "1", "");
        Racquet r17 = new Racquet(UUID.randomUUID(), LocalDate.of(2019, 1, 13), "John Smith", "Solinco", "Tour Bite", "54", "lb", "3", "");
        Racquet r29 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 5, 12), "Tim Smith", "Babolat", "RPM", "44", "lb", "1", "");
        Racquet r18 = new Racquet(UUID.randomUUID(), LocalDate.of(2019, 5, 13), "Bryan Smith", "Wilson", "NXT", "56", "lb", "5", "");
        Racquet r19 = new Racquet(UUID.randomUUID(), LocalDate.of(2019, 9, 13), "Colin Smith", "Solinco", "Tour Bite", "46", "lb", "1", "4 knots");
        Racquet r20 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 1, 5), "Harold Smith", "Solinco", "Tour Bite", "60", "lb", "2", "4 knots");
        Racquet r21 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 4, 5), "Todd Smith", "Babolat", "RPM", "45", "lb", "1", "4 knots");
        Racquet r22 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 10, 5), "Sarah Smith", "Wilson", "NXT", "37", "lb", "2", "4 knots");
        Racquet r23 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 11, 4), "Caitlin Smith", "Babolat", "RPM", "49", "lb", "1", "2 knots");
        Racquet r24 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 10, 7), "Ally Smith", "Solinco", "Tour Bite", "57", "lb", "2", "4 knots");
        Racquet r25 = new Racquet(UUID.randomUUID(), LocalDate.of(2020, 11, 8), "Mary Smith", "Wilson", "NXT", "58", "lb", "1", "4 knots");
        Racquet r26 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 2, 17), "Tami Smith", "Babolat", "RPM", "61", "lb", "1", "4 knots");
        Racquet r27 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 2, 15), "Sally Smith", "Solinco", "Tour Bite", "51", "lb", "1", "4 knots");
        Racquet r28 = new Racquet(UUID.randomUUID(), LocalDate.of(2021, 3, 15), "Carry Smith", "Wilson", "NXT", "52", "lb", "1", "4 knots");

        racquets.add(r0);
        racquets.add(r1);
        racquets.add(r2);
        racquets.add(r3);
        racquets.add(r4);
        racquets.add(r5);
        racquets.add(r6);
        racquets.add(r7);
        racquets.add(r8);
        racquets.add(r9);
        racquets.add(r10);
        racquets.add(r11);
        racquets.add(r12);
        racquets.add(r13);
        racquets.add(r14);
        racquets.add(r15);
        racquets.add(r16);
        racquets.add(r17);
        racquets.add(r18);
        racquets.add(r19);
        racquets.add(r20);
        racquets.add(r21);
        racquets.add(r22);
        racquets.add(r23);
        racquets.add(r24);
        racquets.add(r25);
        racquets.add(r26);
        racquets.add(r27);
        racquets.add(r28);
        racquets.add(r29);

        ArrayList<Section> result = RacquetListUtils.splitRacquetsByDate(racquets);
        for (Racquet r : racquets) {
            names.add(r.clientName);
        }

    }

    public int getRacquetTotal() {
        return racquetTotal;
    }

    public int getClientTotal() {
        return clientTotal;
    }

    public int getProfitTotal() {
        return profitTotal;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Racquet> getRacquets() { return racquets; }

    public void addRacquet(Racquet racquet) {
        racquets.add(racquet);
    }

    public void addName(String name) {
        names.add(name);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void editRacquet(UUID id, LocalDate date, String clientName, String stringBrand, String stringType, String tension, String tensionUnit, String quantity, String notes) {
        Racquet r = racquets.stream().filter(racquet -> id.equals(racquet.id)).findFirst().orElse(null);
        if (r == null) {
            return;
        }
        r.clientName = clientName;
        r.stringBrand = stringBrand;
        r.stringType = stringType;
        r.tension = tension;
        r.unit = tensionUnit;
        r.quantity = quantity;
        r.notes = notes;
        r.date = date;
    }

    public Racquet lastRacquetOfName(String name) {
        ArrayList<Racquet> sortedRacquets = new ArrayList<>(racquets);
        Collections.sort(sortedRacquets);
        Collections.reverse(sortedRacquets);
        for (Racquet r : racquets) {
            if (r.clientName.toLowerCase().equals(name.toLowerCase())) {
                return r;
            }
        }
        return null;
    }
}
