public class ClinicActivity extends AppCompatActivity {
    private EditText editTextClinicName, editTextAddress, editTextVATNumber;
    private Button buttonCreateClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_create_clinic);

        editTextClinicName = findViewById(R.id.editTextClinicName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextVATNumber = findViewById(R.id.editTextVATNumber);
        buttonCreateClinic = findViewById(R.id.buttonCreateClinic);

        buttonCreateClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String clinicName = editTextClinicName.getText().toString();
                String address = editTextAddress.getText().toString();
                String vatNumber = editTextVATNumber.getText().toString();

                
                createClinic(clinicName, address, vatNumber);
            }
        });
    }

    private void createClinic(String clinicName, String address, String vatNumber) {
       
    }
}
