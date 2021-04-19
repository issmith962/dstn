package projects.isaacsmith.dstn.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.apache.commons.lang3.text.WordUtils;

import java.time.LocalDate;

public class SectionHeader implements Section{
    public LocalDate date;
    public int section;

    public SectionHeader(int section, LocalDate date) {
        this.section = section;
        this.date = date;
    }

    @Override
    public int type() {
        return TYPE_DATE;
    }

    @Override
    public int sectionPosition() {
        return section;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getText() {
        return WordUtils.capitalize(date.getDayOfWeek().toString().substring(0, 3).toLowerCase()) + ", " +
                WordUtils.capitalize(date.getMonth().toString().substring(0, 3).toLowerCase()) +
                " " + date.getDayOfMonth() + ", " + date.getYear();
    }
}
