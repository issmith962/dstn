package projects.isaacsmith.dstn.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import projects.isaacsmith.dstn.model.Racquet;
import projects.isaacsmith.dstn.mock_backend.MockServices;

public class AddRacquetPresenter {
    private MockServices mMockServices;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public AddRacquetPresenter() {
        mMockServices = new MockServices();
    }
    public void addRacquet(Racquet racquet) {
        mMockServices.addRacquet(racquet);
    }
    public void addName(String name) {
        mMockServices.addName(name);
    }
    public ArrayList<Racquet> getRacquets() { return mMockServices.getRacquets(); }
    public Racquet lastRacquetOfName(String name) { return mMockServices.lastRacquetOfName(name); }

}
