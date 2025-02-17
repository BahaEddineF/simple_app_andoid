package Android.tp_n1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Appliquer les insets pour le système de barres
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des éléments de l'interface
        EditText amountInput = findViewById(R.id.amountInput);
        TextView resultText = findViewById(R.id.resultText);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton dinarToEuro = findViewById(R.id.dinarToEuro);
        RadioButton euroToDinar = findViewById(R.id.euroToDinar);
        Button convertButton = findViewById(R.id.convertButton);

        // Ajouter un écouteur de clic sur le bouton de conversion
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountString = amountInput.getText().toString();

                if (amountString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un montant", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Récupérer le montant saisi
                    double amount = Double.parseDouble(amountString);
                    double result = 0;

                    // Vérifier quelle conversion est sélectionnée
                    if (dinarToEuro.isChecked()) {
                        // Conversion Dinar ➡ Euro
                        result = amount * 0.0075; // Exemple de taux de change
                    } else if (euroToDinar.isChecked()) {
                        // Conversion Euro ➡ Dinar
                        result = amount * 133.33; // Exemple de taux de change
                    } else {
                        Toast.makeText(MainActivity.this, "Veuillez sélectionner une option de conversion", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Afficher le résultat
                    resultText.setText(String.format("%.2f dinars", result));
                } catch (NumberFormatException e) {
                    // Gérer l'erreur si l'entrée n'est pas un nombre valide
                    Toast.makeText(MainActivity.this, "Veuillez entrer un montant valide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}