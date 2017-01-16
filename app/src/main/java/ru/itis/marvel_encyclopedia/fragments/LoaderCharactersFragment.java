package ru.itis.marvel_encyclopedia.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.itis.marvel_encyclopedia.asyncTasks.AsyncTaskGetCharacters;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;

/**
 * Created by Anatoly on 15.01.2017.
 */

public class LoaderCharactersFragment extends Fragment {
    private TaskInterface mTaskInterface;
    private AsyncTaskGetCharacters myAsync;

    public boolean isRunning() {return myAsync!=null;}

    @Override
    public void onAttach(Context context) {
        setInterface(context);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        setInterface(activity);
        super.onAttach(activity);
    }

    private void setInterface(Context context){
        if(context instanceof TaskInterface)
            mTaskInterface = (TaskInterface) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
//        myAsync = new AsyncTaskGetCharacters(mTaskInterface);
//        myAsync.execute();
    }

    public void startAsync(){
        if(myAsync==null){
            myAsync = new AsyncTaskGetCharacters(mTaskInterface);
            myAsync.execute();
        }
    }
    public void stopAsync(){
        if(myAsync!=null){
            myAsync.cancel(true);
            myAsync=null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myAsync=null;
    }
}
