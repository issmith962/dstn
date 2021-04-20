package projects.isaacsmith.dstn.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import projects.isaacsmith.dstn.mock_backend.MockServices;
import projects.isaacsmith.dstn.model.Racquet;

public class HubPresenter {
    private MockServices mMockServices;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public HubPresenter() {
        mMockServices = new MockServices();
    }
    public ArrayList<String> getNames() {
        return mMockServices.getNames();
    }
    public ArrayList<Racquet> getRacquets() { return mMockServices.getRacquets(); }
    public int getRacquetTotal() {
        return mMockServices.getRacquetTotal();
    }
    public int getClientTotal() {
        return mMockServices.getClientTotal();
    }
    public int getProfitTotal() {
        return mMockServices.getProfitTotal();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRacquetsFromFile(InputStream input) {
        mMockServices.addRacquetsFromFile(input);
    }
}
