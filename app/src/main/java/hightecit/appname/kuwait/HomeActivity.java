package hightecit.appname.kuwait;

import android.os.Bundle;

import hightecit.appname.kuwait.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater(), baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle("Home");
    }
}
