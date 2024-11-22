package com.unipim.pim_srv_fazenda_urbana_mobile;

import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.unipim.pim_srv_fazenda_urbana_mobile.databinding.ActivityMainBinding;
import com.unipim.pim_srv_fazenda_urbana_mobile.models.Cliente;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final String TAG = "NetworkCheck"; // Tag para logs de diagnóstico de rede.
    private Spinner spinnerClientes; // Dropdown para exibir a lista de clientes.
    private EditText editTextId, editTextNome, editTextEmail; // Campos de entrada para ID, Nome e Email do cliente.
    private Button buttonLoadClientes, buttonAddCliente, buttonUpdateCliente, buttonDeleteCliente; // Botões para ações CRUD.
    private Cliente cliente = new Cliente(); // Lista de objetos Cliente para armazenar dados carregados.
    private ArrayAdapter<String> adapter; // Adaptador para manipular o Spinner.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}