package ru.itis.marvel_encyclopedia.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.graphics.drawable.DrawableWrapper;
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
import ru.itis.marvel_encyclopedia.fragments.LoaderCharactersFragment;
import ru.itis.marvel_encyclopedia.interfaces.TaskInterface;
import ru.itis.marvel_encyclopedia.providers.CharacterProvider;

/**
 * Created by Anatoly on 15.01.2017.
 */

public class RecyclerCharactersAdapter extends RecyclerView.Adapter<RecyclerCharactersAdapter.CharacterViewHolder> {
    List<Result> mCharacters;
    FragmentActivity fragmentActivity;
    Context context;

    public RecyclerCharactersAdapter(Context context, List<Result> characters, FragmentActivity fragmentActivity) {
        this.context = context;
        this.mCharacters = characters;
        this.fragmentActivity = fragmentActivity;
        getAsyncFragment();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.character_item,
                parent,
                false
        );
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CharacterViewHolder holder, final int position) {
        final Result result = mCharacters.get(position);
        holder.nameCharacter.setText(result.getName());
        Glide.with(context).load(result.getThumbnail().getPath() + "/standard_fantastic." + result.getThumbnail().getExtension()).fitCenter().into(holder.imgCharacter);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoFragment fragment = new InfoFragment().newInstance(result);
                fragmentActivity.getSupportFragmentManager().popBackStack();
                fragmentActivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_info,
                                fragment,
                                InfoFragment.class.getSimpleName()).addToBackStack(null).commit();
            }
        });
        holder.setChecked(CharacterProvider.getInstance(context).isFavourite(mCharacters.get(position)));
        holder.favouriteCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result tempResult = mCharacters.get(position);
                if(CharacterProvider.getInstance(context).isFavourite(tempResult)){
                    CharacterProvider.getInstance(context).deleteFromFavourite(tempResult);
                } else {
                    CharacterProvider.getInstance(context).addToFavourite(tempResult);
                }
                holder.setChecked(CharacterProvider.getInstance(context).isFavourite(mCharacters.get(position)));
            }
        });
        if(position == mCharacters.size() - 5){
            getAsyncFragment().startAsync(mCharacters.size(), null);
        }
    }

    private LoaderCharactersFragment getAsyncFragment(){
        LoaderCharactersFragment fragment = (LoaderCharactersFragment) fragmentActivity.getSupportFragmentManager().findFragmentByTag(LoaderCharactersFragment.class.getName() + "a");
        if(fragment==null){
            fragment = new LoaderCharactersFragment();
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(fragment, LoaderCharactersFragment.class.getName() + "a").commit();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public void updateInformation(List<Result> lastResults){
        mCharacters.addAll(lastResults);
        notifyDataSetChanged();
    }


    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameCharacter;
        ImageView imgCharacter;
        ImageButton favouriteCharacter;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            nameCharacter = (TextView) itemView.findViewById(R.id.name_character);
            imgCharacter = (ImageView) itemView.findViewById(R.id.character_img);
            favouriteCharacter = (ImageButton) itemView.findViewById(R.id.favourite);
        }

        public void setChecked(boolean f) {
            if (f) {
                favouriteCharacter.setImageResource(R.drawable.ic_star_black_24dp);
            } else {
                favouriteCharacter.setImageResource(R.mipmap.ic_star);
            }
        }

    }

}
