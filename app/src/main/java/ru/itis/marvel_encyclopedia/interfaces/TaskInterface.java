package ru.itis.marvel_encyclopedia.interfaces;

import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;

/**
 * Created by Anatoly on 15.01.2017.
 */

public interface TaskInterface {
    void OnTaskFinish(List<Result> characters);
    void OnTaskStart();
    void OnTaskProgress();
    void OnTask2Finish(List<Result> characters);
}
