package projects.isaacsmith.dstn.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import projects.isaacsmith.dstn.R;
import projects.isaacsmith.dstn.RacquetListUtils;
import projects.isaacsmith.dstn.model.Racquet;
import projects.isaacsmith.dstn.model.StrungRacquet;
import projects.isaacsmith.dstn.presenter.SearchPresenter;

public class SearchActivity extends AppCompatActivity implements ListItemClickListener {
    private SearchPresenter mSearchPresenter;
    private ArrayList<Racquet> mRacquets;

    private RecyclerView mRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchPresenter = new SearchPresenter();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titleView = toolbar.findViewById(R.id.toolbar_title);
        titleView.setText(R.string.search_title);

        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // Do search with this string
            mRacquets = RacquetListUtils.sortAlphabetically(
                    RacquetListUtils.suggestionize(query, mSearchPresenter.getRacquets()));
            mRecyclerView = findViewById(R.id.search_recycler);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(mRacquets, this);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onListItemClick(int position) {
        Racquet r = ((Racquet) mRacquets.get(position));
        Intent intent = new Intent(SearchActivity.this, AddRacquetActivity.class);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        finish();
        startActivity(intent);
    }

    private class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameView;
        private SearchRecyclerAdapter adapter;

        public SearchViewHolder(@NonNull View itemView, SearchRecyclerAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
            nameView = itemView.findViewById(R.id.search_name);
        }
        public void bindName(String name) {
            nameView.setText(name);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            adapter.mOnClickListener.onListItemClick(position);
        }
    }

    private class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchViewHolder> {
        private int TYPE_ROW = 1;
        private int TYPE_FINAL_ROW = 2;

        private ArrayList<Racquet> racquets;
        private final ListItemClickListener mOnClickListener;

        public SearchRecyclerAdapter(ArrayList<Racquet> racquets, ListItemClickListener onClickListener) {
            this.racquets = racquets;
            this.mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if (viewType == TYPE_FINAL_ROW) {
                view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.final_search_row, parent, false);
            } else {
                view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.search_row, parent, false);
            }
            return new SearchViewHolder(view, this);
        }

        @Override
        public int getItemViewType(int position) {
            if (position == racquets.size() - 1) {
                return TYPE_FINAL_ROW;
            } else {
                return TYPE_ROW;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
            ((SearchViewHolder) holder).bindName(racquets.get(position).clientName);
        }

        @Override
        public int getItemCount() {
            return racquets.size();
        }


    }

}
