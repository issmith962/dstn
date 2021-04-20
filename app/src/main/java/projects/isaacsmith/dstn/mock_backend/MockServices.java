package projects.isaacsmith.dstn.mock_backend;

import android.os.Build;
import android.widget.RadioButton;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import projects.isaacsmith.dstn.model.Racquet;

public class MockServices {
    private final MockDatabase mMockDatabase;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MockServices() {
        mMockDatabase = MockDatabase.getInstance();
    }

    public int getRacquetTotal() {
        return mMockDatabase.getRacquetTotal();
    }
    public int getClientTotal() {
        return mMockDatabase.getClientTotal();
    }
    public int getProfitTotal() {
        return mMockDatabase.getProfitTotal();
    }
    public void addRacquet(Racquet racquet) {
        mMockDatabase.addRacquet(racquet);
    }
    public ArrayList<String> getNames() {
        return mMockDatabase.getNames();
    }
    public ArrayList<Racquet> getRacquets() { return mMockDatabase.getRacquets(); }
    public void addName(String name) {
        mMockDatabase.addName(name);
    }
    public Racquet lastRacquetOfName(String name) { return mMockDatabase.lastRacquetOfName(name); }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRacquetsFromFile(InputStream input) {
        mMockDatabase.addRacquetsFromFile(input);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void editRacquet(UUID id, LocalDate date, String clientName, String stringBrand, String stringType, String tension, String tensionUnit, String quantity, String notes) {
        mMockDatabase.editRacquet(id, date, clientName, stringBrand, stringType, tension, tensionUnit, quantity, notes);
    }
}
