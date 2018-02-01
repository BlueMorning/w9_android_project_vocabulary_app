package example.codeclan.com.vocabulary_application.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Model.MeaningModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumWordTypeAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.MeaningsListAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.ViewMeaningsListAdapter;

public class ViewWordActivity extends AppCompatActivity {


    // Tab Item Word
    private TextView viewWordSpelling;
    private TextView viewWordType;
    private ListView viewWordMeanings;
    private WordsRoomDatabase database;
    private WordModel wordModel;
    private Button viewWordDeleteButton;

    // Tab Item Stats
    private TextView wordStatsLatestTestWrongAnswersPercentage;
    private TextView wordStatsLatestTestRightAnswersPercentage;

    private TextView wordStatsAllTestRightAnswersPercentage;
    private TextView wordStatsAllTestWrongAnswersPercentage;

    private TextView wordStatsAllTestWrongAnswers;
    private TextView wordStatsAllTestRightAnswers;

    private TextView wordStatsLatestTestWrongAnswers;
    private TextView wordStatsLatestTestRightAnswers;

    private TextView wordStatsTrainingStepProgressBar;
    private TextView wordStatsTrainingStep;

    private TextView imageMasteryLevel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_word);

        database                = WordsRoomDatabase.getDatabase(this);
        WordEntity wordEntity   = (WordEntity)this.getIntent().getSerializableExtra("wordEntity");
        wordModel               = new WordModel(wordEntity, database);

        TabHost mTabHost;
        mTabHost = (TabHost)findViewById(R.id.viewWordTabHost);
        mTabHost.setup();

        TabHost.TabSpec spec1 = mTabHost.newTabSpec("Word");
        spec1.setContent(R.id.viewWordTabWord);
        spec1.setIndicator("Word");
        mTabHost.addTab(spec1);

        TabHost.TabSpec spec2 = mTabHost.newTabSpec("Stats");
        spec2.setContent(R.id.viewWordTabStats);
        spec2.setIndicator("Stats");
        mTabHost.addTab(spec2);


        viewWordSpelling        = findViewById(R.id.viewWordSpelling);
        viewWordType            = findViewById(R.id.viewWordType);
        viewWordMeanings        = findViewById(R.id.viewWordMeaningsList);
        viewWordDeleteButton    = findViewById(R.id.viewWordDeleteButton);

        viewWordDeleteButton.setTag(wordModel);

        viewWordSpelling.setText(StringUtils.capitalize(wordModel.getWordEntity().getSpelling().toUpperCase()));
        viewWordType.setText(wordModel.getWordEntity().getType().getLabel());

        wordModel.initializeMeanings();
        ViewMeaningsListAdapter meaningsListAdapter = new ViewMeaningsListAdapter(this,
                this.wordModel.getMeaningsList());
        viewWordMeanings.setAdapter(meaningsListAdapter);

    }

    public void onClickViewWordCloseButton(View button){
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }

    public void onClickViewWordDeleteButton(View button){

        WordModel wordModel = ((WordModel)(button).getTag());

        new AlertDialog.Builder(this)
                .setTitle("Delete a Word")
                .setMessage(String.format("Are you sure to delete %s ?", wordModel.getWordEntity().getSpelling().toUpperCase()))
                .setIcon(R.drawable.delete)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteWord(button);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void deleteWord(View button){
        WordModel wordModel = ((WordModel)(button).getTag());
        wordModel.delete();
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }
}
