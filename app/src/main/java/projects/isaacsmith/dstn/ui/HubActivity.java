package projects.isaacsmith.dstn.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shuhart.stickyheader.StickyAdapter;
import com.shuhart.stickyheader.StickyHeaderItemDecorator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.text.WordUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

import projects.isaacsmith.dstn.RacquetListUtils;
import projects.isaacsmith.dstn.R;
import projects.isaacsmith.dstn.model.Racquet;
import projects.isaacsmith.dstn.model.Section;
import projects.isaacsmith.dstn.model.StrungRacquet;
import projects.isaacsmith.dstn.presenter.HubPresenter;
import projects.isaacsmith.dstn.ui.utils.CustomSpinner;

public class HubActivity extends AppCompatActivity implements ListItemClickListener{
    private AppBarConfiguration mAppBarConfiguration;
    private HubPresenter mHubPresenter;
    private NameRecyclerViewAdapter mNameRecyclerViewAdapter;

    TextView clientTotal;
    TextView profitTotal;
    TextView racquetTotal;

    private ArrayList<Racquet> racquets;
    private ArrayList<Section> formattedRacquets;
    DatePickerDialog datePicker;
    LocalDate date;
    Button customDateButton;
    RecyclerView nameRecyclerView;
    CustomSpinner dateSpinner;
    boolean all;
    long selection;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        mHubPresenter = new HubPresenter();
        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titleView = findViewById(R.id.toolbar_title);
        titleView.setText(R.string.stringing_hub);

        racquets = mHubPresenter.getRacquets();
        // Date Button
        customDateButton = (Button)findViewById(R.id.hub_date_button);

