package example.codeclan.com.vocabulary_application.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;

@Dao
public interface TrainingDao {


    @Query("SELECT * FROM trainings LIMIT 100")
    List<TrainingEntity> getAll();

    @Query("SELECT * FROM trainings WHERE tra_id = :id")
    TrainingEntity getTrainingById(Long id);

    @Query(   " SELECT * FROM words "
            + " INNER JOIN words_trainings_joins ON words_trainings_joins.wrd_tra_wrd_id = words.wrd_id"
            + " WHERE words_trainings_joins.wrd_tra_tra_id = :id ")
    List<WordEntity> getWordsByTrainingId(Long id);

    @Insert
    public Long insertTraining(TrainingEntity trainingEntity);

    @Update
    public void updateTraining(TrainingEntity training);

    @Delete
    public void deleteTraining(TrainingEntity training);

    @Query("DELETE FROM trainings WHERE tra_id = :id")
    void deleteTrainingById(Long id);

    @Query("DELETE FROM trainings")
    void deleteAllTrainings();

    @Query("SELECT * FROM trainings WHERE "+
            "     (:enumWordCount = 0  OR tra_total_words = :enumWordCount) " +
            " AND (:enumStatus    = '' OR tra_status = :enumStatus) " +
            " AND (:enumPriority  = '' " +
            "       OR (:enumPriority = 'LOW'       AND tra_next_best_training > date('now'))" +
            "       OR (:enumPriority = 'MEDIUM'    AND tra_next_best_training = date('now'))" +
            "       OR (:enumPriority = 'HIGH'      AND tra_next_best_training < date('now')))" +
            " ORDER BY tra_next_best_training ASC")
    List<TrainingEntity> getTrainingByPriorityAndWordCountAndStatus(String enumPriority,
                                                                    int enumWordCount,
                                                                    String enumStatus);
}
