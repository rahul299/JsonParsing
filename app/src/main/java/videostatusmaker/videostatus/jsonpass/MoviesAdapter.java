package videostatusmaker.videostatus.jsonpass;

import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Modul> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, username,email,street,suite,city,zipcode,lat,lag;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            username = (TextView) view.findViewById(R.id.username);
            email = (TextView) view.findViewById(R.id.email);
            street = (TextView) view.findViewById(R.id.street);
            suite = (TextView) view.findViewById(R.id.suite);
            city = (TextView) view.findViewById(R.id.city);
            zipcode = (TextView) view.findViewById(R.id.zipcode);
            lat = (TextView) view.findViewById(R.id.lat);
            lag = (TextView) view.findViewById(R.id.lag);
        }
    }


    public MoviesAdapter(List<Modul> moviesList) {
        this.moviesList = moviesList;
//        Log.i("bbbb","getId---"+moviesList.get(0).getName());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Modul movie = moviesList.get(position);
        holder.id.setText(movie.getId());
        Log.i("bbbb","getId---"+movie.getId());
        holder.name.setText(movie.getName());
        holder.username.setText(movie.getUsername());
        holder.email.setText(movie.getEmail());
        holder.street.setText(movie.getStreet());
        holder.suite.setText(movie.getSuite());
        holder.city.setText(movie.getCity());
        holder.zipcode.setText(movie.getZipcode());
        holder.lat.setText(movie.getLat());
        holder.lag.setText(movie.getLag());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}