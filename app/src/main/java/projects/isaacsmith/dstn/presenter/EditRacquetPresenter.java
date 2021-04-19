package projects.isaacsmith.dstn.presenter;

import android.os.Build;
import android.widget.RadioButton;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import projects.isaacsmith.dstn.mock_backend.MockServices;
import projects.isaacsmith.dstn.model.Racquet;

public class EditRacquetPresenter {
    private MockServices mMockServices;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public EditRacquetPresenter() {
        mMockServices = new MockServices();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void editRacquet(UUID id, LocalDate date, String clientName, String stringBrand, String stringType, String tension, String tensionUnit, String quantity, String notes) {
        mMockServices.editRacquet(id, date, clientName, stringBrand, stringType, tension, tensionUnit, quantity, notes);
    }
    public ArrayList<Racquet> getRacquets() { return mMockServices.getRacquets(); }
}
