package com.example.ejercicio10;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// 1. Import the necessary Fragment classes
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // --- START: ADD THIS CODE BLOCK ---

        // This 'if' statement prevents creating new fragments on top of old ones
        // when the screen is rotated or the activity is recreated.
        if (savedInstanceState == null) {
            // 2. Get the FragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();

            // 3. Begin a transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 4. Create two separate instances of your 'contador' fragment
            contador fragmento1 = new contador();
            contador fragmento2 = new contador();

            // 5. Add each fragment instance to its specific container
            fragmentTransaction.add(R.id.fragment_container_1, fragmento1);
            fragmentTransaction.add(R.id.fragment_container_2, fragmento2);

            // 6. Commit the transaction to make the fragments appear
            fragmentTransaction.commit();
        }

        // --- END: ADD THIS CODE BLOCK ---


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
