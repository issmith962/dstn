package projects.isaacsmith.dstn.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class StrungRacquet implements Section {
    public Racquet racquet;
    public int section;
    public boolean lastInSection;

    public StrungRacquet(Racquet racquet, int section, boolean lastInSection) {
        this.racquet = racquet;
        this.section = section;
        this.lastInSection = lastInSection;
    }

    @Override
    public int type() {
        if (lastInSection) {
            return TYPE_FINAL_NAME;
        } else {
            return TYPE_NAME;
        }
    }

    @Override
    public int sectionPosition() {
        return section;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean isNew() {
        return (racquet.date.equals(LocalDate.now()));
    }

    @Override
    public String getText() {
        return racquet.clientName;
    }
}
