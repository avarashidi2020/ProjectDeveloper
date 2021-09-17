package app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.activity.ActivityMore;
import app.datamodel.Model_Post;
import app.proje_retrofit.R;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {


  Context context;
  List<Model_Post> data;
  Intercae_delete_Post intercae_delete_post;

  public AdapterFavorite(Context context, List<Model_Post> data, Intercae_delete_Post intercae_like_post) {
    this.context = context;
    this.data = data;
    this.intercae_delete_post = intercae_like_post;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.item_1, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    final Model_Post data = this.data.get(position);

    Glide.with(context).load(data.getImage()).into(holder.img_post);

    holder.txt_title.setText(data.getTitle());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(context, ActivityMore.class);
        intent.putExtra("title", data.getTitle());
        intent.putExtra("image", data.getImage());
        intent.putExtra("linkvideo", data.getLinkvideo());
        context.startActivity(intent);


      }
    });


    holder.relative_delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        intercae_delete_post.intercae_delete_Post(data.getId(),position);
      }
    });

  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_title;
    ImageView img_post;
    RelativeLayout relative_delete;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      txt_title = (TextView) itemView.findViewById(R.id.txt_title);
      img_post = (ImageView) itemView.findViewById(R.id.img_post);
      relative_delete = (RelativeLayout) itemView.findViewById(R.id.relative_delete);
    }
  }


  public interface Intercae_delete_Post {
    void intercae_delete_Post(String idpost,int possition);
  }
}
