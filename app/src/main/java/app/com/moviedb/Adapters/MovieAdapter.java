package app.com.moviedb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.moviedb.Models.Result;
import app.com.moviedb.R;
import app.com.moviedb.UI.Activities.DetailActivity;
import app.com.moviedb.Utils.DateUtils;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder>{

    private List<Result> movieList;
    private Context mContext;

    private static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public MovieAdapter(List<Result> movies){
        this.movieList = movies;
    }

    @NonNull
    @Override
    public MoviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_single_item,parent,false);
        mContext = parent.getContext();
        return new MoviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviewViewHolder holder, int position) {

        String image_url = IMAGE_URL_BASE_PATH + movieList.get(holder.getAdapterPosition()).getPosterPath();
        String movieType = "Genre: ";
        Glide.with(mContext).load(image_url).into(holder.moviePoster);

        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.movieReleaseDate.setText("Release date: "+ DateUtils.getStringDate(movieList.get(position).getReleaseDate()));
        holder.moviePopularity.setText("Popularity: "+movieList.get(position).getPopularity());
        holder.movieRating.setRating(Float.parseFloat(movieList.get(position).getVoteAverage().toString()) / 2);

        for (int i : movieList.get(position).getGenreIds()){
            movieType+=getGenre(i)+", ";
        }
        holder.movieType.setText(movieType);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public String getGenre(int id){
        Map<Integer,String> genreMap = new HashMap<>();
        genreMap.put(28,"Action");
        genreMap.put(12,"Adventure");
        genreMap.put(16,"Animation");
        genreMap.put(35,"Comedy");
        genreMap.put(80,"Crime");
        genreMap.put(99,"Documentary");
        genreMap.put(18,"Drama");
        genreMap.put(10751,"Family");
        genreMap.put(14,"Fantasy");
        genreMap.put(36,"History");
        genreMap.put(27,"Horror");
        genreMap.put(10402,"Music");
        genreMap.put(9648,"Mystery");
        genreMap.put(10749,"Romance");
        genreMap.put(878,"Science Fiction");
        genreMap.put(10770,"TV Movie");
        genreMap.put(53,"Thriller");
        genreMap.put(10752,"War");
        genreMap.put(37,"Western");

        return genreMap.get(id);
    }

    public class MoviewViewHolder extends RecyclerView.ViewHolder {

        private TextView movieTitle;
        private RatingBar movieRating;
        private TextView moviePopularity;
        private TextView movieType;
        private TextView movieReleaseDate;
        private ImageView moviePoster;
        private LinearLayout movieDetails;

        private MoviewViewHolder(View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movie_title);
            movieRating = itemView.findViewById(R.id.movie_rating);
            moviePopularity = itemView.findViewById(R.id.movie_popularity);
            movieType = itemView.findViewById(R.id.movie_type);
            movieReleaseDate = itemView.findViewById(R.id.movie_release_date);
            moviePoster = itemView.findViewById(R.id.movie_image);
            movieDetails = itemView.findViewById(R.id.moview_details);

            movieDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailIntent = new Intent(mContext, DetailActivity.class);
                    detailIntent.putExtra("DetailObject",movieList.get(getAdapterPosition()));
                    mContext.startActivity(detailIntent);
                }
            });

        }
    }

}
