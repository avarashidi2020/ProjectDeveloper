package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import app.basefile.BaseFile;
import app.baseurl.BaseUrl;
import app.datamodel.Datamodel;
import app.proje_retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityEdit extends AppCompatActivity {

  Intent intent;
  BaseUrl baseUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);


    EditText editText = findViewById(R.id.edt_title);
    Button btnEdit = findViewById(R.id.btn_edit);
    ProgressBar progress = findViewById(R.id.progress);

    intent = getIntent();
    String idpost = intent.getStringExtra("idpost");


    btnEdit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (editText.getText().toString().isEmpty()) {
          editText.setError("لطفا نام جدید را وارد کنید");
          editText.findFocus();
        } else {
          progress.setVisibility(View.VISIBLE);
          Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl.geturl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
          BaseFile baseFile = retrofit.create(BaseFile.class);
          Call<Datamodel> calleditTittle = baseFile.editTitle(idpost, editText.getText().toString());
          calleditTittle.enqueue(new Callback<Datamodel>() {
            @Override
            public void onResponse(Call<Datamodel> call, Response<Datamodel> response) {
              progress.setVisibility(View.GONE);
              if (response.isSuccessful()) {
                Datamodel datamodel = response.body();
                if (datamodel.getStatus().equals("ok")) {
                  Toast.makeText(ActivityEdit.this, "نام پست ویرایش شد", Toast.LENGTH_SHORT).show();
                  finish();
                } else {
                  Toast.makeText(ActivityEdit.this, "خطا در ویرایش نام پست", Toast.LENGTH_LONG).show();
                }
              }
            }

            @Override
            public void onFailure(Call<Datamodel> call, Throwable t) {
              Toast.makeText(ActivityEdit.this, "خطا در ارسالس اطلاعات به سرور ..", Toast.LENGTH_SHORT).show();
              progress.setVisibility(View.GONE);
            }
          });
        }
      }
    });
  }
}