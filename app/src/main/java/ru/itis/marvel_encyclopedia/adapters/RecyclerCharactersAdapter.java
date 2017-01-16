package ru.itis.marvel_encyclopedia.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.itis.marvel_encyclopedia.POJO.Result;
import ru.itis.marvel_encyclopedia.R;
import ru.itis.marvel_encyclopedia.activities.FavoriteActivity;
import ru.itis.marvel_encyclopedia.activities.ListActivity;
import ru.itis.marvel_encyclopedia.fragments.InfoFragment;
import ru.itis.marvel_encyclopedia.providers.CharacterProvider;

/**
 * Created by Anatoly on 15.01.2017.
 */

public class RecyclerCharactersAdapter extends RecyclerView.Adapter<RecyclerCharactersAdapter.CharacterViewHolder>{
    List<Result> mCharacters;
    Activity activity;
    Context context;

    public RecyclerCharactersAdapter(Context context, List<Result> characters, Activity activity){
        this.context=context;
        this.mCharacters = characters;
        this.activity = activity;
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
        Glide.with(context).load(result.getThumbnail().getPath()+"/standard_fantastic."+result.getThumbnail().getExtension()).fitCenter().into(holder.imgCharacter);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof ListActivity) {

                    InfoFragment fragment = new InfoFragment().newInstance(result);
                    ((ListActivity) activity).getSupportFragmentManager().popBackStack();
                    ((ListActivity) activity).getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_info,
                                    fragment,
                                    InfoFragment.class.getSimpleName()).addToBackStack(null).commit();
                }
                if (activity instanceof FavoriteActivity) {

                    InfoFragment fragment = new InfoFragment().newInstance(result);
                    ((FavoriteActivity) activity).getSupportFragmentManager().popBackStack();
                    ((FavoriteActivity) activity).getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_info,
                                    fragment,
                                    InfoFragment.class.getSimpleName()).addToBackStack(null).commit();
                }
            }
        });
        holder.favouriteCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharacterProvider.getInstance(context).addCharacter(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        TextView nameCharacter;
        ImageView imgCharacter;
        ImageButton favouriteCharacter;
        public CharacterViewHolder(View itemView) {
            super(itemView);
            nameCharacter = (TextView) itemView.findViewById(R.id.name_character);
            imgCharacter = (ImageView) itemView.findViewById(R.id.character_img);
            favouriteCharacter = (ImageButton) itemView.findViewById(R.id.favourite);

        }

    }

}
