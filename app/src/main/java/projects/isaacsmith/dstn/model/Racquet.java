package projects.isaacsmith.dstn.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.UUID;

public class Racquet implements Comparable<Racquet> {
    public UUID id;
    public LocalDate date;
    public String clientName;
    public String stringBrand;
    public String stringType;
    public String tension;
    public String unit;
    public String quantity;
    public String notes;

    public Racquet() {
    }

    public Racquet(UUID id, LocalDate date, String clientName, String stringBrand, String stringType, String tension, String unit, String quantity, String notes) {
        this.id = id;
        this.date = date;
        this.clientName = clientName;
        this.stringBrand = stringBrand;
        this.stringType = stringType;
        this.tension = tension;
        this.unit = unit;
        this.quantity = quantity;
        this.notes = notes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(Racquet racquet) {
        return this.date.compareTo(racquet.date);
    }
}
