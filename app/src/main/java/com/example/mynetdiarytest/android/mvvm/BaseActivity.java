package com.example.mynetdiarytest.android.mvvm;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    abstract public T viewModel();

}
