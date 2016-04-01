package com.majie.stugrade.ui.score;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ScoreService {
    @GET("DataServlet")
    Observable<ScoreEntity> getData(@Query("account") String account);
}
