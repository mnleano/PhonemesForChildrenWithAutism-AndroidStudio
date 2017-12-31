package com.pguese.pfcwa.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pguese.pfcwa.R;
import com.pguese.pfcwa.utils.MediaPlayerHelper;
import com.pguese.pfcwa.data.Const;
import com.pguese.pfcwa.data.Flashcards;
import com.pguese.pfcwa.model.FlashcardItem;
import com.pguese.pfcwa.widgets.TextViewCG;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlashcardActivity extends BaseActivity {

    String TAG = FlashcardActivity.class.getSimpleName();

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivSpeak)
    ImageView ivSpeak;
    @BindView(R.id.ivFC1)
    ImageView ivFC1;
    @BindView(R.id.ivFC2)
    ImageView ivFC2;
    @BindView(R.id.ivFC3)
    ImageView ivFC3;
    @BindView(R.id.tvFlashcard)
    TextViewCG tvFlashcard;
    @BindView(R.id.ivFlashcard)
    ImageView ivFlashcard;


    List<FlashcardItem> flashcards;
    FlashcardItem flashcard;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        index = getIntent().getIntExtra(Const.KEY_INDEX, 0);
        flashcards = Flashcards.getFlashcard(index);

        Picasso.with(this).load(flashcards.get(0).getDrawable()).into(ivFC1);
        Picasso.with(this).load(flashcards.get(1).getDrawable()).into(ivFC2);
        Picasso.with(this).load(flashcards.get(2).getDrawable()).into(ivFC3);

        displayFlashcard(0);
    }

    @OnClick({R.id.ivBack, R.id.ivSpeak, R.id.ivFC1, R.id.ivFC2, R.id.ivFC3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                super.onBackPressed();
                break;
            case R.id.ivSpeak:
                MediaPlayerHelper.repeat();
                break;
            case R.id.ivFC1:
                displayFlashcard(0);
                break;
            case R.id.ivFC2:
                displayFlashcard(1);
                break;
            case R.id.ivFC3:
                displayFlashcard(2);
                break;
        }
    }

    private void displayFlashcard(int i) {
        flashcard = flashcards.get(i);
        tvFlashcard.setText(flashcard.getName());
        Picasso.with(this).load(flashcard.getDrawable()).into(ivFlashcard);
        MediaPlayerHelper.playFlashcard(this, Flashcards.getPlaybackLetter(index), flashcard.getPlayback());

    }

    //    private void play() {
    //        if (mediaPlayer != null)
    //            if (mediaPlayer.isPlaying())
    //                mediaPlayer.stop();
    //        mediaPlayer.start();
//    }


}
