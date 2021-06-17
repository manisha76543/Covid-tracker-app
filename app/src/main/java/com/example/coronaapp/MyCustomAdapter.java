package com.example.coronaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<countryModel> {

    private Context context;
    private List<countryModel> countryModelsList;
    private List<countryModel> countryModelsListFiltered;


    public MyCustomAdapter(@NonNull Context context, List<countryModel> countryModelsList) {
        super(context, R.layout.list_custom_item,countryModelsList);

        this.context = context;
        this.countryModelsList=countryModelsList;
        this.countryModelsListFiltered = countryModelsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(imageView);


        return view;

    }

    @Override
    public int getCount() {

        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public countryModel getItem(int position) {
        return countryModelsListFiltered.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                if(constraint == null || constraint.length() == 0)
                {
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;
                }
                else
                {
                    List<countryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (countryModel itemModel : countryModelsList)
                    {
                        if(itemModel.getCountry().toLowerCase().contains(searchStr))
                        {
                            resultsModel.add(itemModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;

                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsListFiltered = (List<countryModel>) results.values;
                AffectedCountriesActivity.countryModelList = (List<countryModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;

    }
}
