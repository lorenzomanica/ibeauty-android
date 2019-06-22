package com.cursoandroid.danielmorsch.ibeauty.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.danielmorsch.ibeauty.R;
import com.cursoandroid.danielmorsch.ibeauty.domain.Credential;
import com.cursoandroid.danielmorsch.ibeauty.domain.Usuario;
import com.cursoandroid.danielmorsch.ibeauty.services.ConnectionManager;
import com.cursoandroid.danielmorsch.ibeauty.services.UsuarioService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.button_login_id)
    Button botao;

    @BindView(R.id.text_username)
    TextView username;

    @BindView(R.id.text_password)
    TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login_id)
    void doLogin() {

        Credential credential = new Credential(username.getText().toString(), password.getText().toString());

        UsuarioService service = ConnectionManager.getInstance().createUsuarioService();
        Call<Usuario> call = service.login(credential);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    goToHome(response.body());
                } else {
                    showError("Usuário ou senha inválidos");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                showError("Falha na autenticação");

            }
        });
    }

    private void showError(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void goToHome(Usuario user) {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("USER", user);
        startActivity(i);
    }
}
