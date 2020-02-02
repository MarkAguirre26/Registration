package virtual.software.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageViewwerActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewwer);

        imageView = findViewById(R.id.imageViewer);

        imageView = findViewById(R.id.imageViewer);
        imageView.setImageBitmap(TemporaryData.bitmap);

        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(imageView);
        pAttacher.update();
    }


    @Override
    public void onBackPressed() {

//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}
