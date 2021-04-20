package projects.isaacsmith.dstn.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import projects.isaacsmith.dstn.mock_backend.MockServices;
import projects.isaacsmith.dstn.model.Racquet;

public class SearchPresenter {
    private final MockServices mMockServices;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public SearchPresenter() {
        mMockServices = new MockServices();
    }
    public ArrayList<Racquet> getRacquets() { return mMockServices.getRacquets(); }
}