        // ---- "Add Racquet" FAB ----
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //getSupportFragmentManager().popBackStack();
                Intent intent = new Intent(HubActivity.this, AddRacquetActivity.class);
                startActivity(intent);
            }
        });
        selection = 0;
        // ---- Setup Date Spinners ----
        String[] dates = {"All", "Today", "This Week", "This Month", "This Year", "From Custom Date"};
        ArrayAdapter dateSpinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_selected, dates);
        dateSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dateSpinner = (CustomSpinner) findViewById(R.id.date_spinner);
        dateSpinner.setAdapter(dateSpinnerAdapter);
        dateSpinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            public void onSpinnerOpened() {
                dateSpinner.setSelected(true);
            }
            public void onSpinnerClosed() {
                dateSpinner.setSelected(false);
            }
        });
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (l == 0) {
                    date = LocalDate.now();
                    all = true;
                    customDateButton.setText("Custom Date");
                } else if (l == 1) {
                    date = LocalDate.now();
                    all = false;
                    customDateButton.setText("Custom Date");
                } else if (l == 2) {
                    date = LocalDate.now().minusWeeks(1);
                    all = false;
                    customDateButton.setText("Custom Date");
                } else if (l == 3) {
                    date = LocalDate.now().minusMonths(1);
                    all = false;
                    customDateButton.setText("Custom Date");
                } else if (l == 4) {
                    date = LocalDate.now().minusYears(1);
                    all = false;
                    customDateButton.setText("Custom Date");
                } else if (l == 5) {
                    date = LocalDate.now().minusYears(100000);
                    all = false;
                }
                ArrayList<Racquet> racquets;
                if (all) {
                    racquets = RacquetListUtils.fromDate(mHubPresenter.getRacquets(), date.minusYears(100));
                } else {
                    racquets = RacquetListUtils.fromDate(mHubPresenter.getRacquets(), date);
                }
                updateRacquets(racquets);
                selection = l;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // ---- Setup racquet totals ----
        clientTotal = findViewById(R.id.client_total);
        profitTotal = findViewById(R.id.profit_total);
        racquetTotal = findViewById(R.id.racquet_total);

        String clientTotalText = String.valueOf(mHubPresenter.getClientTotal());
        clientTotal.setText(clientTotalText);
        String racquetTotalText = String.valueOf(mHubPresenter.getRacquetTotal());
        racquetTotal.setText(racquetTotalText);
        String profitTotalText = String.valueOf("$" + mHubPresenter.getProfitTotal());
        profitTotal.setText(profitTotalText);

        nameRecyclerView = findViewById(R.id.hub_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        nameRecyclerView.setLayoutManager(layoutManager);
        updateRacquets(racquets);
        StickyHeaderItemDecorator decorator = new StickyHeaderItemDecorator(mNameRecyclerViewAdapter);
        decorator.attachToRecyclerView(nameRecyclerView);

        date = null;
    }

    private void updateRacquets(ArrayList<Racquet> racquets) {
        formattedRacquets = RacquetListUtils.splitRacquetsByDate(racquets);
        mNameRecyclerViewAdapter = new NameRecyclerViewAdapter(formattedRacquets, this);
        nameRecyclerView.setAdapter(mNameRecyclerViewAdapter);

        racquetTotal.setText(Integer.toString(RacquetListUtils.getRacquetTotal(racquets)));
        profitTotal.setText("$" + Integer.toString(RacquetListUtils.getProfitTotal(racquets)));
        clientTotal.setText(Integer.toString(RacquetListUtils.getClientTotal(racquets)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Racquet> racquets = mHubPresenter.getRacquets();
        if (all) {
            racquets = RacquetListUtils.fromDate(mHubPresenter.getRacquets(), date.minusYears(100));
        } else {
            racquets = RacquetListUtils.fromDate(mHubPresenter.getRacquets(), date);
        }
        updateRacquets(racquets);
        dateSpinner.setSelection((int)selection);
        customDateButton.setText("Custom Date");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onListItemClick(int position) {
        //Toast.makeText(this, ((StrungRacquet)formattedRacquets.get(position)).racquet.clientName, Toast.LENGTH_SHORT).show();
        Racquet r = ((StrungRacquet) formattedRacquets.get(position)).racquet;
        Intent intent = new Intent(HubActivity.this, EditRacquetActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("clientName", r.clientName);
        bundle.putString("stringBrand", r.stringBrand);
        bundle.putString("stringType", r.stringType);
        bundle.putString("tension", r.tension);
        bundle.putString("unit", r.unit);
        bundle.putString("quantity", r.quantity);
        bundle.putString("notes", r.notes);
        bundle.putSerializable("date", r.date);
        bundle.putString("id", r.id.toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            v.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            activity.getWindow().getDecorView();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
                findViewById(android.R.id.content).clearFocus();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showDatePickerDialog(View v) {
        dateSpinner.setSelection(5);
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePicker = new DatePickerDialog(HubActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        LocalDate newDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
                        String dayOfWeek = WordUtils.capitalize(newDate.getDayOfWeek().toString().toLowerCase());
                        date = newDate;
                        String displayDate = WordUtils.capitalize(Month.of(monthOfYear + 1).name().substring(0,3).toLowerCase()) +
                                " " + dayOfMonth + ", " + year;
                        customDateButton.setText(displayDate);

                        ArrayList<Racquet> racquets = RacquetListUtils.fromDate(mHubPresenter.getRacquets(), date);
                        updateRacquets(racquets);
                    }
                }, year, month, day);
        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePicker.show();
    }

    private class NameRecyclerViewAdapter extends StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder> {
        private int TYPE_NAME = 1;
        private int TYPE_FINAL_NAME = 2;
        private int TYPE_DATE = 3;

        private final ListItemClickListener mOnClickListener;
        private ArrayList<Section> items;

        public NameRecyclerViewAdapter(ArrayList<Section> formattedRacquets, ListItemClickListener onClickListener) {
            this.items = formattedRacquets;
            this.mOnClickListener = onClickListener;
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).type();
        }

        @NonNull
        @Override
        public GenericHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if (viewType == TYPE_NAME) {
                view = LayoutInflater.from(HubActivity.this).inflate(R.layout.name_row, parent, false);
                return new NameHolder(view);
            } else if (viewType == TYPE_FINAL_NAME) {
                view = LayoutInflater.from(HubActivity.this).inflate(R.layout.final_name_row, parent, false);
                return new NameHolder(view);
            } else if (viewType == TYPE_DATE) {
                view = LayoutInflater.from(HubActivity.this).inflate(R.layout.date_row, parent, false);
                return new DateHolder(view);
            } else {
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int type;
            if (getItemViewType(position) == TYPE_NAME || getItemViewType(position) == TYPE_FINAL_NAME) {
                ((NameHolder) holder).bindName(items.get(position).getText());
                ((NameHolder) holder).setNew(items.get(position).isNew());
            } else if (getItemViewType(position) == TYPE_DATE) {
                ((DateHolder) holder).bindDate(items.get(position).getText());
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public int getHeaderPositionForItem(int itemPosition) {
            return items.get(itemPosition).sectionPosition();
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int headerPosition) {
            ((DateHolder) holder).bindDate(getTextFromSectionPosition(headerPosition));
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            return createViewHolder(parent, TYPE_DATE);
        }

        public String getTextFromSectionPosition(int position) {
            for (Section s : items) {
                if ((s.type() == TYPE_DATE) && (s.sectionPosition() == position)) {
                    return s.getText();
                }
            }
            return null;
        }

        public class NameHolder extends GenericHolder implements View.OnClickListener {
            private final TextView name;
            private final ImageView image;

            public NameHolder(@NonNull View itemView) {
                super(itemView);

                itemView.setOnClickListener(this);
                name = itemView.findViewById(R.id.hub_name);
                image = itemView.findViewById(R.id.new_indicator);
            }
            public void bindName(String newName) {
                name.setText(newName);
            }

            public void setNew(boolean isNew) {
                if (isNew) {
                    image.setVisibility(View.VISIBLE);
                } else {
                    image.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                mOnClickListener.onListItemClick(position);
            }
        }

        public class FinalNameHolder extends GenericHolder implements View.OnClickListener {
            private final TextView name;
            private final ImageView image;

            public FinalNameHolder(@NonNull View itemView) {
                super(itemView);

                itemView.setOnClickListener(this);
                name = itemView.findViewById(R.id.hub_name);
                image = itemView.findViewById(R.id.new_indicator);
            }

            public void bindName(String newName) {
                name.setText(newName);
            }

            public void setNew(boolean isNew) {
                if (isNew) {
                    image.setVisibility(View.VISIBLE);
                } else {
                    image.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                mOnClickListener.onListItemClick(position);
            }
        }

        public abstract class GenericHolder extends RecyclerView.ViewHolder {
            public GenericHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class DateHolder extends GenericHolder {
            private final TextView date;

            public DateHolder(@NonNull View itemView) {
                super(itemView);

                date = itemView.findViewById(R.id.hub_date);
            }

            public void bindDate(String newDate) {
                date.setText(newDate);
            }
        }
    }
}