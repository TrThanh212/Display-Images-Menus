package com.example.displayimagesmenus;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ImageView myImageView, UrlImageView;
    private Button changeImageButton;
    private boolean isImage1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Display images from local file
        myImageView = findViewById(R.id.myImageView);
        changeImageButton = findViewById(R.id.changeImageButton);

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImage1) {
                    myImageView.setImageResource(R.drawable.hinh1);
                    changeImageButton.setText("Change Image 1");
                } else {
                    myImageView.setImageResource(R.drawable.lamnen);
                    changeImageButton.setText("Change Image 2");
                }
                isImage1 = !isImage1;
            }
        });
        // Display images from URL - Picasso
        String url = "https://cdn.pixabay.com/photo/2015/10/09/00/55/lotus-978659_640.jpg";

        UrlImageView = findViewById(R.id.UrlImageView);

        if (url == null || url.isEmpty()) {
            // If URL is null or empty, load placeholder/error image
            Picasso.get()
                    .load(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(UrlImageView);
        } else {
            // Load image from URL
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(UrlImageView);
        }
        /////
        /////fINAL
        // Register a view to a context menu
        TextView articleText = findViewById(R.id.article);
        registerForContextMenu(articleText);
    }

    //Menu Options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Event onOptionsItemSelected
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_favorites) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.context_edit) {

            Toast.makeText(this, "Held down edit", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.context_share) {

            Toast.makeText(this, "Held down Share", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}
