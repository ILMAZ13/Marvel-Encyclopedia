package ru.itis.marvel_encyclopedia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;

/**
 * Created by Anatoly on 15.01.2017.
 */

public class RecyclerCharactersAdapter extends RecyclerView.Adapter<RecyclerCharactersAdapter.CharacterViewHolder>{
    List<Result> mCharacters;
    Context context;

    public RecyclerCharactersAdapter(Context context, List<Result> characters){
        this.context=context;
        this.mCharacters = characters;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.character_item ,
                parent,
                false
        );
        return new CharacterViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        final Result result = mCharacters.get(position);
        holder.nameCharacter.setText(result.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        //TODO вернуть героев из asyncTask через фрагмент
//        mCharacters.add(new Result());
        return mCharacters.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        TextView nameCharacter;
        public CharacterViewHolder(View itemView) {
            super(itemView);
            nameCharacter = (TextView) itemView.findViewById(R.id.name_character);
        }

    }

}
