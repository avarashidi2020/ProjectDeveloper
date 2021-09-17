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
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import app.activity.ActivityEdit;
import app.activity.ActivityMore;
import app.datamodel.Model_Post;
import app.proje_retrofit.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


  Context context;
  List<Model_Post> data;
  Intercae_Like_Post intercae_like_post;

  public Adapter(Context context, List<Model_Post> data, Intercae_Like_Post intercae_like_post) {
    this.context = context;
    this.data = data;
    this.intercae_like_post = intercae_like_post;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    final Model_Post data = this.data.get(position);

    Glide.with(context).load(data.getImage()).into(holder.img_post);

    holder.txt_title.setText(data.getTitle());

    if (data.getUserfavorite().equals("true")) {
      holder.img_like.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.icon_favorite, null));
    } else {
      holder.img_like.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.icon_no_favorite, null));
    }
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


    holder.relative_like.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        intercae_like_post.intercae_Like_Post(data.getId(),holder.img_like);
      }
    });


    holder.relative_edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(context, ActivityEdit.class);
        intent.putExtra("idpost",data.getId());
        context.startActivity(intent);
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
    RelativeLayout relative_like;
    RelativeLayout relative_edit;
    ImageView img_like;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      txt_title = (TextView) itemView.findViewById(R.id.txt_title);
      img_post = (ImageView) itemView.findViewById(R.id.img_post);
      relative_like = (RelativeLayout) itemView.findViewById(R.id.relative_like);
      relative_edit = (RelativeLayout) itemView.findViewById(R.id.relative_edit);
      img_like = (ImageView) itemView.findViewById(R.id.img_like);
    }
  }


  public interface Intercae_Like_Post {
    void intercae_Like_Post(String idpost,ImageView imageView);
  }
}
