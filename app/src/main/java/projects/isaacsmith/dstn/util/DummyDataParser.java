package projects.isaacsmith.dstn.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import projects.isaacsmith.dstn.model.Racquet;

public class DummyDataParser {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Racquet> getDummyDataFromFile(InputStream input) {
        ArrayList<Racquet> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                int year = Integer.parseInt(tokens[0]);
                int month = Integer.parseInt(tokens[1]);
                int day = Integer.parseInt(tokens[2]);
                String clientName = tokens[3];
                String stringBrand = tokens[4];
                String stringType = tokens[5];
                String tension = tokens[6];
                String unit = tokens[7];
                String quantity = tokens[8];
                String notes = tokens[9];

                Racquet r = new Racquet(UUID.randomUUID(), LocalDate.of(year, month, day), clientName, stringBrand, stringType, tension, unit, quantity, notes);
                result.add(r);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
