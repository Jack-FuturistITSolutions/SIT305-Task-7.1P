package com.example.task71p;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAdvertActivity extends AppCompatActivity {

    RadioGroup radioGroupType;
    RadioButton radioLost, radioFound;
    EditText editName, editTitle, editPhone, editDescription, editDate, editLocation;
    Button buttonSaveAdvert;
    ImageButton backButton;
    LostAndFoundDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_advert);

        // Initialise database
        database = new LostAndFoundDatabase(this);

        // Initialise views
        radioGroupType = findViewById(R.id.radioGroupType);
        radioLost = findViewById(R.id.radioLost);
        radioFound = findViewById(R.id.radioFound);
        editTitle = findViewById(R.id.editTitle);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);
        editLocation = findViewById(R.id.editLocation);
        buttonSaveAdvert = findViewById(R.id.buttonSaveAdvert);

        // Set the back button up with a listener that closes the activity on click, returning to MainActivity
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Collect inputs and handle logic for saving the advert
        buttonSaveAdvert.setOnClickListener(v -> {
            int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
            String type = (selectedTypeId == R.id.radioLost) ? "Lost" : "Found";
            String title = editTitle.getText().toString().trim();
            String name = editName.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String description = editDescription.getText().toString().trim();
            String date = editDate.getText().toString().trim(); // Convert this to a Date Picker like in previous task for 9.1P
            String location = editLocation.getText().toString().trim();

            // Ensure all fields are filled
            if (name.isEmpty() || title.isEmpty() || selectedTypeId == -1 || description.isEmpty() || date.isEmpty() || location.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert the advert into the database, await confirmation for successful insert
            boolean success = database.insertAdvert(title, type, name, phone, description, date, location);

            // Handle insert outcome
            if (success) {
                Toast.makeText(this, "Advert saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to save advert.", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle system window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.advert), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}