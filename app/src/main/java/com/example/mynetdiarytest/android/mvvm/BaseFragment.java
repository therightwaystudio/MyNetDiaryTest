package com.example.mynetdiarytest.android.mvvm;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {

    abstract public T viewModel();

}
